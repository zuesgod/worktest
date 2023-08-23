package listtest;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 测试集合
 *
 * @author zeus
 * @date 2022-01-27 9:16
 **/
public class ListTest {

    @Test
    public void test1() {
        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = null;

        System.out.println("list1.isEmpty() = " + list1.isEmpty());
//        System.out.println("list2.isEmpty() = " + list2.isEmpty());
        System.out.println("isNotNull(list1) = " + isNotNull(list1));
        System.out.println("isNotNull(list2) = " + isNotNull(list2));

    }

    @Test
    public void test2() {
        String[] str1 = new String[]{"uuid", "123", "sdf"};
        int[] int1 = new int[]{22, 123, 23};
        for (String s : str1) {
            System.out.println("s = " + s);
        }
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("aszfd a");
        list1.add("wqeqw");

        List<String> strings = castList(list1, String.class);
        System.out.println("strings = " + strings);
        List<Integer> list = castList(int1, Integer.class);
        System.out.println("list = " + list);
    }

    /*
     *  如何将List转为Map<Object, List<Object>>
     *  基本写法
     *
     */
    @Test
    public void test3() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("word");
        list.add("come");
        list.add("on");
        Map<Integer, List<String>> ans = new HashMap<>();
        //将相同字符长度的元素放入一个键的值中
//        for(String str: list) {
//            List<String> sub = ans.get(str.length());
//            if(Objects.isNull(sub)) {
//                sub = new ArrayList<>();
//                ans.put(str.length(), sub);
//            }
//            sub.add(str);
//        }
//        System.out.println(ans);
        System.out.println("----------------------jdk8的写法---------------------------");
        for (String str : list) {
            ans.computeIfAbsent(str.length(), k -> new ArrayList<>()).add(str);
        }
        System.out.println(ans);
        System.out.println("----------------------jdk8 Stream的写法---------------------------");
        //借助Stream流的方式处理
        Map<Integer, List<String>> ans1 = list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(ans1);
    }


    /**
     * 求两个集合的差集
     * @author zues
     * @date 2023/8/4 9:40
     */
    @Test
    public void test4(){
        List<Long> scheduleIds = new ArrayList<>();
        scheduleIds.add(1L);
        scheduleIds.add(2L);
        scheduleIds.add(3L);
        scheduleIds.add(4L);
        scheduleIds.add(5L);
        scheduleIds.add(6L);

        List<Long> idList = new ArrayList<>();
        idList.add(2L);
        idList.add(3L);
        idList.add(8L);
        idList.add(4L);

        ArrayList<Long> copyList = new ArrayList<>();
        copyList.addAll(scheduleIds);

//        scheduleIds.removeAll(idList);
        scheduleIds.remove(5L);
        System.out.println("scheduleIds = " + scheduleIds);
        System.out.println("copyList = " + copyList);

    }



    public static <E> boolean isNotNull(List<E> list) {
        return null != list && !list.isEmpty();
    }


    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                System.out.println("o = " + o);
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
