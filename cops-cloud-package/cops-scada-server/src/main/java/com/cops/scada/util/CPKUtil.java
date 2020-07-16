package com.cops.scada.util;

import com.cops.scada.entity.VO.ResponseCPK;

public class CPKUtil {

    public static Double StDev(Double[] arrData) //计算标准偏差
    {
        Double xSum = 0.0;
        Double xAvg = 0.0;
        Double sSum = 0.0;
        int arrNum = arrData.length;
        for (int i = 0; i < arrNum; i++) {
            xSum += arrData[i];
        }
        xAvg = xSum / arrNum;
        for (int j = 0; j < arrNum; j++) {
            sSum += ((arrData[j] - xAvg) * (arrData[j] - xAvg));
        }
        Double temp = Math.sqrt((sSum / (arrNum - 1)));
        System.out.println("计算标准偏差:"+temp);
        return temp;
    }

    public static Double Cp(Double UpperLimit, Double LowerLimit, Double StDev)//计算cp
    {
        Double tmpV = 0.0;
        tmpV = UpperLimit - LowerLimit;
        Double temp = Math.abs(tmpV / (6 * StDev));
        System.out.println("计算cp:"+temp);
        return temp;
    }

    public static Double Avage(Double[] arrData)    //计算平均值
    {
        Double tmpSum = 0.0;
        for (int i = 0; i < arrData.length; i++) {
            tmpSum += arrData[i];
        }
        Double temp = tmpSum / arrData.length;
        System.out.println("计算平均值:"+temp);
        return temp;
    }

    public static Double Max(Double[] arrData)   //计算最大值
    {
        Double tmpMax = 0.0;
        for (int i = 0; i < arrData.length; i++) {
            if (tmpMax < arrData[i]) {
                tmpMax = arrData[i];
            }
        }
        System.out.println("计算最大值:"+tmpMax);
        return tmpMax;
    }

    public static Double Min(Double[] arrData)  //计算最小值
    {
        Double tmpMin = arrData[0];
        for (int i = 1; i < arrData.length; i++) {
            if (tmpMin > arrData[i]) {
                tmpMin = arrData[i];
            }
        }
        System.out.println("计算最小值:"+tmpMin);
        return tmpMin;
    }

    public static Double CpkU(Double UpperLimit, Double Avage, Double StDev)//计算CpkU
    {
        Double tmpV = 0.0;
        tmpV = UpperLimit - Avage;
        Double temp = tmpV / (3 * StDev);
        System.out.println("计算CpkU:"+temp);
        return temp;
    }

    public static Double CpkL(Double LowerLimit, Double Avage, Double StDev) //计算CpkL
    {
        Double tmpV = 0.0;
        tmpV = Avage - LowerLimit;
        Double temp = tmpV / (3 * StDev);
        System.out.println("计算CpkL:"+temp);
        return temp;
    }

    public static Double Cpk(Double CpkU, Double CpkL)  //计算Cpk
    {
        return Math.abs(Math.min(CpkU, CpkL));
    }

    public static Double getR_value(Double[] k_valuesTOO) {
        Double min = k_valuesTOO[0];
        Double max = k_valuesTOO[0];
        for (int i = 0; i < k_valuesTOO.length; i++) {
            if (k_valuesTOO[i] < min) {
                min = k_valuesTOO[i];
            }
            if (k_valuesTOO[i] > max) {
                max = k_valuesTOO[i];
            }
        }
        return max - min;
    }

