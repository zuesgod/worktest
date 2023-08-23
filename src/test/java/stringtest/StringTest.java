package stringtest;

import org.junit.jupiter.api.Test;

/**
 * string测试类
 *
 * @author zeus
 * @date 2022-01-27 9:38
 **/
public class StringTest {

    /**
     * 给定字符串，统计每个字符出现的次数
     */
    public static void main(String[] args) {
        String s = "1239586838923173478943890234092";

        long nanos = System.nanoTime();
        countChars1(s); // for+for 逐个计数、查重
        System.out.println("\n for+for运行时间（纳秒）：" + (System.nanoTime() - nanos)); // 约79w纳秒

        System.out.println("-------------------------");
        nanos = System.nanoTime();
        countChars2(s); // replace() 重复的替换为空，两个字符串长度相减
        System.out.println("\n replace运行时间（纳秒）：" + (System.nanoTime() - nanos)); // 约250w纳秒

        System.out.println("-------------------------");
        nanos = System.nanoTime();
        countChars3(s); // new int[10] 使用0-9下标作为数字，元素作计数
        System.out.println("\n int[10]运行时间（纳秒）：" + (System.nanoTime() - nanos)); // 约39w纳秒

        System.out.println("-------------------------");
        nanos = System.nanoTime();
        countChars4(s); // regex 正则匹配直接替换出相同的字符组成字符串，长度即数量
        System.out.println("\n regex运行时间（纳秒）：" + (System.nanoTime() - nanos)); // 约50w~80w纳秒不稳定

    }

    public static void countChars1(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            Character ch = new Character(s.charAt(i));
            if (sb.indexOf(ch.toString()) >= 0) { // 利用StringBuilder的indexOf()方法查重
                continue;
            }

            for (int j = 0; j < s.length(); j++) {
                if (ch == s.charAt(j)) {
                    sb.append(ch);
                    count++;
                }
            }
            System.out.println(ch + "出现次数：" + count);
        }
    }

    public static void countChars2(String s) {
        for (int i = 0; i < 10; i++) {
            String str = s.replace(String.valueOf(i), ""); // 将1-9单个相同的数字替换出来，剩下的保存
            System.out.println(i + "出现次数：" + (s.length() - str.length()));
        }
    }

    public static void countChars3(String s) {
        int[] c = new int[10];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int a = ch - 48;
            c[a]++;
        }

        for (int i = 0; i < c.length; i++) {
            System.out.println(i + "出现次数：" + c[i]);
        }
    }

    public static void countChars4(String s) {
        for (int i = 0; i < 10; i++) {
            String str2 = s.replace("[^" + i + "]", "");
            System.out.println(i + "出现次数：" + str2.length());
        }
    }


}
