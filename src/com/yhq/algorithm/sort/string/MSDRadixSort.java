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
        //取asc码的256个字符的长度
        String[] aux = new String[arrays.length];
        sort(arrays,aux, 257,0,arrays.length-1,0);
    }

    private static int charAt(String s,int i){
        if(s.length() > i){
            return s.charAt(i);
        }else{
            return -1;
        }
    }

    /**
     * MSD
     * @param array 字符串数组a
     * @param aux 辅助字符串数组
     * @param R 分组个数R
     * @param lo 起始下标
     * @param hi 终止下标
     * @param d 当前处理的字符数组下标
     */
    public void sort(String[] array,  String[] aux ,int R,int lo,int hi,int d) {
        if(hi <= lo)return;
        int[] count = new int[R + 2];

        // 第一步，计算出现的频率
        for (int i = lo; i <= hi; i++) {
            count[charAt(array[i],d) + 2]++;
        }
        log(R, count);
        //第二步，将频率转换成索引
        for (int r = 0; r < R+1; r++) {
            count[r + 1] += count[r];
        }

        //第三步， 将元素分类
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(array[i],d)+1]++] = array[i];
        }

        // 第四步，回写
        for (int i = lo; i <= hi; i++) {
            array[i] = aux[i-lo];
        }

        for(int r = 0;r < R;r++){
            sort(array,aux,R,lo+count[r],lo+count[r+1]-1,d+1);
        }
    }

    public static void main(String[] args) {
        String[] arrays = {"cac", "fnc", "e", "oel", "akfgg", "erk","eg","akf"};
        MSDRadixSort msdRadixSort = new MSDRadixSort(arrays);
        msdRadixSort.sort();
        msdRadixSort.show();
    }

}
