package com.cops.scada.controller.system;

import com.cops.scada.annotation.SysLog;
import com.cops.scada.entity.QuartzTask;
import com.cops.scada.service.QuartzTaskService;
import com.cops.scada.util.LayerData;
import com.cops.scada.util.RestResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务  前端控制器
 * </p>
 *
 * @author wangl
 * @since 2018-01-24
 */
@Controller
@RequestMapping("/admin/quartzTask")
public class QuartzTaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTaskController.class);


    @Autowired
    private QuartzTaskService quartzTaskService;

    @GetMapping("list")
    @SysLog("跳转定时任务列表")
    public String list(){
        return "/admin/quartzTask/list";
    }

    @RequiresPermissions("quartz:task:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<QuartzTask> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<QuartzTask> layerData = new LayerData<>();
        EntityWrapper<QuartzTask> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String name = (String) map.get("name");
            if(StringUtils.isNotBlank(name)) {
                wrapper.like("name",name);
            }else{
                map.remove("name");
            }

            String status = (String) map.get("status");
            if(StringUtils.isNotBlank(status)) {
                wrapper.eq("status",status);
            }else{
                map.remove("status");
            }

        }
        Page<QuartzTask> pageData = quartzTaskService.queryList(wrapper,new Page<>(page,limit));
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    public String add(){
        return "/admin/quartzTask/add";
    }

    @RequiresPermissions("quartz:task:add")
    @PostMapping("add")
    @SysLog("保存新增定时任务数据")
    @ResponseBody
    public RestResponse add(QuartzTask quartzTask){
        quartzTaskService.saveQuartzTask(quartzTask);
        return RestResponse.success();
    }

    @GetMapping("edit")
    public String edit(Long id,Model model){
        QuartzTask quartzTask = quartzTaskService.selectById(id);
        model.addAttribute("quartzTask",quartzTask);
        return "/admin/quartzTask/edit";
    }

    @RequiresPermissions("quartz:task:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑定时任务数据")
    public RestResponse edit(QuartzTask quartzTask){
        if(null == quartzTask.getId() || 0 == quartzTask.getId()){
            return RestResponse.failure("ID不能为空");
        }
        quartzTaskService.updateQuartzTask(quartzTask);
        return RestResponse.success();
    }

    @RequiresPermissions("quartz:task:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除定时任务数据")
    public RestResponse delete(@RequestParam(value = "ids[]",required = false)List<Long> ids){
        if(null == ids || 0 == ids.size()){
            return RestResponse.failure("ID不能为空");
        }
        quartzTaskService.deleteBatchTasks(ids);
        return RestResponse.success();
    }

    /**
     * 暂停选中的定时任务
     * @param ids 任务ID List
     * @return
     */
    @RequiresPermissions("quartz:task:paush")
    @PostMapping("paush")
    @ResponseBody
    public RestResponse paush(@RequestParam(value = "ids[]",required = false)List<Long> ids){
        if(null == ids || 0 == ids.size()){
            return RestResponse.failure("ID不能为空");
        }
        quartzTaskService.paush(ids);
        return RestResponse.success();
    }

    /**
     * 恢复选中的定时任务运行
     * @param ids 任务ID List
     * @return
     */
    @RequiresPermissions("quartz:task:resume")
    @PostMapping("resume")
    @ResponseBody
    public RestResponse resume(@RequestParam(value = "ids[]",required = false)List<Long> ids){
        if(null == ids || 0 == ids.size()){
            return RestResponse.failure("ID不能为空");
        }
        quartzTaskService.resume(ids);
        return RestResponse.success();
    }

    /**
     * 立即执行选中的定时任务
     * @param ids 任务ID List
     * @return
     */
    @RequiresPermissions("quartz:task:run")
    @PostMapping("run")
    @ResponseBody
    public RestResponse run(@RequestParam(value = "ids[]",required = false)List<Long> ids){
        if(null == ids || 0 == ids.size()){
            return RestResponse.failure("ID不能为空");
        }
        quartzTaskService.run(ids);
        return RestResponse.success();
    }

}