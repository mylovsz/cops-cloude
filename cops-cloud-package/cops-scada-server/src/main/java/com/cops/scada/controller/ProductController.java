package com.cops.scada.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.entity.Job;
import com.cops.scada.entity.Plan;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.JobVO;
import com.cops.scada.entity.VO.JobsVO;
import com.cops.scada.service.JobService;
import com.cops.scada.service.PlanService;
import com.cops.scada.service.ProductService;
import com.cops.scada.util.LayerData;
import com.cops.scada.util.RestResponse;
import com.xiaoleilu.hutool.Hutool;
import com.xiaoleilu.hutool.json.JSONObject;
import lombok.experimental.var;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import sun.reflect.generics.tree.Tree;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品表  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-11
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private PlanService planService;

    @Autowired
    private JobService jobService;

    @GetMapping("list")
    @SysLog("跳转产品表列表")
    public String list() {
        return "/product/list";
    }

    @RequiresPermissions("scada:product:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求产品表列表数据")
    public LayerData<Product> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                   ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Product> layerData = new LayerData<>();
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);
        if (!map.isEmpty()) {
            String sn = (String) map.get("sn");
            if (StringUtils.isNotBlank(sn)) {
                wrapper.like("sn", sn.trim());
            } else {
                map.remove("sn");
            }

            String name = (String) map.get("name");
            if (StringUtils.isNotBlank(name)) {
                wrapper.like("name", name.trim());
            } else {
                map.remove("name");
            }

            String type = (String) map.get("type");
            if (StringUtils.isNotBlank(type)) {
                wrapper.eq("type", type);
            } else {
                map.remove("type");
            }

            String bom = (String) map.get("bom");
            if (StringUtils.isNotBlank(bom)) {
                wrapper.like("bom", bom.trim());
            } else {
                map.remove("bom");
            }

            String state = (String) map.get("state");
            if (StringUtils.isNotBlank(state)) {
                wrapper.eq("state", state);
            } else {
                map.remove("state");
            }

        }
        Page<Product> pageData = productService.selectPage(new Page<>(page, limit), wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增产品表页面")
    public String add() {
        return "/product/add";
    }

    @RequiresPermissions("scada:product:add")
    @PostMapping("add")
    @SysLog("保存新增产品表数据")
    @ResponseBody
    public RestResponse add(Product product) {
        if (StringUtils.isBlank(product.getSn())) {
            return RestResponse.failure("产品编号不能为空");
        }
        if (productService.existsBySN(product.getSn())) {
            return RestResponse.failure("该产品编号已经存在，无需添加");
        }
        if (StringUtils.isBlank(product.getName())) {
            return RestResponse.failure("产品名称不能为空");
        }
        if (product.getType() == null) {
            return RestResponse.failure("产品类型不能为空");
        }
        if (StringUtils.isBlank(product.getBom())) {
            return RestResponse.failure("BOM编号不能为空");
        }
        if (product.getState() == null) {
            return RestResponse.failure("状态不能为空");
        }
        productService.insert(product);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑产品表页面")
    public String edit(Long id, Model model) {
        Product product = productService.selectById(id);
        model.addAttribute("product", product);
        return "/product/edit";
    }

    @RequiresPermissions("scada:product:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑产品表数据")
    public RestResponse edit(Product product) {
        if (null == product.getId() || 0 == product.getId()) {
            return RestResponse.failure("ID不能为空");
        }
        if (StringUtils.isBlank(product.getSn())) {
            return RestResponse.failure("产品编号不能为空");
        }
        if (productService.existsBySN(product.getSn(), product.getId())) {
            return RestResponse.failure("该产品编号已经存在");
        }
        if (StringUtils.isBlank(product.getName())) {
            return RestResponse.failure("产品名称不能为空");
        }
        if (product.getType() == null) {
            return RestResponse.failure("产品类型不能为空");
        }
        if (StringUtils.isBlank(product.getBom())) {
            return RestResponse.failure("BOM编号不能为空");
        }
        if (product.getState() == null) {
            return RestResponse.failure("状态不能为空");
        }
        productService.updateById(product);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:product:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除产品表数据")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (null == id || 0 == id) {
            return RestResponse.failure("ID不能为空");
        }
        Product product = productService.selectById(id);
        product.setDelFlag(true);
        productService.updateById(product);
        return RestResponse.success();
    }

    @GetMapping("/editsop")
    @SysLog("跳转编辑工艺路线表页面")
    public String editSOP(Long id, Model model) {
        List<Product> productList = productService.selectAll();
        model.addAttribute("productList", productList);

        Product product = productService.selectById(id);
        model.addAttribute("product", JSON.toJSONString(product, SerializerFeature.WriteMapNullValue));
        List<Job> jobs = jobService.getJobTemplateByProductId(id);
        if (jobs == null) {//不存在ID，返回全局模板
            jobs = jobService.getJobTemplate();
            model.addAttribute("jobs", JSON.toJSONString(jobs, SerializerFeature.WriteMapNullValue));
        } else {//存在ID ，返回产品模板
            model.addAttribute("jobs", JSON.toJSONString(jobs, SerializerFeature.WriteMapNullValue));
        }
        return "/product/editsop2";
    }


    @RequestMapping(value = "/getjobtemplate", method = RequestMethod.POST, consumes = "application/json")
    @SysLog("获取job模板")
    @ResponseBody
    public RestResponse getjobtemplate(@RequestBody String msg) {

        Map maps = (Map) JSON.parse(msg);

        Long id = Long.valueOf(maps.get("productid").toString());
        Long typeP = Long.valueOf(maps.get("identityType").toString());

        List<Job> jobs;
        if (typeP == 0) {
            jobs = jobService.getJobTemplate();
        } else {
            jobs = jobService.getJobTemplateByProductId(id);
        }
        //List<Job> jobs = jobService.getJobTemplateByProductId(312L);
        //List<Job> jobs = jobService.getJobTemplate();
        if(jobs==null)
        {
            return RestResponse.success("无数据");
        }
        String str = JSON.toJSON(jobs).toString();
        System.out.println(str);
        return RestResponse.success(str);
    }

    @RequestMapping("/getjobtemplate")
    @SysLog("获取job模板")
    @ResponseBody
    public RestResponse getjobtemplate2(HttpServletRequest request) {

        Long productid= Long.valueOf(request.getParameter("productid").toString());
        Long identityType=Long.valueOf(request.getParameter("identityType"));

        List<Job> jobs;
        if (identityType == 0) {
            jobs = jobService.getJobTemplate();
        } else {
            jobs = jobService.getJobTemplateByProductId(productid);
        }
        RestResponse response =new RestResponse();

        Map map = new HashMap();
        map.put("totalCount",jobs.size());
        map.put("list",jobs);
        response.setData(map);
        response.setAny("code",200);
        return response;
    }

    /**
     * 字符流读取方式，废弃
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/savejobtemplate", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    @SysLog("获取上传的作业模板数据")
    public String saveJobTemplate(HttpServletRequest req) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String s = null;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            System.out.println(sb.toString());
            JSONArray aa = JSON.parseArray(sb.toString());
            System.out.println("ok");
            System.out.println(aa.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping(value = "/savejobtemplate2", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    @SysLog("获取上传的作业模板数据")
    public RestResponse saveJobTemplate(@RequestBody JSONArray jsonParam) {
        List<Job> jobs = new ArrayList<>();
        try {
            jobs = jsonParam.toJavaList(Job.class);//前端编辑后的模板
        } catch (Exception ex) {
            return RestResponse.failure("Json转换失败，请检查数据格式");
        }

        if (jobs != null && jobs.size() > 0) {
            for (int i = 0; i < jobs.size(); i++) {
                Long id = jobs.get(i).getSort().longValue();
                jobs.get(i).setParentId(id);
                jobs.get(i).setParentIds(id + ",");
            }
            List<Job> productIdTemplte = jobService.getJobTemplateByProductId(jobs.get(0).getProductId());//获取数据库中产品的模板

            if (productIdTemplte == null) {//不存在产品模板，插入数据库
                for (Job job : jobs) {
                    job.setId(null);
                    jobService.insert(job);
                }
            } else {//存在，更新
                for (Job job : jobs) {
                    if (job.getId() == null) {//id是空的，表示新增工序，插入
                        jobService.insert(job);
                    } else {//不是空的，更新工序
                        jobService.updateJobByID(job);
                    }
                }
                //获取差集,为删除的
                List<Job> jobAll = new ArrayList<>();//合并数据库与前端
                jobAll.addAll(jobs);
                jobAll.addAll(productIdTemplte);

                List<Job> dels = jobAll.stream().filter(distinctByKey(b -> b.getId())).collect(Collectors.toList());

                for (Job b : dels) {
                    boolean bool = false;
                    if (b.getId() != null) {
                        for (Job j : jobs) {
                            if (b.getId() == j.getId()) {
                                bool = true;
                                break;
                            }
                        }
                        if (bool == false) {
                            b.setDelFlag(true);
                            jobService.updateJobByID(b);
                        }
                    }
                }
            }
        }
        return RestResponse.success();
    }

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}