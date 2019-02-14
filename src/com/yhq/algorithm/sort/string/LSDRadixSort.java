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
        KeyIndex keyIndex = new KeyIndex();
        for (int k =maxLength-1; k >= 0; --k) {
            keyIndex.sort(arrays, 257,k);
            log(k);
        }
    }

    public static void main(String[] args) {
        String[] arrays = {"cac", "fnc", "edb", "oel", "akf", "erk"};
        LSDRadixSort lsdRadixSort = new LSDRadixSort(arrays,3);
        lsdRadixSort.sort();
        lsdRadixSort.show();
    }

}
