package com.cops.nc65.Controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.Bom;
import com.cops.entity.nc65.BomOnhand;
import com.cops.entity.nc65.BomQueryV;
import com.cops.entity.nc65.DTO.BomOnhandWrapperDTO;
import com.cops.entity.nc65.DTO.MaterialCodeWrapperDTO;
import com.cops.entity.nc65.DTO.WrapperDTO;
import com.cops.entity.nc65.SupplierPrice;
import com.cops.library.until.DataConvert;
import com.cops.library.until.LayerData;
import com.cops.library.until.NC65RestResponse;
import com.cops.nc65.service.BomService;
import com.cops.nc65.service.SupplierPriceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bom")
public class BomController {

    @Autowired
    private BomService bomService;

    @Autowired
    private SupplierPriceService supplierPriceService;

    public void BomOnhandAddMap(HashMap<String, BomOnhand> bomOnhandHashMap, List<BomOnhand> bomOnhandList){
        if(bomOnhandHashMap == null
            || bomOnhandList == null)
            return;

        for (BomOnhand bo :
                bomOnhandList) {
            BomOnhand boh = bomOnhandHashMap.get(bo.getBMaterialCode());
            if(boh == null)
                bomOnhandHashMap.put(bo.getBMaterialCode(), bo);
            else{
                boh.setNum(boh.getNum()+bo.getNum());
                bomOnhandHashMap.put(bo.getBMaterialCode(), boh);
            }
        }
    }

    @ApiOperation(value = "获取BOMALL信息", notes = "当前仅支持查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "BomOnhandWrapperDTO", name = "bomOnhandWrapperDTOList", value = "请求参数", required = true)
    })
    @PostMapping("bomOnhandAllItem")
    @ResponseBody
    public NC65RestResponse bomOnhandAllItem(@RequestBody BomOnhandWrapperDTO bomOnhandWrapperDTOList){

        if(bomOnhandWrapperDTOList == null
                || bomOnhandWrapperDTOList.getMaterialCodeWrapperDTO() == null
                || bomOnhandWrapperDTOList.getMaterialCodeWrapperDTO().size() == 0
                || bomOnhandWrapperDTOList.getStordocCodes() == null
                || bomOnhandWrapperDTOList.getStordocCodes().size() == 0)
            return NC65RestResponse.failure("传入的参数错误");

        NC65RestResponse nc65RestResponse = NC65RestResponse.success();
        HashMap<String, String> bomStatusMap = new HashMap<>();

        // 存放结果，不存在半成品
        HashMap<String, BomOnhand> resultOnhandListForItems = new HashMap<>();

        for (MaterialCodeWrapperDTO dto:
                bomOnhandWrapperDTOList.getMaterialCodeWrapperDTO()) {
            // 存放查询到的所有数据
            HashMap<String, List<BomOnhand>> bomOnhandMap = new HashMap<>();

            EntityWrapper<Bom> bomWrapper = new EntityWrapper<>();
            bomWrapper.eq("bb_bom.hbdefault", "Y");
            bomWrapper.eq("bb_bom.dr", 0);
            bomWrapper.eq("bm_material.dr", 0);
            bomWrapper.eq("bm_material.code", dto.getMaterialCode());
            // 查询到所有的BOM
            List<Bom> bomList = bomService.getBom(bomWrapper);
            if(bomList == null || bomList.size() == 0){
                bomStatusMap.put(dto.getMaterialCode(), "不存在");
                continue;
            }
            bomStatusMap.put(dto.getMaterialCode(), "存在");
            Bom bom = bomList.get(0);
            // 查询实际物料（第一级）
            EntityWrapper<BomOnhand> bomOnhandEntityWrapper = new EntityWrapper<>();
            bomOnhandEntityWrapper.eq("material_id", bom.getMaterialId());
            bomOnhandEntityWrapper.andNew().in("stor_code", bomOnhandWrapperDTOList.getStordocCodes()).or().isNull("stor_code");
            List<BomOnhand> bomOnhandList = bomService.getBomOnhand(bomOnhandEntityWrapper, dto.getNum());

            bomOnhandMap.put(bom.getMaterialCode(), bomOnhandList); // 将子项（第一级）存入返回数据中
            BomOnhandAddMap(resultOnhandListForItems, bomOnhandList);

            if(bomOnhandWrapperDTOList.isAllTtem()){
                continue;
            }

            // 查询带有子项的物料集合
            List<BomQueryV> bomQueryVList = bomService.getBomQueryV(bom.getMaterialId());
            // 循环遍历当前项
            if(bomQueryVList != null) {
                for (BomQueryV bqv :bomQueryVList
                ) {
                    BomOnhand boh = resultOnhandListForItems.remove(bqv.getCode());

                    bomOnhandEntityWrapper = new EntityWrapper<>();
                    bomOnhandEntityWrapper.eq("material_code", bqv.getCode());
                    bomOnhandEntityWrapper.andNew().in("stor_code", bomOnhandWrapperDTOList.getStordocCodes()).or().isNull("stor_code");
                    if(boh != null)
                        bomOnhandList = bomService.getBomOnhand(bomOnhandEntityWrapper, boh.getNum());
                    else
                        bomOnhandList = bomService.getBomOnhand(bomOnhandEntityWrapper, bqv.getNitemnum()*dto.getNum());

                    bomOnhandMap.put(bqv.getCode(), bomOnhandList); // 将子项（第一级）存入返回数据中
                    BomOnhandAddMap(resultOnhandListForItems, bomOnhandList);
                }
            }
        }

        List<BomOnhand> listReuslts = new ArrayList<>();
        for(BomOnhand b: resultOnhandListForItems.values())
            listReuslts.add(b);

        // 报价查询
        if(bomOnhandWrapperDTOList.isPrice()){
            for (BomOnhand b :
                    listReuslts) {
                b.setSupplierPrice(supplierPriceService.getSupplierPrice(b.getBMaterialCode()));
            }
        }

        nc65RestResponse.setData(listReuslts);

        nc65RestResponse.setAny("bomResultState", bomStatusMap);

        return nc65RestResponse;
    }

