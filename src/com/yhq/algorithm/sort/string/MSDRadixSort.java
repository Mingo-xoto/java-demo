package com.yhq.algorithm.sort.string;


/**
 *  排序的那堆字符串长度不同
 *　MSD全称：Most-signiﬁcant-digit-ﬁrst 高位有效数字优先。
 *　LSD是从最后一位字符开始往前排序的，而MSD是从第一位字符开始往后排序的。
 *
 * 　这个算法演示过程本身就是一个递归的过程。
 * 　　1. 对所有字符串的第一个字符那一列通过键索引计数法把这堆字符串排一次序，根据排序结果进行分区；
 * 　　2. 从上往下处理各个分区：如果分区只有一个字符串，则此区处理完毕；如果有多个字符串，则对此区的字符串的下一个字符进行排序、分区、处理分区。
 * 　　3. 所有分区处理完后，排序完毕，算法结束。
 * @Author : YHQ
 * @Date: 11:21 2019/2/14
 *
 */
public class MSDRadixSort implements IRadixSort{

    private String []arrays;

    public MSDRadixSort(String[] arrays) {
        this.arrays = arrays;
    }

    @Override
    public String[] getArrays() {
        return arrays;
    }

    @Override
    public void sort(){
        KeyIndex keyIndex = new KeyIndex();
        //取asc码的256个字符的长度
        String[] aux = new String[arrays.length];
        keyIndex.sort(arrays,aux, 257,0,arrays.length-1,0);
    }

    public static void main(String[] args) {
        String[] arrays = {"cac", "fnc", "e", "oel", "akfgg", "erk","eg","akf"};
        MSDRadixSort msdRadixSort = new MSDRadixSort(arrays);
        msdRadixSort.sort();
        msdRadixSort.show();
    }

}
