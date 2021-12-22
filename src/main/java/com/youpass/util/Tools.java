package com.youpass.util;

import com.youpass.model.OptionInfo;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class Tools {

    public static void main(String[] args) {
        List<Integer> givenList = Arrays.asList(1, 2, 3,4,5,6);
//        Collections.shuffle(givenList);

        int randomSeriesLength = 6;

        List<Integer> randomSeries = rand_order_index(0, "10010");
        System.out.println(randomSeries);
    }

    //传入答案 进行编码
    public static List<Integer> rand_order_index(Integer order, String stu_answer) {
        int length = stu_answer.length();
        if (length == 0) {
            return null;
        }
        List<Integer> standard_answer = new ArrayList<>();
        char[] chars = stu_answer.toCharArray();
        for (char ch : chars) {
            standard_answer.add(Character.getNumericValue(ch));
        }
        switch (order) {
            case 1:
                //前移
                int first1 = standard_answer.get(0);
                standard_answer.remove(0);
                standard_answer.add(first1);
                break;
            case 2:
                //后移
                standard_answer.add(0, standard_answer.get(standard_answer.size() - 1));
                standard_answer.remove(standard_answer.size() - 1);
                break;
            case 3:
                // 前逆
                int first3 = standard_answer.get(0);
                standard_answer.remove(0);
                standard_answer.add(first3);
                Collections.reverse(standard_answer);
                break;
            case 4:
                // 后逆
                standard_answer.add(0, standard_answer.get(standard_answer.size() - 1));
                standard_answer.remove(standard_answer.size() - 1);
                Collections.reverse(standard_answer);
                break;
            case 5:
                ///逆序
                Collections.reverse(standard_answer);
                break;
            case 6:
                // 原序列
                break;
        }
        return standard_answer;
    }

    // 传入选项list 打乱
    public static List<OptionInfo> rand_order(int order, List<OptionInfo> ans_list) {
        int length = ans_list.size();
        if (length == 0) {
            return null;
        }
        switch (order) {
            case 1:
                //前移
                OptionInfo first1 = ans_list.get(0);
                ans_list.remove(0);
                ans_list.add(first1);
                break;
            case 2:
                //后移
                ans_list.add(0, ans_list.get(ans_list.size() - 1));
                ans_list.remove(ans_list.size() - 1);
                break;
            case 3:
                // 前逆
                OptionInfo first3 = ans_list.get(0);
                ans_list.remove(0);
                ans_list.add(first3);
                Collections.reverse(ans_list);
                break;
            case 4:
                // 后逆
                ans_list.add(0, ans_list.get(ans_list.size() - 1));
                ans_list.remove(ans_list.size() - 1);
                Collections.reverse(ans_list);
                break;
            case 5:
                ///逆序
                Collections.reverse(ans_list);
                break;
            case 6:
                // 原序列
                break;
        }
        return ans_list;
    }

    // 没写呢 ans_list传入打乱顺序 CD {3, 4} 和选项个数5 转化成 00110
    public static String RandOrderIndexReturn(int order, int length, List<Character> ans_list) {
        if (length == 0) {
            return null;
        }
        List<Integer> num_list = new ArrayList<>();
        for (char c : ans_list) {
            int code = c - 'A';
            num_list.add(code);
        }
        List<Character> code_list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (num_list.contains(i)) {
                code_list.add('1');
            } else {
                code_list.add('0');
            }
        }
        System.out.println(code_list);
        switch (order) {
            case 1:
                //前移
                code_list.add(0, code_list.get(code_list.size() - 1));
                code_list.remove(code_list.size() - 1);
                break;
            case 2:
                //后移
                char first1 = code_list.get(0);
                code_list.remove(0);
                code_list.add(first1);
                break;
            case 3:
                // 前逆
                Collections.reverse(code_list);
                code_list.add(0, code_list.get(code_list.size() - 1));
                code_list.remove(code_list.size() - 1);
                break;
            case 4:
                // 后逆
                Collections.reverse(code_list);
                char first3 = code_list.get(0);
                code_list.remove(0);
                code_list.add(first3);
                break;
            case 5:
                ///逆序
                Collections.reverse(code_list);
                break;
            case 6:
                // 原序列
                break;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < length; i++) {
            ans.append(code_list.get(i));
        }
        return ans.toString();
    }
}
