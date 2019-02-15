package com.yhq.algorithm.sort.string;

/**
 * 字符串基数排序接口
 * @Author : YHQ
 * @Date: 15:16 2019/2/14
 *
 */
public interface IRadixSort {
    String[] getArrays();

    /**
     * 排序
     * @Author : YHQ
     * @Date: 15:41 2019/2/14
     *
     */
    void sort();

    /**
     * 打印处理日志
     * @Author : YHQ
     * @Date: 14:33 2019/2/14
     *
     */
    default void log(int k) {
        String[]arrays = getArrays();
        System.out.println("------start log sort dealing process----");
        System.out.println(String.format("第%d列排序",k+1));
        for(int i = 0;i < k+1;++i){
            if(i == k){
                System.out.print("↓\t");
            }else{
                System.out.print(" \t");
            }
        }
        System.out.println();
        for(int i = 0;i < arrays.length;++i){
            for(int j = 0;j < arrays[i].length();++j){
                System.out.print(arrays[i].charAt(j)+"\t");
            }
            System.out.println();
        }
        System.out.println("------end log sort dealing process----");
    }

    /**
     * 打印处理日志
     * @Author : YHQ
     * @Date: 14:19 2019/2/14
     *
     */
    default void log(int R, int[] count) {
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

    default void show(){
        String[]arrays = getArrays();
        System.out.println("最终排列");
        for(int i = 0;i < arrays.length;++i){
            for(int j = 0;j < arrays[i].length();++j){
                System.out.print(arrays[i].charAt(j)+"\t");
            }
            System.out.println();
        }
    }
}
