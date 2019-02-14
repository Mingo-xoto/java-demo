package com.yhq.algorithm.sort.string;

/**
 * 键索引计数法
 *
 * @author pengcx
 *
 */
class KeyIndex {

    /**
     * LSD
     * @param stringArray 字符串数组a
     * @param R 分组个数R
     * @param p 字符串数组的当前处理列下标，将字符串数组的元素（字符串）理解为字符数组，p即为字符数组的当前处理下标
     */
    public static void sort(String[] stringArray, int R,int p) {
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
     * @param R 分组个数R
     */
    public static void sort(String[] array,  String[] aux ,int R,int lo,int hi,int d) {
        if(hi <= lo)return;
        int[] count = new int[R + 2];

        // 第一步，计算出现的频率:从字符二维数组取的一维列下标取全部二维下标值为p的元素
        for (int i = lo; i <= hi; i++) {
            count[charAt(array[i],d) + 2]++;
        }
//        log(R, count);
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

    /**
     * 打印处理日志
     * @Author : YHQ
     * @Date: 14:19 2019/2/14
     *
     */
    private static void log(int R, int[] count) {
        System.out.println("\n------start log current sort result----");
        System.out.print("    i:\t");
        for(int l = 0;l < R;++l){
            if(count[l] > 0){
                System.out.print(String.format("%c(%d) ",(char)(l-1),l));
            }
        }
        System.out.print("\ncount:\t");
        for(int i = 0;i < count.length;++i){
            if(count[i] > 0) {
                int len = (i+"").length()+3;
                int len2 = (count[i]+"").length();
                StringBuilder sb = new StringBuilder(" ");
                for (int h = len2;h < len;++h){
                    sb.append(" ");
                }
                System.out.print(count[i]+sb.toString());
            }
        }
        System.out.println("\n------end log current sort result----\n");
    }

}