//    @ApiOperation(value = "获取BOMALL信息", notes = "当前仅支持《materialCode》《materialId》《bomDefault 不填写，默认:Y》查询")
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "WrapperDTO", name = "wrapperDTO", value = "请求参数，wrapper为bom", required = true)
//    })
//    @PostMapping("bomonhandall")
//    @ResponseBody
//    public NC65RestResponse bomonhandall(@RequestBody WrapperDTO wrapperDTO){
//        Bom bom = (Bom) DataConvert.mapToObject((Map<String, Object>) wrapperDTO.getWrapper(), Bom.class);
//        Integer page = wrapperDTO.getPage();
//        Integer limit = wrapperDTO.getLimit();
//        EntityWrapper<Bom> bomWrapper = new EntityWrapper<>();
//        if(bom != null){
//            if(StringUtils.isNotBlank(bom.getMaterialCode())){
//                // 物料Code查询
//                bomWrapper.eq("bm_material.code", bom.getMaterialCode());
//            }
//            if(StringUtils.isNotBlank(bom.getMaterialId())){
//                // 物料主键查询
//                bomWrapper.eq("bm_material.pk_material", bom.getMaterialId());
//            }
//            if(StringUtils.isNotBlank(bom.getBomDefault())){
//                // 默认bom
//                bomWrapper.eq("bb_bom.hbdefault", bom.getBomDefault());
//            }
//            else
//            {
//                // 默认bom
//                bomWrapper.eq("bb_bom.hbdefault", "Y");
//            }
//        }
//        List<Bom> bomList = bomService.getBom(bomWrapper);
//        if(bomList == null || bomList.size() == 0)
//            return NC65RestResponse.failure("不存在改产品的BOM");
//
//        // 获取第一个产品的BOM信息到bom中
//        NC65RestResponse nc65RestResponse = NC65RestResponse.success();
//        bom = bomList.get(0);
//        nc65RestResponse.setAny("bom", bom); // 将BOM信息放到返回数据中
//        // 查询带有子项的物料集合
//        List<BomQueryV> bomQueryVList = bomService.getBomQueryV(bom.getMaterialId());
//        nc65RestResponse.setAny("bomQueryV", bomQueryVList); // 将子项存入返回数据中
//        // 查询实际物料（第一级）
//        EntityWrapper<BomOnhand> bomOnhandEntityWrapper = new EntityWrapper<>();
//        bomOnhandEntityWrapper.eq("material_id", bom.getMaterialId());
//        List<BomOnhand> bomOnhandList = bomService.getBomOnhand(bomOnhandEntityWrapper);
//        nc65RestResponse.setAny(bom.getMaterialCode(), bomOnhandList); // 将子项（第一级）存入返回数据中
//        // 循环遍历当前项
//        if(bomQueryVList != null) {
//            for (BomQueryV bqv :bomQueryVList
//            ) {
//                bomOnhandEntityWrapper = new EntityWrapper<>();
//                bomOnhandEntityWrapper.eq("material_code", bqv.getCode());
//                bomOnhandList = bomService.getBomOnhand(bomOnhandEntityWrapper);
//                nc65RestResponse.setAny(bqv.getCode(), bomOnhandList); // 将子项（第一级）存入返回数据中
//            }
//        }
//
//        return nc65RestResponse;
//    }

    @ApiOperation(value = "获取BOM信息", notes = "当前仅支持《》《》《》查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "WrapperDTO", name = "wrapperDTO", value = "请求参数", required = true)
    })
    @PostMapping("bomonhand")
    @ResponseBody
    public NC65RestResponse bomonhand(@RequestBody WrapperDTO wrapperDTO){
        BomOnhand bomOnhand = (BomOnhand) DataConvert.mapToObject((Map<String, Object>) wrapperDTO.getWrapper(), BomOnhand.class);
        Integer page = wrapperDTO.getPage();
        Integer limit = wrapperDTO.getLimit();
        EntityWrapper<BomOnhand> wrapper = new EntityWrapper<>();
        if(bomOnhand != null){
            if(StringUtils.isNotBlank(bomOnhand.getMaterialCode())){
                // 生产订单号
                wrapper.eq("material_code", bomOnhand.getMaterialCode());
            }
            if(StringUtils.isNotBlank(bomOnhand.getMaterialId())){
                // 生产订单号
                wrapper.like("material_id", bomOnhand.getMaterialId());
            }
        }
        Page<BomOnhand> bomOnhandPage = bomService.getBomOnhandPage(new Page(page, limit), wrapper);

        LayerData<BomOnhand> layerData = new LayerData<>();
        layerData.setData(bomOnhandPage.getRecords());
        layerData.setCount(bomOnhandPage.getTotal());

        return NC65RestResponse.success().setData(layerData);
    }
}
