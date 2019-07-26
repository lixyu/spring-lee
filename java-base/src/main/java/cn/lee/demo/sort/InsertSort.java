package cn.lee.demo.sort;

/**
 * @author Lee
 * @date 2019-07-25 10:00
 */
public class InsertSort {
    private static int[] testArray = {26, 53, 48, 11, 13, 48, 32, 15};

    public static void main(String[] args) {
        insertSort();
        for (int i = 0; i < testArray.length; i++) {
            System.out.print(testArray[i] + " ");
        }
    }

    public static void insertSort() {
        for (int i = 1; i < testArray.length; i++) {
            //得到遍历时候当前索引下面的值，然后和前面排序号的数组元素进行比较，插入到合适的位置
            for (int j = i; j > 0 && less(testArray[j], testArray[j - 1]); j--) {
                int temp = testArray[j];
                testArray[j] = testArray[j - 1];
                testArray[j - 1] = temp;
            }
        }
    }

    public static boolean less(int a, int b) {
        return a < b;
    }
}
