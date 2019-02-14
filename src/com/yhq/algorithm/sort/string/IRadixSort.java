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
