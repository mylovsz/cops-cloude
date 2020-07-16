package com.cops.library.until;

import java.util.Calendar;

public class ScadaPlanUtils {

    /**
     * 获取Plan label的标签
     * @param planSn
     * @param productType
     * @return
     */
    public static String getPlanLable(String planSn, Integer productType){
        String result = "";
        Calendar calendar = Calendar.getInstance();
        switch (productType) {
            case 3: // "LA"
                break;
            case 1: // "LC"
                break;
            case 2: // "LD"
                result = String.format("L%s%s00001", planSn.substring(0, 2), planSn.substring(planSn.length()-5));
                break;
            case 4: // "LF"
            case 0: // "LT"
                result = String.format("H%s%s00001", planSn.substring(0, 2), planSn.substring(planSn.length()-5));
                break;
            case 5: // "LG"
                result = String.format("%s%s00001", planSn.substring(0, 2), planSn.substring(planSn.length()-5));
                break;
            case 6: // "LM"
                break;
            case 7: // "LP"
                break;
            case 8: // "LR"
                break;
            case 9: // "LS"
                break;
            case 10: // "半成品"
                break;
            default:
                break;
        }
        return result;
    }
}
