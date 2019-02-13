package com.yhq.algorithm.sort.other;

import java.util.*;

public class Algrithm {

    public static void main(String[] args) {
        // int arrays[] = getArray(11, -100, 10000);
        // int arrays[] = { 10, 11, 12, 1, 2, 5, 1 };
        // // maxSum(arrays);
        // HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // ConcurrentHashMap<Integer, Integer> cmap = new
        // ConcurrentHashMap<Integer, Integer>();
        // out.println(maxSum1(arrays, arrays.length));
        // out.println(toHexString(42));
        // char[] chs = { 'q', 'y', 'w', 'y', 'q', 'e', 'r', '2', '3', 't', 'd',
        // 'd' };
        // algrithm.firstRepeatLetter(chs);
//        Algrithm algrithm = new Algrithm();
//        algrithm.topTenTest();
//        combineCents();
//        sexRate();
    }

    public static int maxSum1(int a[], int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return a[0];
        }
        int front = 0, after = a[0];
        int front_new;
        for (int i = 1; i < n; ++i) {
            front_new = (front > after) ? front : after;
            after = front + a[i];
            front = front_new;
        }
        return after > front ? after : front;
    }

    /**
     * 1/2/5组合10算法
     */
    public static void combineCents() {
        int one = 1, two = 2, five = 5, ten = 10;
        if (ten % five == 0) {
            for (int j = 0, loop = ten / five; j < loop; ++j) {
                System.out.print(five + " ");
            }
            System.out.println();
        }
        if (ten % two == 0) {
            for (int j = 0, loop = ten / two; j < loop; ++j) {
                System.out.print(two + " ");
            }
            System.out.println();
        }
        if (ten % one == 0) {
            for (int j = 0, loop = ten / one; j < loop; ++j) {
                System.out.print(one + " ");
            }
            System.out.println();
        }
        // 混合组合
        for (int i = 1; i < ten; ++i) {
            int left = ten - i * one;
            //剩余数值可整除5
            if (left % five == 0) {
                for (int j = 0; j < i; ++j) {
                    System.out.print(one + " ");
                }
                for (int j = 0, loop = left / five; j < loop; ++j) {
                    System.out.print(five + " ");
                }
                System.out.println();
            }
            //剩余数值可整除2
            if (left % two == 0) {
                for (int j = 0; j < i; ++j) {
                    System.out.print(one + " ");
                }
                for (int j = 0, loop = left / two; j < loop; ++j) {
                    System.out.print(two + " ");
                }
                System.out.println();
            } else {
                left -= five * i;
                if (left % two == 0 && left > 0) {
                    for (int j = 0; j < i; ++j) {
                        System.out.print(one + " ");
                    }
                    for (int j = 0, loop = left / two; j < loop; ++j) {
                        System.out.print(two + " ");
                    }
                    for (int j = 0; j < i; ++j) {
                        System.out.print(five + " ");
                    }
                    System.out.println();
                }
            }

        }
    }

    /**
     * 一段时期内出生人口男女比例：受重男轻女思想影响，假定头胎生女的夫妇会继续生孩子，直至生男孩为止，一旦头胎生男孩就立即不生育
     *
     */
    public static void sexRate(){
        while (true) {
            int maleCount = 0, femaleCount = 0;
            final int MALE = 0, FEMALE = 1;
            long b = System.currentTimeMillis();
            while (System.currentTimeMillis() - b < 1000) {
                Random random = new Random();
                int sex = random.nextInt(2);
                if (sex == MALE) {
                    maleCount++;
                } else if (sex == FEMALE) {
                    femaleCount++;
                    // 生女：则该夫妻继续生直至生男为止
                    while (true) {
                        random = new Random();
                        sex = random.nextInt(2);
                        if (sex == MALE) {
                            maleCount++;
                            break;
                        } else if (sex == FEMALE) {
                            femaleCount++;
                        }
                    }
                }
            }
            System.out.println("-----------");
            System.out.println("男：" + maleCount);
            System.out.println("女：" + femaleCount);
            System.out.println("差额：" + (femaleCount - maleCount));
            if (maleCount >= femaleCount) {
                break;
            }
        }
    }

    public void topTenTest() {
        final int length = 100000000;
        int numbers[] = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; ++i) {
            numbers[i] = random.nextInt(length * 50) + 1;
        }
        final int top = 5900;
        List<Integer> tops1 = new ArrayList<Integer>();
        List<Integer> tops2 = new ArrayList<Integer>();
        List<Integer> tops3 = new ArrayList<Integer>();
        initialTop(numbers, tops1, top);
        for (int num : tops1) {
            tops2.add(num);
            tops3.add(num);
        }
        System.out.println("---------固定列表存储方式1：从大到小比较--------");
        long b = System.currentTimeMillis();
        topTen(numbers, tops1, top);
        long e = System.currentTimeMillis();
        System.out.print("耗时：" + (e - b) + "ms\n前" + top + "：");
        if (top <= 100) {
            for (int i = 0, size = tops1.size(); i < size; ++i) {
                System.out.print(tops1.get(i) + " ");
            }
        }
        System.out.println("\n\n\n---------固定列表存储方式2：从小到大比较--------");
        b = System.currentTimeMillis();
        topTen2(numbers, tops2, top);
        e = System.currentTimeMillis();
        System.out.print("耗时：" + (e - b) + "ms\n前" + top + "：");
        if (top <= 100) {
            for (int i = 0, size = tops2.size(); i < size; ++i) {
                System.out.print(tops2.get(i) + " ");
            }
        }
        System.out.println("\n\n\n---------固定列表存储方式3：二分插入--------");
        b = System.currentTimeMillis();
        topTen3(numbers, tops3, top);
        e = System.currentTimeMillis();
        System.out.print("耗时：" + (e - b) + "ms\n前" + top + "：");
        if (top <= 100) {
            for (int i = 0, size = tops3.size(); i < size; ++i) {
                System.out.print(tops3.get(i) + " ");
            }
        }
        for (int i = 0, size = tops1.size(); i < size; ++i) {
            int n1 = tops1.get(i);
            int n2 = tops2.get(i);
            int n3 = tops3.get(i);
            if (!(n1 == n2 && n2 == n3)) {
                System.out.println("三次取前十的数据不一致");
            }
        }
        // System.out.println();
        // b = System.currentTimeMillis();
        // algrithm.quicksort(numbers, 0, length - 1);
        // e = System.currentTimeMillis();
        // System.out.print("---------快速排序方式--------\n耗时：" + (e - b) +
        // "ms\n前10：");
        // int max = numbers[0];
        // for (int i = length - 10; i < length; ++i) {
        // System.out.print(numbers[i] + " ");
        // // if (max > numbers[i]) {
        // // System.out.println("停止");
        // // } else {
        // // max = numbers[i];
        // // }
        // }
    }

    /**
     * 初始化前top数组
     *
     * @param numbers
     * @param tops
     * @param top
     */
    private void initialTop(int[] numbers, List<Integer> tops, int top) {
        long a = System.currentTimeMillis();
        tops.add(numbers[0]);
        // 先将前top位排好序放到固定大小的List中
        for (int i = 1; i < top; ++i) {
            int j = i;
            for (int index = tops.size() - 1; index >= 0; --index) {
                if (numbers[i] < tops.get(index)) {
                    j--;
                } else {
                    break;
                }

            }
            tops.add(j, numbers[i]);
        }
        long b = System.currentTimeMillis();
        for (int num : tops) {
            System.out.print(num + ",");
        }
        System.out.println("\n初始化耗时：" + (b - a));
    }

    /**
     * 前10大：从大到小比较
     *
     * @param numbers
     */
    public void topTen(int numbers[], List<Integer> tops, int top) {
        // initialTop(numbers, tops, top);
        // int count = 0;
        long a = System.currentTimeMillis();
        // 遍历原数组后续元素，跟排好序的固定list进行逐一比较，一旦原数组后续元素比固定list中任一元素大，则插到对应位置，并移除最小元素
        for (int i = top; i < numbers.length; ++i) {
            if (numbers[i] <= tops.get(0)) {
                continue;
            }
            for (int index = top - 1; index >= 0; --index) {
                if (numbers[i] >= tops.get(index)) {
                    tops.remove(0);
                    tops.add(index, numbers[i]);
                    break;
                }
                // count++;
            }
        }
        long b = System.currentTimeMillis();
        System.out.println("取耗时：" + (b - a));
        // System.out.println("计算次数：" + count);
    }

    /**
     * 前10大：从小到大比较
     *
     * @param numbers
     */
    public void topTen2(int numbers[], List<Integer> tops, int top) {
        // initialTop(numbers, tops, top);
        // int count = 0;
        // 遍历原数组后续元素，跟排好序的固定list进行逐一比较，一旦原数组后续元素比固定list中任一元素大，则插到对应位置，并移除最小元素
        long a = System.currentTimeMillis();
        for (int i = top; i < numbers.length; ++i) {
            if (numbers[i] <= tops.get(0)) {
                continue;
            }
            if (numbers[i] >= tops.get(top - 1)) {
                tops.add(top, numbers[i]);
                tops.remove(0);
                continue;
            }
            for (int index = 0; index < top; ++index) {
                if (numbers[i] <= tops.get(index)) {
                    if (index > 0) {
                        tops.add(index, numbers[i]);
                        tops.remove(0);
                    }
                    break;
                }
                // count++;
            }
        }
        long b = System.currentTimeMillis();
        System.out.println("取耗时：" + (b - a));
        // System.out.println("计算次数：" + count);
    }

    /**
     * 前10大:二分插入
     *
     * @param numbers
     */
    public void topTen3(int numbers[], List<Integer> tops, int top) {
        // initialTop(numbers, tops, top);
        // int count = 0;
        long a = System.currentTimeMillis();
        // 遍历原数组后续元素，跟排好序的固定list进行逐一比较，一旦原数组后续元素比固定list中任一元素大，则插到对应位置，并移除最小元素
        for (int i = top; i < numbers.length; ++i) {
            if (numbers[i] <= tops.get(0)) {
                continue;
            }
            int p = top / 2;
            int l = 0;
            int r = top;
            while (true) {
                if (r == p || l == p) {
                    tops.remove(0);
                    tops.add(p, numbers[i]);
                    break;
                }
                // count++;
                if (numbers[i] <= tops.get(p)) {
                    r = p;
                    p = (p + l) / 2;
                } else {
                    l = p;
                    p = (p + r) / 2;
                }
            }
        }
        long b = System.currentTimeMillis();
        System.out.println("取耗时：" + (b - a));
        // System.out.println("计算次数：" + count);
    }

    /**
     * 快速排序
     *
     * @param numbers
     * @param left
     * @param right
     */
    public void quicksort(int numbers[], int left, int right) {
        if (left < right) {
            int key = numbers[left];
            int low = left;
            int high = right;
            while (low < high) {
                while (low < high && numbers[high] > key) {
                    high--;
                }
                numbers[low] = numbers[high];
                while (low < high && numbers[low] <= key) {
                    low++;
                }
                numbers[high] = numbers[low];
            }
            numbers[low] = key;
            quicksort(numbers, left, low - 1);
            quicksort(numbers, low + 1, right);
        }
    }

}
