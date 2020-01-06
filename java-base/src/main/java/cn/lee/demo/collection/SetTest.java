package cn.lee.demo.collection;

import java.util.*;

/**
 * @author Lee
 * @date 2019-08-01 14:49
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        System.out.println(set.add("Lee"));
        System.out.println(set.add("credit"));
        System.out.println(set.add("cnblog"));
        System.out.println(set.add("蓝天"));
        System.out.println(set.add("白云"));

        System.out.println(set.add("Lee"));

        System.out.println("HashSet元素1:" + set.size());
        System.out.println(set.remove("credit"));
        System.out.println("HashSet元素2:" + set.size());

        Iterator<String> stringIterator = set.iterator();
        while (stringIterator.hasNext()) {
            System.out.println(stringIterator.next());
        }
        System.out.println("=====================================");
        for (String str : set) {
            System.out.println(str);
        }
        System.out.println("=====================================");

        set.clear();
        System.out.println("set.clear()---->" + set.isEmpty());

        System.out.println("=====================================");
        HashSet<String> hashSet = new HashSet<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();

        List<String> list = Arrays.asList("2", "3", "1", "9", "7", "B", "A");
        for (String s : list) {
            hashSet.add(s);
            linkedHashSet.add(s);
            treeSet.add(s);
        }

        System.out.println("HashSet(不保证顺序):" + hashSet);
        System.out.println("LinkedHashSet(先进先出):" + linkedHashSet);
        System.out.println("TreeSet(排序功能):" + treeSet);

        System.out.println(treeSet.first());
        System.out.println(treeSet.last());
    }
}
