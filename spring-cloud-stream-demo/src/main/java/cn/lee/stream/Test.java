package cn.lee.stream;

import java.math.BigDecimal;

/**
 * @author : Lee
 * @date : 2019/6/20
 */

public class Test {


    public static void main(String[] args) {
        BigDecimal.ZERO.equals(null);
        BigDecimal dd = null;
        dd.equals(BigDecimal.ZERO);
    }
}
