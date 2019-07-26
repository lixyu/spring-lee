package cn.lee.demo.sort;

/**
 * @author Lee
 * @date 2019-07-25 09:52
 */
public class SelectSort {
    private static int[] testArray = {26, 53, 48, 11, 13, 48, 32, 15};

    public static void main(String[] args) {
        selectSort();
        for (int i = 0; i < testArray.length; i++) {
            System.out.println(testArray[i]);
        }
    }

    public static void selectSort() {
        for (int i = 0; i < testArray.length; i++) {
            int min = i; //当前最小元素的索引
            //遍历剩余的数组
            for (int j = i + 1; j < testArray.length; j++) {
                //比较当前数和最小数组元素索引值
                if (testArray[j] < testArray[min]) {
                    min = j;
                }
            }
            int temp = testArray[i];
            testArray[i] = testArray[min];
            testArray[min] = temp;
        }
    }

}
