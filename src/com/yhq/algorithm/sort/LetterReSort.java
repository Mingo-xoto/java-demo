package com.yhq.algorithm.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * 字母重排
 * @Author : YHQ
 * @Date: 14:28 2019/2/13
 *
 */
public class LetterReSort {

    final int LOWERBEGIN = (int) 'a';
    final int LOWEREND = (int) 'z';
    final int UPPERBEGIN = (int) 'A';
    final int UPPEREND = (int) 'Z';

    public boolean isLower(char ch){
        return ch >= LOWERBEGIN && ch <= LOWEREND;
    }

    public boolean isUpper(char ch){
        return ch >= UPPERBEGIN && ch <= UPPEREND;
    }

    /**
     * 字符串大小写重排序：小写字母在前，大写字母在后，且不打乱各自原先排序：例如aXbZcP重拍后为：abcXZP
     * @Author : YHQ
     * @Date: 14:23 2019/2/13
     *
     */
    public void letterRearrangeTest() {
        char chs[] = { 'O', 'k', 'h', 'a', 'o', 'p', 'i', 'n', 'G', 'C', 'e', 'i', 'l', 'X', 'u' };
        letterRearrange(chs);
        letterRearrange1(chs);
    }

    /**
     * 空间复杂度O(1)字符串大小写重排
     *
     * @param chs
     */
    public void letterRearrange(char chs[]) {
        for (int i = 0; i < chs.length; ++i) {
            if (isUpper(chs[i])) {
                for (int j = i + 1; j < chs.length; ++j) {
                    if (isLower(chs[j])) {
                        for (int p = j - 1; p >= i; p--, j--) {
                            char ch = chs[p];
                            chs[p] = chs[j];
                            chs[j] = ch;
                            System.out.print("i=" + i + " ,j=" + j + "\t--->\t");
                            for (char c : chs) {
                                System.out.print(c);
                            }
                            System.out.println();
                        }
                        break;
                    }
                }
            }
        }
        System.out.println("重排后字符串：");
        for (char c : chs) {
            System.out.print(c);
        }
        System.out.println();
    }

    /**
     * 时间复杂度O(1)字符串大小写重排
     *
     * @param chs
     */
    public void letterRearrange1(char chs[]) {
        StringBuilder lower = new StringBuilder();
        StringBuilder upper = new StringBuilder();
        for (int i = 0; i < chs.length; ++i) {
            if (isUpper(chs[i])) {
                upper.append(chs[i]);
            } else if (isLower(chs[i])) {
                lower.append(chs[i]);
            }
        }
        System.out.println(lower.toString() + upper.toString());
    }

    /**
     * 首个重复字符:首个重复出现的字符，例如axxa，则为x
     *
     * @param chs
     */
    public void firstRepeatLetter(char chs[]) {
        Map<Character, Boolean> map = new HashMap<>();
        map.put(chs[0], true);
        for (int i = 1; i < chs.length; ++i) {
            if (map.containsKey(chs[i])) {
                System.out.println(String.format("目标字符：%c，位置：%d",chs[i],i));
                break;
            }
            map.put(chs[i], true);
        }
    }

    /**
     * 排最前的重复字符:重复字符中排在最前面的字符，例如axxa，则为a
     *
     * @param chs
     */
    public void firstRepeatLetter2(char chs[]) {
        Map<Character, Integer> preMap = new HashMap<>();
        Map<Character, Integer> afterMap = new HashMap<>();
        int i = 0;
        int l = chs.length-1;

        int afterFrist = -1;

        char fisrt = chs[i];
        char last = chs[l--];
        if(fisrt == last){
            System.out.println("目标字符位置："+i);
            return;
        }
        preMap.put(fisrt, i);
        afterMap.put(last, l);
        i++;
        do {
            System.out.println(i+":"+l);
            char after = chs[l];
            char pre = chs[i];
            System.out.println(String.format("pre字符：%c，after字符：%c，pre字符集：%s，after字符集：%s",pre,after,preMap,afterMap));
            //后半部分读取的当前字符已在前半字符集
            if (preMap.containsKey(after)) {
                System.out.println(String.format("\n\nA-P类，目标字符：%c，位置：%d",after,preMap.get(after)));
                return;
            }
            //前半部分读取的当前字符已在前半字符集
            if (preMap.containsKey(pre)) {
                System.out.println(String.format("\n\nP-P类，目标字符：%c，位置：%d",pre,preMap.get(pre)));
                return;
            }
            //后半部分读取的当前字符在后半字符集:特殊处理，因为可能会在前半部分字符集里面出现重复字符
            if (afterMap.containsKey(after)) {
                afterFrist = l;
                System.out.println(String.format("\n\nP-A类：重复字符%c，位置%d：",after,i));
            }
            //前半部分读取的当前字符在后半字符集
            if (afterMap.containsKey(pre)) {
                System.out.println(String.format("\n\nP-A类：目标字符：%c，位置：%d",pre,afterMap.get(pre)));
                return;
            }
            preMap.put(pre, i);
            afterMap.put(after, l);
            i++;
            l--;
            System.out.println();
        } while (i < l);
        System.out.println(String.format("\n\nA-A类：目标字符：%c，位置：%d",chs[afterFrist],afterFrist));
    }

    public static void main(String[] args) {
        LetterReSort letterReSort = new LetterReSort();
//        letterReSort.letterRearrangeTest();
        char chs[] = { '0','=','2','3','4','a','x','x','a' };
        letterReSort.firstRepeatLetter(chs);
        letterReSort.firstRepeatLetter2(chs);
    }

}
