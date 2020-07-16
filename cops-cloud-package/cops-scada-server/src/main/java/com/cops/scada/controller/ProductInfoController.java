package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.library.until.ScadaMapUtils;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.entity.ProductInfo;
import com.cops.scada.entity.ProductInfoAttrValue;
import com.cops.scada.entity.Series;
import com.cops.scada.entity.SeriesAttr;
import com.cops.scada.entity.VO.ProductInfoVO;
import com.cops.scada.entity.VO.SeriesAttrAndValueVO;
import com.cops.scada.service.ProductInfoAttrValueService;
import com.cops.scada.service.ProductInfoService;
import com.cops.scada.service.SeriesAttrService;
import com.cops.scada.service.SeriesService;
import com.cops.scada.util.LayerData;
import com.cops.scada.util.RestResponse;
import com.xiaoleilu.hutool.map.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.math.raw.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 展示产品  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@Controller
@RequestMapping("/productInfo")
public class ProductInfoController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoController.class);

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private ProductInfoAttrValueService productInfoAttrValueService;

    @Autowired
    private SeriesAttrService seriesAttrService;

    @GetMapping("index")
    public String showIndex(Model model){

        return "/productInfo/index";
    }

    @GetMapping("showOne")
    public String showOne(Model model, String id){
        EntityWrapper<ProductInfoVO> wrapper = new EntityWrapper<>();
        wrapper.eq("product_info.id", id);
        wrapper.eq("product_info.del_flag", false);
        Page<ProductInfoVO> pageData = productInfoService.getProductInfoVO(new Page<>(1, 1), wrapper);
        List<ProductInfoVO> list = pageData.getRecords();
        if(list != null && list.size()>0){
            for (ProductInfoVO p :
                    list) {
                p.setProductInfoAttrAndValueVOList(productInfoAttrValueService.listByProductInfoId(p.getId()));
            }

            model.addAttribute("productInfoVO", list.get(0));
        }

        return "/productInfo/showOne";
    }

    /**
     * 展示页
     * @param model
     * @param typeValue
     * @return
     */
    @GetMapping("showLed")
    public String showLed(Model model,
                          @RequestParam(value = "typeValue",defaultValue = "1")Integer typeValue,
                          @RequestParam(value = "series", defaultValue = "") String series) {

        model.addAttribute("seriesTypeValue", typeValue);

        // 查找新品
        EntityWrapper<ProductInfoVO> wrapper = new EntityWrapper<>();
        wrapper.eq("series.type", typeValue);
        wrapper.eq("product_info.state", 1);
        wrapper.eq("product_info.del_flag", false);
        wrapper.orderBy("product_info.update_date", false);
        Page<ProductInfoVO> pageData = productInfoService.getProductInfoVO(new Page<>(1, 4), wrapper);
        List<ProductInfoVO> list = pageData.getRecords();
        if(list != null && list.size()>0){
            for (ProductInfoVO p :
                    list) {
                p.setProductInfoAttrAndValueVOList(productInfoAttrValueService.listByProductInfoId(p.getId()));
            }
        }
        model.addAttribute("newProduct", list);

        // 系列参数
        List<Series> seriesList = seriesService.list(typeValue);
        model.addAttribute("seriesList", seriesList);

        Long seriesSelectId = 0L;
        if(StringUtils.isBlank(series)){
            // 说明没有选择，此时默认选择第一个系列
            if(seriesList != null && seriesList.size() > 0){
                seriesSelectId = seriesList.get(0).getId();
            }
        } else {
            seriesSelectId = Long.parseLong(series);
        }
        model.addAttribute("seriesSelectId", seriesSelectId);

        // 动态条件获取
        List<SeriesAttrAndValueVO> seriesAttrAndValueVOList = null;
        List<SeriesAttr> seriesAttrList = seriesAttrService.listForSearchBySeriesId(seriesSelectId);
        if(seriesAttrList != null && seriesAttrList.size()>0){
            seriesAttrAndValueVOList = new ArrayList<>();
            for (SeriesAttr sa :
                    seriesAttrList) {
                SeriesAttrAndValueVO seriesAttrAndValueVO = new SeriesAttrAndValueVO();
                seriesAttrAndValueVO.setSeriesAttr(sa);
                seriesAttrAndValueVO.setCondition(productInfoAttrValueService.listForConditionBySeriesAttrId(sa.getId(), seriesSelectId));
                seriesAttrAndValueVOList.add(seriesAttrAndValueVO);
            }
        }
        model.addAttribute("seriesAttrAndValueVOList", seriesAttrAndValueVOList);

        return "/productInfo/showLed";
    }

    /**
     * 展示页
     * @param model
     * @param typeValue
     * @return
     */
    @GetMapping("show")
    public String show(Model model,
                       @RequestParam(value = "typeValue",defaultValue = "1")Integer typeValue,
                       @RequestParam(value = "series", defaultValue = "") String series) {

        model.addAttribute("seriesTypeValue", typeValue);

        // 查找新品
        EntityWrapper<ProductInfoVO> wrapper = new EntityWrapper<>();
        wrapper.eq("series.type", typeValue);
        wrapper.eq("product_info.state", 1);
        wrapper.eq("product_info.del_flag", false);
        wrapper.orderBy("product_info.update_date", false);
        Page<ProductInfoVO> pageData = productInfoService.getProductInfoVO(new Page<>(1, 6), wrapper);
        List<ProductInfoVO> list = pageData.getRecords();
        if(list != null && list.size()>0){
            for (ProductInfoVO p :
                    list) {
                p.setProductInfoAttrAndValueVOList(productInfoAttrValueService.listByProductInfoId(p.getId()));
            }
        }
        model.addAttribute("newProduct", list);

        // 系列参数
        List<Series> seriesList = seriesService.list(typeValue);
        model.addAttribute("seriesList", seriesList);

        Long seriesSelectId = 0L;
        if(StringUtils.isBlank(series)){
            // 说明没有选择，此时默认选择第一个系列
            if(seriesList != null && seriesList.size() > 0){
                seriesSelectId = seriesList.get(0).getId();
            }
        } else {
            seriesSelectId = Long.parseLong(series);
        }
        model.addAttribute("seriesSelectId", seriesSelectId);

        // 动态条件获取
        List<SeriesAttrAndValueVO> seriesAttrAndValueVOList = null;
        List<SeriesAttr> seriesAttrList = seriesAttrService.listForSearchBySeriesId(seriesSelectId);
        if(seriesAttrList != null && seriesAttrList.size()>0){
            seriesAttrAndValueVOList = new ArrayList<>();
            for (SeriesAttr sa :
                    seriesAttrList) {
                SeriesAttrAndValueVO seriesAttrAndValueVO = new SeriesAttrAndValueVO();
                seriesAttrAndValueVO.setSeriesAttr(sa);
                seriesAttrAndValueVO.setCondition(productInfoAttrValueService.listForConditionBySeriesAttrId(sa.getId(), seriesSelectId));
                seriesAttrAndValueVOList.add(seriesAttrAndValueVO);
            }
        }
        model.addAttribute("seriesAttrAndValueVOList", seriesAttrAndValueVOList);

        return "/productInfo/show";
    }

