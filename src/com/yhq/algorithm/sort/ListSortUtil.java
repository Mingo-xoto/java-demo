package com.yhq.algorithm.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * list工具
 *
 * @Author : YHQ
 * @Date: 10:40 2018/12/7
 */
public class ListSortUtil {

    private static ListSortUtil listUtil = null;

    private ListSortUtil() {
    }

    public static ListSortUtil getInstance() {
        synchronized (ListSortUtil.class) {
            if (listUtil == null) {
                listUtil = new ListSortUtil();
            }
        }
        return listUtil;
    }

    /**
     * 插入排序:升序
     *
     * @Author : YHQ
     * @Date: 10:41 2018/12/7
     */
    public static <E extends Comparable> void insertSort(E vo, List<E> list) {
        insertSort0(vo, list,true);
    }

    private static <E extends Comparable> void insertSort0(E vo, List<E> list,boolean asc) {
        if (list == null) {
            list = new LinkedList<>();
        }
        if (list.isEmpty()) {
            list.add(vo);
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            E temp = list.get(i);
            if(compareValue(vo,temp,asc) < 0){
                list.add(i, vo);
                return;
            }
        }
        list.add(vo);
    }

    /**
     * 插入排序:降序
     *
     * @Author : YHQ
     * @Date: 10:41 2018/12/7
     */
    public static <E extends Comparable> void insertSortDesc(E vo, List<E> list) {
        insertSort0(vo, list,false);
    }

    private static <E extends Comparable> int compareValue(E e1,E e2,boolean asc){
        if(asc){
            return e1.compareTo(e2);
        }
        return e2.compareTo(e1);
    }

    /**
     * 二分插入：升序排序
     * @Author : YHQ
     * @Date: 14:02 2019/1/16
     *
     */
    public static <E extends Comparable> void binaryInsertSort(E vo, List<E> list){
        binaryInsertSort0(vo,list,true);
    }

    /**
     * 二分插入：倒序排序
     * @Author : YHQ
     * @Date: 14:02 2019/1/16
     *
     */
    public static <E extends Comparable> void binaryInsertSortDesc(E vo, List<E> list){
        binaryInsertSort0(vo,list,false);
    }

    /**
     * 二分插入排序
     *
     * @Author : YHQ
     * @Date: 10:41 2018/12/7
     */
    private static <E extends Comparable> void binaryInsertSort0(E vo, List<E> list, boolean asc) {
        if (list == null) {
            list = new LinkedList<>();
        }
        if (list.isEmpty()) {
            list.add(vo);
            return ;
        }
        if(list.size() == 1){
            if(compareValue(list.get(0),vo,asc)< 0){
                list.add(1,vo);
            }else {
                list.add(0,vo);
            }
            return;
        }
        int low = 0;
        int high = list.size();
        int mid = (low + high) / 2;
        while (true) {
            if(low == mid){
                if(compareValue(vo,list.get(mid),asc) >= 0){
                    list.add(mid+1, vo);
                }else{
                    list.add(mid, vo);
                }
                return;
            }
            if (mid == high) {
                if(compareValue(list.get(mid),vo,asc) < 0){
                    list.add(mid+1, vo);
                }else{
                    list.add(mid, vo);
                }
                return;
            }
            E midVo = list.get(mid);
            //等于mid：紧接放在mid后
            if (compareValue(midVo,vo,asc) == 0) {
                list.add(mid, vo);
                return;
            }
            //小于mid：
            if (compareValue(midVo,vo,asc) > 0) {
                high = mid;
                mid = (low + high) / 2;
                continue;
            }
            //大于mid：
            if (compareValue(midVo,vo,asc) < 0) {
                low = mid;
                mid = (low + high) / 2;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> orginalList = new LinkedList<>();
        List<Integer> orginalList2 = new LinkedList<>();
        ListSortUtil sortUtil = getInstance();
        final int loop = 100000;
        Random random = new Random(10000);
        for(int i = 0;i < loop;++i){
            int rand = random.nextInt(2*loop);
            orginalList.add(rand);
            orginalList2.add(rand);
        }

        System.out.println("----开始----");
        fuck0(orginalList, orginalList2);
        System.out.println("----我是一条完美的分界线----");
        fuck1(orginalList, orginalList2);
        System.out.println("----结束----");


        System.out.println("----我是又又又一条完美的分界线----\n\n");

        fuck1(orginalList, orginalList2);
        System.out.println("----我是一条完美的分界线----");
        fuck0(orginalList, orginalList2);
    }

    private static void fuck0(List<Integer> orginalList, List<Integer> orginalList2) {

        List<Integer> insertSortList = new LinkedList<>();
        List<Integer> binaryInsertSortList = new LinkedList<>();
        long bt1 = System.nanoTime();
        for(Integer item:orginalList2){
            insertSort(item, insertSortList);
        }

        long et1 = System.nanoTime();
        for(Integer item:orginalList){
            binaryInsertSort(item, binaryInsertSortList);
        }
        long et2 = System.nanoTime();

        System.out.println("LinkedList插入排序耗时："+(et1-bt1)+" ms");
        System.out.println("LinkedList二分排序耗时："+(et2-et1)+" ms");
        show(binaryInsertSortList,true);
        show(insertSortList,true);
    }

    private static void fuck1(List<Integer> orginalList, List<Integer> orginalList2) {
        List<Integer> insertSortList = new ArrayList<>();
        List<Integer> binaryInsertSortList = new ArrayList<>();
        long bt3 = System.nanoTime();
        for(Integer item:orginalList2){
            binaryInsertSortDesc(item, binaryInsertSortList);
        }

        long et3 = System.nanoTime();
        for(Integer item:orginalList){
            insertSortDesc(item, insertSortList);
        }
        long et4 = System.nanoTime();

        System.out.println("ArrayList二分排序耗时："+(et3-bt3)+" ms");
        System.out.println("ArrayList插入排序耗时："+(et4-et3)+" ms");
        show(binaryInsertSortList,false);
        show(insertSortList,false);
    }

    private static void show(List<Integer> list,boolean asc){
        int pre = list.get(0);
        System.out.print(pre+"\t");
        for (int i =1;i < list.size();++i){
            int t = list.get(i);
            pre = t;
            System.out.print(t+"\t");
        }
        System.out.println("\n检测");
        pre = list.get(0);
        for (int i =1;i < list.size();++i){
            int t = list.get(i);
            if(asc?pre > t:pre<t){
                throw new RuntimeException("排序不对");
            }
            pre = t;
        }
    }

}
