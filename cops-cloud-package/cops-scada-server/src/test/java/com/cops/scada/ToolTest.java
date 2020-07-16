package com.cops.scada;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class ToolTest {

    /**
     * 获取对象属性，返回一个字符串数组
     *
     * @param  o 对象
     * @return String[] 字符串数组
     */
    private static String[] getFiledName(Object o)
    {
        try
        {
            Field[] fields = o.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for (int i=0; i < fields.length; i++)
            {
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;
        } catch (SecurityException e)
        {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * 使用反射根据属性名称获取属性值
     *
     * @param  fieldName 属性名称
     * @param  o 操作对象
     * @return Object 属性值
     */

    private static Object getFieldValueByName(String fieldName, Object o)
    {
        try
        {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e)
        {
            System.out.println("属性不存在");
            return null;
        }
    }

    public static void main(String[] args) {
/*        BigDecimal a=new BigDecimal("10");
        BigDecimal b=new BigDecimal("20");
        BigDecimal c=a.add(b);
        System.out.println(c);
         c=a.subtract(b);
        System.out.println(c);
         c=a.multiply(b).setScale(3);
        System.out.println(c);
         c=a.divide(b);
        System.out.println(c);
        c=(a.divide(b)).multiply(new BigDecimal(100)).setScale(3);
        System.out.println(c.toString()+"%");

        System.out.println((a.divide(b).setScale(3)).multiply(new BigDecimal(100)).toString()+"%");*/

/*        OrderPlanWHTotal orderPlanWHTotal=new OrderPlanWHTotal();
        orderPlanWHTotal.setProlificacyTotal("1");
        orderPlanWHTotal.setProlificacyTotalRate("2");
        orderPlanWHTotal.setWHBorrow("3");
        orderPlanWHTotal.setWHErrorTotal("4");
        orderPlanWHTotal.setWHInvestTotal("5");
        orderPlanWHTotal.setWHLend("6");
        orderPlanWHTotal.setWHReal("7");
        orderPlanWHTotal.setWHShould("8");
        Object a = getFieldValueByName("prolificacyTotal",orderPlanWHTotal);*/

        String name=null;
        if ("".equals(name)) {//将""写在前头，这样，不管name是否为null，都不会出错。
            //do something
            System.out.println("null");
        }
    }
}