//    /**
//     * 展示页
//     * @param model
//     * @param
//     * @return
//     */
//    @GetMapping("show")
//    public String show(Model model, @RequestParam(value = "type") String type) {
////        EntityWrapper wrapper = new EntityWrapper();
////        wrapper.ne("del_flag", "1");
////        wrapper.eq("type", type);
////        wrapper.orderBy("sort");
////        //获取当前类型的系列
////        List<Series> series = seriesService.selectList(wrapper);
////        List<SeriesAttr> seriesAttrList = new ArrayList<>();
////        for (Series seriesModel : series) {
////            List<SeriesAttr> seriesAttrs = seriesAttrService.selectBySeriesId(seriesModel.getId());
////            if (seriesAttrs.size() > 0) {
////                seriesAttrList.addAll(seriesAttrs);
////            }
////        }
////        //类型关联属性
////        if (seriesAttrList.size() > 0) {
////            seriesAttrList = seriesAttrList.stream().distinct().collect(Collectors.toList());
////        }
////        List<JSONObject> jsonObjects = new ArrayList<>();
////        for (SeriesAttr seriesAttr : seriesAttrList) {
////            if (seriesAttr.getIsSearch().equals("0")){
////                continue;
////            }
////            EntityWrapper AttrWrapper = new EntityWrapper();
////            if (seriesAttr.getValueType().equals("0")){
////                AttrWrapper.setSqlSelect("value");
////                AttrWrapper.eq("series_attr_id",seriesAttr.getId());
////                AttrWrapper.groupBy("value");
////                Object obj = productInfoAttrValueService.selectObj(AttrWrapper);
////                JSONObject jsonObject = new JSONObject();
////                jsonObject.put("values",obj);
////                jsonObject.put("model",seriesAttr);
////
////                jsonObjects.add(jsonObject);
////            }else if (seriesAttr.getValueType().equals("1")){
////
////            }
////        }
////
////        model.addAttribute("series", series);
////        model.addAttribute("seriesAttrList", seriesAttrList);
//        // 系列参数
//        List<Series> seriesList = seriesService.list(1);
//        model.addAttribute("seriesList", seriesList);
//
//        // 动态条件获取
//        List<SeriesAttrAndValueVO> seriesAttrAndValueVOList = null;
//        List<SeriesAttr> seriesAttrList = seriesAttrService.listForSearchByType(1);
//        if(seriesAttrList.size()>0){
//            seriesAttrAndValueVOList = new ArrayList<>();
//            for (SeriesAttr sa :
//                    seriesAttrList) {
//                SeriesAttrAndValueVO seriesAttrAndValueVO = new SeriesAttrAndValueVO();
//                seriesAttrAndValueVO.setSeriesAttr(sa);
//                seriesAttrAndValueVO.setCondition(productInfoAttrValueService.listForConditionBySeriesAttrId(sa.getId()));
//                seriesAttrAndValueVOList.add(seriesAttrAndValueVO);
//            }
//        }
//        model.addAttribute("seriesAttrAndValueVOList", seriesAttrAndValueVOList);
//
//        return "/productInfo/show";
//    }

    @GetMapping("list")
    @SysLog("跳转展示产品列表")
    public String list(Model model,
                       @RequestParam(value = "typeValue",defaultValue = "1")Integer typeValue,
                       @RequestParam(value = "series", defaultValue = "") String series) {

        model.addAttribute("seriesTypeValue", typeValue);

        // 系列参数
        List<Series> seriesList = seriesService.list(typeValue);
        model.addAttribute("seriesList", seriesList);

        Long seriesSelectId = 0L;
        if(StringUtils.isBlank(series)){
            // 说明没有选择，此时默认选择第一个系列
            if(seriesList != null && seriesList.size() > 0){
                seriesSelectId = seriesList.get(0).getId();
            }
        } else {
            seriesSelectId = Long.parseLong(series);
        }
        model.addAttribute("seriesSelectId", seriesSelectId);

        // 动态条件获取
        List<SeriesAttrAndValueVO> seriesAttrAndValueVOList = null;
        List<SeriesAttr> seriesAttrList = seriesAttrService.listForSearchBySeriesId(seriesSelectId);
        if(seriesAttrList.size()>0){
            seriesAttrAndValueVOList = new ArrayList<>();
            for (SeriesAttr sa :
                    seriesAttrList) {
                SeriesAttrAndValueVO seriesAttrAndValueVO = new SeriesAttrAndValueVO();
                seriesAttrAndValueVO.setSeriesAttr(sa);
                seriesAttrAndValueVO.setCondition(productInfoAttrValueService.listForConditionBySeriesAttrId(sa.getId(), seriesSelectId));
                seriesAttrAndValueVOList.add(seriesAttrAndValueVO);
            }
        }
        model.addAttribute("seriesAttrAndValueVOList", seriesAttrAndValueVOList);

        return "/productInfo/list";
    }

    @PostMapping("listHeader")
    @ResponseBody
    @SysLog("请求展示产品列表数据头")
    public List<SeriesAttr> listHeader(@RequestParam(value = "seriesId", defaultValue = "") String seriesId) {
        // 系列参数
        Long seriesSelectId = 0L;
        if(StringUtils.isBlank(seriesId)){
            // 说明没有选择，此时默认选择第一个系列
            return null;
        } else {
            seriesSelectId = Long.parseLong(seriesId);
        }
        List<SeriesAttr> result = seriesAttrService.listBySeriesId(seriesSelectId);
        return result;
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求展示产品列表数据")
    public LayerData<ProductInfoVO> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ProductInfoVO> layerData = new LayerData<>();
        EntityWrapper<ProductInfoVO> wrapper = new EntityWrapper<>();
        wrapper.eq("product_info.del_flag", false);
        wrapper.orderBy("product_info.name");
        boolean isAll = true;
        if (!map.isEmpty()) {

            // 1. 系列，多选
            List<Long> seriesIds = null;
            Map<String, String> seriesNameList = ScadaMapUtils.MapLike(map, "seriesNameList");
            if(seriesNameList.size()>0) {
                seriesIds = new ArrayList<>();
                for (Map.Entry<String, String> c :
                        seriesNameList.entrySet()) {
                    seriesIds.add(Long.parseLong(c.getValue()));
                }
                if (seriesIds != null) {
                    if (seriesIds.size() > 0) {
                        wrapper.in("series.id", seriesIds);
                    }
                }
            }
            // end 系列，多选

            // 2. 动态条件查询-多选
            // HashSet<Long> nameIds = new HashSet<>();
            // 注释以上内容，采用交集
            List<Long> nameListIds = null;
            Map<String, String> conditionList = ScadaMapUtils.MapLike(map, "muchSearch");
            if(conditionList.size()>0) {
                List<Long>  nameListTemp = null;
                Map<String, List<Long>> nameListAllIds = MapUtil.newHashMap();

                for (Map.Entry<String, String> c :
                        conditionList.entrySet()) {

                    String[] conditions = c.getValue().split("`");
                    String strName = c.getKey().split("-")[0];

                    if (conditions.length == 5) {
                        nameListTemp = productInfoAttrValueService.listForNameId(Long.parseLong(conditions[1]),
                                conditions[3], conditions[4]);
                    } else if (conditions.length == 4) {
                        nameListTemp = productInfoAttrValueService.listForNameId(Long.parseLong(conditions[1]),
                                conditions[3], null);
                    } else {
                        nameListTemp = null;
                    }

                    if (nameListTemp != null) {
                        if(nameListAllIds.containsKey(strName)){
                            // 说明存在了，要进行并集
                            List<Long> l = nameListAllIds.get(strName);
                            l.removeAll(nameListTemp);
                            l.addAll(nameListTemp);
                            nameListAllIds.put(strName, l);
                        } else {
                            nameListAllIds.put(strName, nameListTemp);
                        }
                    }
                }

                for (Map.Entry<String, List<Long>> m :
                        nameListAllIds.entrySet()){
                    if(nameListIds == null){
                        nameListIds = m.getValue();
                    } else {
                        nameListIds.retainAll(m.getValue());
                    }
                }
                if (nameListIds != null && nameListIds.size() > 0) {
                    wrapper.in("product_info.id", nameListIds);
                } else {
                    wrapper.eq("1", 0);
                }
            }
            // end 动态条件查询

            String seriesType = (String) map.get("seriesType");
            if (StringUtils.isNotBlank(seriesType)) {
                wrapper.like("series.type", seriesType);
            } else {
                map.remove("seriesType");
            }

            String seriesName = (String) map.get("seriesName");
            if (StringUtils.isNotBlank(seriesName)) {
                wrapper.like("series.name", seriesName);
            } else {
                map.remove("seriesName");
            }

            String seriesId = (String) map.get("seriesId");
            if (StringUtils.isNotBlank(seriesId)) {
                wrapper.like("series.id", seriesId);
            } else {
                map.remove("seriesId");
            }

            String name = (String) map.get("name");
            if (StringUtils.isNotBlank(name)) {
                wrapper.like("product_info.name", name);
            } else {
                map.remove("name");
            }

            String nameEn = (String) map.get("nameEn");
            if (StringUtils.isNotBlank(nameEn)) {
                wrapper.like("product_info.name_en", nameEn);
            } else {
                map.remove("nameEn");
            }

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("product_info.state",state);
            }else{
                map.remove("state");
            }

            // 动态条件查询
            // HashSet<Long> nameIds = new HashSet<>();
            // 注释以上内容，采用交集
            List<Long> nameIds = null, nameTemp = null;
            Map<String, String> condition = ScadaMapUtils.MapLike(map, "search");
            if(condition.size()>0) {
                for (Map.Entry<String, String> c :
                        condition.entrySet()) {
                    String[] conditions = c.getValue().split("`");
                    if (conditions.length == 5) {
                        nameTemp = productInfoAttrValueService.listForNameId(Long.parseLong(conditions[1]),
                                conditions[3], conditions[4]);
                    } else if (conditions.length == 4) {
                        nameTemp = productInfoAttrValueService.listForNameId(Long.parseLong(conditions[1]),
                                conditions[3], null);
                    } else {
                        nameTemp = null;
                    }

                    if (nameTemp != null) {
                        if (nameIds == null) {
                            nameIds = nameTemp;
                        } else {
                            nameIds.retainAll(nameTemp);
                        }
                    }
                }
                if (nameIds != null) {
                    if (nameIds.size() > 0) {
                        wrapper.in("product_info.id", nameIds);
                    } else {
                        wrapper.eq("1", 0);
                    }
                }
            }
            // end 动态条件查询
        }
        Page<ProductInfoVO> pageData = productInfoService.getProductInfoVO(new Page<>(page, limit), wrapper);
        if(isAll){
            List<ProductInfoVO> list = pageData.getRecords();
            if(list != null && list.size()>0){
                for (ProductInfoVO p :
                        list) {
                    p.setProductInfoAttrAndValueVOList(productInfoAttrValueService.listByProductInfoId(p.getId()));
                }
            }
            layerData.setData(list);
        } else {
            layerData.setData(pageData.getRecords());
        }
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增展示产品页面")
    public String add(Model model, @RequestParam(value = "typeValue",defaultValue = "1")Integer typeValue) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("del_flag", false);
        entityWrapper.eq("type", typeValue);
        entityWrapper.orderBy("sort");
        List<Series> series = seriesService.selectList(entityWrapper);
        model.addAttribute("series", series);
        return "/productInfo/add";
    }

    @PostMapping("add")
    @SysLog("保存新增展示产品数据")
    @ResponseBody
    public RestResponse add(ProductInfo productInfo) {
        if (productInfo.getSeriesId() == null) {
            return RestResponse.failure("系列不能为空");
        }

        productInfo.setIsUsed(1);
        productInfo.setNameEn(productInfo.getName());

        if (productInfoService.insert(productInfo)) {
            List<SeriesAttr> seriesAttrs = seriesAttrService.selectBySeriesId(productInfo.getSeriesId());
            List<ProductInfoAttrValue> productInfoAttrValues = new ArrayList<>();
            for (SeriesAttr seriesAttr : seriesAttrs) {
                ProductInfoAttrValue productInfoAttrValue = new ProductInfoAttrValue();
                productInfoAttrValue.setProductInfoId(productInfo.getId());
                productInfoAttrValue.setSeriesAttrId(seriesAttr.getId());
                productInfoAttrValues.add(productInfoAttrValue);
            }
            if (productInfoAttrValues.size() > 0) {
                productInfoAttrValueService.insertBatch(productInfoAttrValues);
            }
        }
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑展示产品页面")
    public String edit(Long id,
                        @RequestParam(value = "typeValue",defaultValue = "1")Integer typeValue,
                        Model model) {
        ProductInfo productInfo = productInfoService.selectById(id);
        model.addAttribute("productInfo", productInfo);
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("del_flag", false);
        entityWrapper.eq("type", typeValue);
        entityWrapper.orderBy("sort");
        List<Series> series = seriesService.selectList(entityWrapper);
        model.addAttribute("series", series);
        return "/productInfo/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑展示产品数据")
    public RestResponse edit(ProductInfo productInfo) {
        if (null == productInfo.getId() || 0 == productInfo.getId()) {
            return RestResponse.failure("ID不能为空");
        }
        if (productInfo.getSeriesId() == null) {
            return RestResponse.failure("系列id不能为空");
        }

        productInfo.setIsUsed(1);
        productInfo.setNameEn(productInfo.getName());

        if (productInfoService.updateById(productInfo)) {
            List<SeriesAttr> seriesAttrs = seriesAttrService.selectBySeriesId(productInfo.getSeriesId());
            List<ProductInfoAttrValue> productInfoAttrValues = new ArrayList<>();
            for (SeriesAttr seriesAttr : seriesAttrs) {
                ProductInfoAttrValue productInfoAttrValue = new ProductInfoAttrValue();
                productInfoAttrValue.setProductInfoId(productInfo.getId());
                productInfoAttrValue.setSeriesAttrId(seriesAttr.getId());
                if (productInfoAttrValueService.getProductInfoAttrByProductInfoIdAndAttrId(productInfo.getId(), seriesAttr.getId()) == null) {
                    productInfoAttrValues.add(productInfoAttrValue);
                }
            }
            if (productInfoAttrValues.size() > 0) {
                productInfoAttrValueService.insertBatch(productInfoAttrValues);
            }
        }

        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除展示产品数据")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (null == id || 0 == id) {
            return RestResponse.failure("ID不能为空");
        }
        ProductInfo productInfo = productInfoService.selectById(id);
        productInfo.setDelFlag(true);
        productInfoService.updateById(productInfo);
        return RestResponse.success();
    }

}