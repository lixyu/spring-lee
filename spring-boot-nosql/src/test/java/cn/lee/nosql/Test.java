package cn.lee.nosql;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lee
 * @date 2019-07-23 13:54
 */
public class Test {
    public static void main(String[] args) {
        int len = 22;
        len = len + (len >> 1);
        System.out.println(len);

        int num = 12;

        System.out.println(num << 1);
        int size = 22;
        System.out.println(size >>> 1);

        Map<String, String> map = new HashMap<>();
    }
}
