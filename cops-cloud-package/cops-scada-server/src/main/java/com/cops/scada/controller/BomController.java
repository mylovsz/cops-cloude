package com.cops.scada.controller;

import com.cops.entity.nc65.BomOnhand;
import com.cops.entity.nc65.DTO.BomOnhandWrapperDTO;
import com.cops.entity.nc65.DTO.MaterialCodeWrapperDTO;
import com.cops.entity.nc65.SupplierPrice;
import com.cops.entity.scada.VO.BomOnhandWrapperVO;
import com.cops.entity.scada.VO.MaterialWrapperVO;
import com.cops.library.until.DataConvert;
import com.cops.library.until.NC65RestResponse;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.service.NC65BomService;
import com.cops.scada.util.LayerData;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bom")
public class BomController extends BaseController {

    @Autowired
    NC65BomService nc65BomService;

    @GetMapping("onhand")
    @SysLog("BOM现存量查询")
    public String onhand(){
        return "/bom/onhand";
    }

    @RequiresPermissions("scada:bom:onhandPrice")
    @PostMapping("onhandPrice")
    @ResponseBody
    @SysLog("BOM现存量查询")
    public LayerData<BomOnhand> onhandPrice(@RequestBody BomOnhandWrapperVO bomOnhandWrapperVO){

        LayerData<BomOnhand> layerData = new LayerData<>();

        if(bomOnhandWrapperVO == null) {
            layerData.setMsg("参数错误");
            return layerData;
        }

        if(bomOnhandWrapperVO.getSearchState() == false) {
            layerData.setMsg("");
            return layerData;
        }

        BomOnhandWrapperDTO bomOnhandWrapperDTO = new BomOnhandWrapperDTO();
        bomOnhandWrapperDTO.setAllTtem(bomOnhandWrapperVO.getIsAllItem());
        bomOnhandWrapperDTO.setPrice(bomOnhandWrapperVO.getIsPrice());
        bomOnhandWrapperDTO.setMaterialCodeWrapperDTO(bomOnhandWrapperVO.getMaterialCodeWrapperDTOS());
        bomOnhandWrapperDTO.setStordocCodes(bomOnhandWrapperVO.getStordocs());

        NC65RestResponse nc65RestResponse = nc65BomService.getBomOnhandAllItem(bomOnhandWrapperDTO);
        List<BomOnhand> bomOnhandList = (List<BomOnhand>)nc65RestResponse.getData();

        // 排序，并计算短缺，实际需求量
        List<BomOnhand> bomOnhandListReuslt = new ArrayList<>();
        for(int i =0;i<bomOnhandList.size();i++){
            BomOnhand b = (BomOnhand)DataConvert.mapToObject((Map<String, Object>)bomOnhandList.get(i), BomOnhand.class);
            b.setNu(i+1);

            if(b.getBaseNum() == null || b.getBaseNum() == 0)
                b.setRequireNum(b.getNum());
            else {
                if(b.getNum() == null)
                    b.setRequireNum(b.getNum());
                else
                    b.setRequireNum(b.getNum() / b.getBaseNum());
            }
            if(b.getHandNum() == null)
                b.setShortage(b.getRequireNum());
            else {
                if(b.getRequireNum() == null)
                    b.setShortage(b.getRequireNum());
                else
                    b.setShortage(b.getRequireNum() - b.getHandNum());
            }

            // 计算价格
            b.setOrigPrice(b.getRequireNum() * b.getOrigPrice());
            b.setOrigTaxPrice(b.getRequireNum() * b.getOrigTaxPrice());
            // end 计算价格

            bomOnhandListReuslt.add(b);
        }
        // end

        layerData.setCount(bomOnhandListReuslt.size());
        layerData.setData(bomOnhandListReuslt);
        String msg = "";
        HashMap<String, String> bomResultState = (HashMap<String, String>)nc65RestResponse.get("bomResultState");
        for (Map.Entry<String, String> entry :
                bomResultState.entrySet()) {
            msg += "【"+ entry.getKey() + "】BOM状态："+entry.getValue()+"<br/>";
        }
        layerData.setMsg(msg);
        return layerData;
    }

