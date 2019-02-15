package com.yhq.algorithm.sort.string;


/**
 * 　LSD全称：Least-signiﬁcant-digit-ﬁrst 低位有效数字优先。
 * 　如果要排序的那堆字符串长度相同，我们可以用LSD基数排序
 *
 * @Author : YHQ
 * @Date: 11:24 2019/2/14
 */
public class LSDRadixSort implements IRadixSort{
    private String []arrays;
    private int maxLength;

    public LSDRadixSort(String[] arrays,int maxLength) {
        this.arrays = arrays;
        this.maxLength = maxLength;
    }

    @Override
    public String[] getArrays() {
        return arrays;
    }

    @Override
    public void sort(){
        for (int k =maxLength-1; k >= 0; --k) {
            sort(arrays, 257,k);
            log(k);
        }
    }
    /**
     * LSD
     * @param stringArray 字符串数组a
     * @param R 分组个数R
     * @param p 字符串数组的当前处理列下标，将字符串数组的元素（字符串）理解为字符数组，p即为字符数组的当前处理下标
     */
    public void sort(String[] stringArray, int R,int p) {
        int N = stringArray.length;
        //取asc码的256个字符的长度
        String[] aux = new String[stringArray.length];
        int[] count = new int[R + 1];

        // 第一步，计算出现的频率:从字符二维数组取的一维列下标取全部二维下标值为p的元素
        for (int i = 0; i < N; i++) {
            count[stringArray[i].charAt(p) + 1]++;
        }
        log(R, count);
        //第二步，将频率转换成索引
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        //第三步， 将元素分类
        for (int i = 0; i < N; i++) {
            aux[count[stringArray[i].charAt(p)]++] = stringArray[i];
        }

        // 第四步，回写
        for (int i = 0; i < N; i++) {
            stringArray[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        String[] arrays = {"cac", "fnc", "edb", "oel", "akf", "erk"};
        LSDRadixSort lsdRadixSort = new LSDRadixSort(arrays,3);
        lsdRadixSort.sort();
        lsdRadixSort.show();
    }

}