    public static ResponseCPK getCPK(Double[] k, Double UpperLimit, Double LowerLimit) //获取CPK值
    {
        if (k.length <= 1 || UpperLimit <= LowerLimit) {
            return null;
        }
        ResponseCPK responseCPK = new ResponseCPK();
        responseCPK.setUpper(String.format("%.2f", UpperLimit));
        responseCPK.setLower(String.format("%.2f", LowerLimit));
        Double avage = Avage(k);
        responseCPK.setAvage(String.format("%.2f", avage));
        Double stDev = StDev(k);
        responseCPK.setStDev(String.format("%.2f", stDev));
        Double cpkU = CpkU(UpperLimit, avage, stDev);
        responseCPK.setCpkU(String.format("%.2f", cpkU));
        Double cpkL = CpkL(LowerLimit, avage, stDev);
        responseCPK.setCpkL(String.format("%.2f", cpkL));
        Double max = Max(k);
        responseCPK.setMax(String.format("%.2f", max));
        Double min = Min(k);
        responseCPK.setMin(String.format("%.2f", min));
        Double cp = Cp(UpperLimit, LowerLimit, stDev);
        responseCPK.setCp(String.format("%.2f", cp));
        Double cpk = Cpk(cpkU, cpkL);
        responseCPK.setCpk(String.format("%.2f", cpk));
        return responseCPK;
    }

//    public static void main(String[] args) {
////        Double[] k = { 1042.332,1040.516,1042.106,1036.056,1041.345,1040.67,1038.252,1040.703,1042.809,1042.843,1044.282,1040.867,1036.146,1038.914,1042.238,1039.159,1042.256,1043.927,1041.287,1036.542,1038.442,1042.97,1037.518,1044.009,1043.75,1037.898,1038.568,1039.836,1042.2,1043.904,1037.451,1035.425,1042.012,1041.301,1037.543,1043.323,1044.81,1036.836,1040.625,1039.337,1036.414,1041.186,1036.714,1044.462,1039.894,1044.381,1038.5,1042.202,1042.974,1037.581,1037.092,1037.176,1038.98,1039.197,1041.913,1039.404,1039.977,1041.921,1041.705,1038.002,1038.157,1035.911,1037.793,1042.426,1036.342,1042.095,1042.321,1039.696,1043.727,1039.881,1039.765,1042.85,1044.121,1038.439,1045.228,1036.853,1036.307,1037.333,1042.69,1039.996,1039.591,1043.824,1039.041,1043.93,1038.208,1042.663,1037.355,1039.739,1039.877,1042.713,1040.887,1041.333,1036.667,1040.801,1039.516,1037.74,1043.92,1037.326,1038.797,1040.592,1039.13,1036.589,1043.211,1038.577,1039.29,1041.116,1043.312,1037.359,1037.004,1039.263,1036.402,1037.818,1037.963,1038.694,1044.23,1039.411,1041.34,1039.674,1037.704,1037.336,1036.532,1039.583,1044.349,1041.619,1038.585,1036.016,1043.398,1039.098,1037.974,1039.087,1038.487,1040.294,1043.168,1043.319,1043.01,1041.39,1039.022,1039.76,1039.23,1040.188,1043.661,1041.573,1039.417,1038.66,1036.935,1043.457,1038.058,1044.324,1037.947,1039.547,1042.892,1040.672,1039.781,1037.164,1042.609,1043.135,1039.902,1036.335,1036.924,1038.19,1038.734,1037.495,1036.85,1042.452,1037.369,1042.893,1042.406,1042.284,1039.048,1039.299,1039.562,1042.363,1042.763,1042.565,1040.368,1043.446,1043.618,1042.829,1039.101,1042.628,1039.931,1040.055,1039.302,1042.159,1039.495,1039.514,1041.355,1040.133,1043.346,1037.328,1037.366,1044.033,1038.165,1040.193,1036.147,1041.74,1035.39,1041.907,1040.409,1041.015,1042.142,1039.682,1041.024,1036.315,1035.736,1035.391,1044.258,1039.934,1037.608,1038.558,1040.716,1041.489,1040.614,1037.871,1041.762,1041.896,1041.645,1038.992,1038.607,1036.935,1035.534,1036.208,1040.867,1037.886,1038.232,1038.432,1043.05,1036.155,1042.966,1038.423,1038.259,1037.759,1042.268,1037.953,1036.884,1041.649,1040.639,1039.352,1038.985,1038.529,1042.281,1040.12,1038.667,1040.661,1036.758,1040.414,1041.069,1040.569,1044.264,1043.586,1037.076,1043.573,1040.551,1041.412,1041.338,1036.289,1039.4,0,1037.69,1039.551,1037.899,1040.833,1042.381,1037.043,1035.152,1042.162,1039.352,1041.176,1040.645,1037.647,1039.846,1036.639,1043.72,1036.942,1040.44,1044.223,1037.354,1043.971,1038.843,1042.042,1041.306 };
////        Double UpperLimit = 1060.00F;  //上限
////        Double LowerLimit = 1000F; //下限
//        Double[] k = { 1.52, 1.53, 1.66, 1.45, 1.52, 1.62, 1.63, 1.61, 1.58, 1.59, 1.57, 1.55, 1.56, 1.66, 1.64, 1.63 };
//        Double UpperLimit = 1.75;  //上限
//        Double LowerLimit = 1.35; //下限
//        ResponseCPK result = getCPK(k, UpperLimit, LowerLimit);
//        System.out.println(result);
//    }
}
