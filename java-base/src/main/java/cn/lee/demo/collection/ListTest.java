package cn.lee.demo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Lee
 * @date 2019-08-01 17:05
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> platformList = new ArrayList<>();

        // 添加元素
        platformList.add("博客园");
        platformList.add("掘金");
        platformList.add("微信公众号");

        // 添加重复元素,会添加成功,因为List支持添加重复元素
        platformList.add("博客园");
        platformList.add("掘金");


        platformList.add(3, "个人博客");

        System.out.println("索引为3的元素为：" + platformList.get(3));
        System.out.println("platformList的元素个数为：" + platformList.size());

        // 指定索引删除重复的元素 "博客园" "掘金"
        platformList.remove(4);
        platformList.remove(4);
        // 删除指定元素 "个人博客"
        platformList.remove("个人博客");

        System.out.println("platformList的元素个数为：" + platformList.size());

        platformList.set(0, "博客园：https://www.cnblogs.com/zwwhnly/");
        platformList.set(1, "掘金：https://juejin.im/user/5c7ce730f265da2dca388167");
        platformList.set(2, "微信公众号：申城异乡人");

        System.out.println("isEmpty:" + platformList.isEmpty());

        System.out.println("使用Iterator遍历：");
        Iterator<String> platformIterator = platformList.iterator();
        while (platformIterator.hasNext()) {
            System.out.println(platformIterator.next());
        }

        System.out.println();
        System.out.println("使用for循环遍历：");
        for (
                int i = 0; i < platformList.size(); i++) {
            System.out.println(platformList.get(i));
        }

        System.out.println();
        System.out.println("使用foreach遍历：");
        for (String platform : platformList) {
            System.out.println(platform);
        }

        System.out.println();

        // 清空集合
        platformList.clear();
        System.out.println("isEmpty:" + platformList.isEmpty());


    }
}
