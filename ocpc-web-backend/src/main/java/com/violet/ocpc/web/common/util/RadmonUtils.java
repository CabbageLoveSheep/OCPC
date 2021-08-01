package com.violet.ocpc.web.common.util;

public class RadmonUtils {
    /**
     * 功能: 生成指定长度的随机密码值 (仅由数值组成)
     * 
     * @author huangfei
     * @param pwdLen 指定的随机密码值的长度
     * @return 生成的随机密码 (String)
     */
    public static String genDigitalPwd(int pwdLen) {
        java.util.Random rdm = new java.util.Random();

        String newString = "";
        String temp = Integer.toString(Math.abs(rdm.nextInt()));
        if (temp.trim().length() > pwdLen)
            newString = temp.substring(0, pwdLen);
        else
            newString = temp;

        return newString;
    }


    /**
     * 功能: 取得指定范围的随机数<br>
     * 注: 取得的值不包含最大值(例如:max=3时值为0,1,2)
     * 
     * @param maxValue 取值时不被包含在内的最大值
     * @return 取得的随整数值
     */
    public static int getRandomIntInMax(int maxValue) {
        return (int) (Math.random() * maxValue % maxValue);
    }
}
