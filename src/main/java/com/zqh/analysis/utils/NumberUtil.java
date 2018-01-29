package com.zqh.analysis.utils;

/**
 * @fileName: NumberUtil
 * @author: qhzhang
 * @date: 2018/01/26 12:01
 * @discription:
 */
public class NumberUtil {

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder("0x");
        int tmp;
        for(byte b : bytes) {
            tmp = b & 0x000000FF;
            if (tmp > 0) {
                String s = Integer.toHexString(tmp).toUpperCase();
                sb.append(s.length()%2 != 0 ? "0"+s:s);
            } else {
                sb.append("00");
            }
        }
        return sb.toString();
    }

    public static int bytesToInt(byte[] bytes) {
        int tmp = 0;
        for(byte b : bytes) {
            tmp = (tmp << 8) | (b & 0x000000FF);
        }
        return tmp;
    }

    public static long bytesToLong(byte[] bytes) {
        long tmp = 0;
        for(byte b : bytes) {
            tmp = (tmp << 8) | (b & 0x000000FF);
        }
        return tmp;
    }

    public static float bytesToFloat(byte[] bytes) {
       return Float.intBitsToFloat(bytesToInt(bytes));
    }

    public static double bytesToDouble(byte[] bytes) {
        return Double.longBitsToDouble(bytesToLong(bytes));
    }
}