    @PostMapping("onhand")
    @ResponseBody
    @SysLog("BOM现存量查询")
    public LayerData<BomOnhand> onhand(@RequestBody BomOnhandWrapperVO bomOnhandWrapperVO){

        LayerData<BomOnhand> layerData = new LayerData<>();

        if(bomOnhandWrapperVO == null) {
            layerData.setMsg("参数错误");
            return layerData;
        }

        if(bomOnhandWrapperVO.getSearchState() == false) {
            layerData.setMsg("");
            return layerData;
        }

        BomOnhandWrapperDTO bomOnhandWrapperDTO = new BomOnhandWrapperDTO();
        bomOnhandWrapperDTO.setAllTtem(bomOnhandWrapperVO.getIsAllItem());
        bomOnhandWrapperDTO.setPrice(bomOnhandWrapperVO.getIsPrice());
        bomOnhandWrapperDTO.setMaterialCodeWrapperDTO(bomOnhandWrapperVO.getMaterialCodeWrapperDTOS());
        bomOnhandWrapperDTO.setStordocCodes(bomOnhandWrapperVO.getStordocs());

        NC65RestResponse nc65RestResponse = nc65BomService.getBomOnhandAllItem(bomOnhandWrapperDTO);
        List<BomOnhand> bomOnhandList = (List<BomOnhand>)nc65RestResponse.getData();

        // 排序，并计算短缺，实际需求量
        List<BomOnhand> bomOnhandListReuslt = new ArrayList<>();
        for(int i =0;i<bomOnhandList.size();i++){
            BomOnhand b = (BomOnhand)DataConvert.mapToObject((Map<String, Object>)bomOnhandList.get(i), BomOnhand.class);
            b.setNu(i+1);

            if(b.getBaseNum() == null || b.getBaseNum() == 0)
                b.setRequireNum(b.getNum());
            else {
                if(b.getNum() == null)
                    b.setRequireNum(b.getNum());
                else
                    b.setRequireNum(b.getNum() / b.getBaseNum());
            }
            if(b.getHandNum() == null)
                b.setShortage(b.getRequireNum());
            else {
                if(b.getRequireNum() == null)
                    b.setShortage(b.getRequireNum());
                else
                    b.setShortage(b.getRequireNum() - b.getHandNum());
            }
            bomOnhandListReuslt.add(b);
        }
        // end

        layerData.setCount(bomOnhandListReuslt.size());
        layerData.setData(bomOnhandListReuslt);
        String msg = "";
        HashMap<String, String> bomResultState = (HashMap<String, String>)nc65RestResponse.get("bomResultState");
        for (Map.Entry<String, String> entry :
                bomResultState.entrySet()) {
            msg += "【"+ entry.getKey() + "】BOM状态："+entry.getValue()+"<br/>";
        }
        layerData.setMsg(msg);
        return layerData;
    }

    @PostMapping("materialWrapper")
    @ResponseBody
    @SysLog("BOM现存量查询条件")
    public LayerData<MaterialWrapperVO> materialWrapper(Integer num){

        LayerData<MaterialWrapperVO> layerData = new LayerData<>();

        List<MaterialWrapperVO> materialWrapperVOList = new ArrayList<>();
        for(int i=0;i<num;i++){
            MaterialWrapperVO materialWrapperVO = new MaterialWrapperVO();
            materialWrapperVO.setId(i+1);
            materialWrapperVO.setMaterialCode("");
            materialWrapperVO.setNum(null);
            materialWrapperVOList.add(materialWrapperVO);
        }

        layerData.setCount(materialWrapperVOList.size());
        layerData.setData(materialWrapperVOList);
        return layerData;
    }
}
