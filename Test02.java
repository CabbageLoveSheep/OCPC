package com.violet.mmall02.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String p = scanner.nextLine();

        long start = System.currentTimeMillis();
        try {
            char[] pArr = p.toCharArray();
            char headChar = pArr[0];

            // 取出p中首字母的下标集合
            List<Integer> headCharIndexList = new ArrayList<>();
            findCharIndex(headCharIndexList, headChar, s, -1, true);
            System.out.println("headCharIndexList" + headCharIndexList);

            List<Integer> charIndexList = null;
            List<Integer> resultList = new ArrayList<>();
            int minIndex = 0;
            boolean flag;
            for (int headIndex : headCharIndexList) {
                flag = true;
                minIndex = headIndex;
                for (int i = 1; i < pArr.length; i++) {
                    charIndexList = new ArrayList<>();
                    findCharIndex(charIndexList, pArr[i], s, minIndex, false);
                    if (charIndexList.isEmpty()) {
                        flag = false;
                        break;
                    }
                    minIndex = charIndexList.get(0);
                }
                if (flag) {
                    resultList.add(charIndexList.get(0) - headIndex - pArr.length + 1);
                } else {
                    break;
                }
            }
            resultList.sort(Integer::compareTo);
            int result = resultList.get(0);
            System.out.println("结果：" + result);
        } catch (Exception e) {
            System.out.println("没有结果！");
        }

        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
    }

    public static void findCharIndex(List<Integer> indexList, char c, String s, int minIndex, boolean flag) {
        int i = s.indexOf(c, minIndex + 1);

        if (i > s.length() || i < 0) {
            return;
        }
        indexList.add(i);

        if (flag) {
            findCharIndex(indexList, c, s, i, flag);
        }
    }
}
