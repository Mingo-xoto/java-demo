package com.yhq.structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StructsDemo {

    public static char[] chars = {'(',')','[',']','{','}','（','）'};
    public static Map<Character,Character> leftMap = new HashMap<>();
    public static Map<Character,Character> rightMap = new HashMap<>();

    static {
        for(int i = 0; i < chars.length;i=i+2){
            leftMap.put(chars[i],chars[i+1]);
            rightMap.put(chars[i+1],chars[i]);
        }
    }

    public static class Stack {
        private int pointer = -1;
        private ArrayList<Character> exps;

        public int getPointer() {
            return pointer;
        }

        public void show(){
            for(int i = 0;i <= pointer;++i){
                System.out.print(exps.get(i)+",");
            }
            System.out.println();
        }

        public void push(char exp){
            if(exps == null){
                exps = new ArrayList<>();
            }
            exps.add(++pointer,exp);
            System.out.print("入栈："+exp+"，指针："+pointer+",当前栈内元素：");
            show();
        }

        public char pop(){
            if(isEmpty()){
                throw new RuntimeException("栈已空");
            }
            char e = exps.get(pointer--);
            System.out.print("出栈："+e+"，指针："+pointer+",当前栈内元素：");
            show();
            return e;
        }

        public char top(){
            return exps.get(pointer);
        }

        public boolean isEmpty(){
            return pointer < 0;
        }
    }

    public static void main(String[] args) {
        String exps = "a*{b+c/(d-e)+f*[g+h*(i+j+k）]}";
        checkBrackets(exps);
    }

    /**
     * 表达式括号平衡检测
     * @Author : YHQ
     * @Date: 11:37 2019/2/13
     *
     */
    private static void checkBrackets(String exps) {
        Stack stack = new Stack();
        for(int i = 0;i < exps.length();++i){
            char bracket = exps.charAt(i);
            if(leftMap.containsKey(bracket)){
                stack.push(bracket);
            }else if(rightMap.containsKey(bracket)){
                try {
                    char tmpTop = stack.pop();
                    char leftBracket = rightMap.get(bracket);
                    if (tmpTop != leftBracket) {
                        System.out.println(String.format("%c对应开括号不匹配，需要的是%c,出栈的是%c", bracket, leftBracket, tmpTop));
                    }
                }catch (RuntimeException e){
                    throw new RuntimeException("表达式中的括号不平衡，缺少开括号");
                }
            }
        }
        if(!stack.isEmpty()){
            System.out.println("表达式中的括号不平衡");
        }
    }
}
