package com.youpass.util;

import com.youpass.model.OptionInfo;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class Tools {
    public static Result<Object> rand_order_index(Integer order, String stu_answer){
        int length = stu_answer.length();
        if (length == 0)
        {
            return ResultUtil.error(ResultEnum.ANSWER_MISS);
        }
        List<Integer> standard_answer = new ArrayList<>();
        char[] chars = stu_answer.toCharArray();
        for (char ch : chars) {
            standard_answer.add(Character.getNumericValue(ch));
        }
        switch (order)
        {
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
        return ResultUtil.success(standard_answer);
    }

    public static Result<Object> rand_order(int order, List<OptionInfo> ans_list){
        int length = ans_list.size();
        if (length == 0)
        {
            return ResultUtil.error(ResultEnum.ANSWER_MISS);
        }
        switch (order)
        {
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
        return ResultUtil.success(ans_list);
    }

    // 没写呢
    public static Result<Object> RandOrderIndexReturn(int order, List<OptionInfo> ans_list){
        int length = ans_list.size();
        if (length == 0)
        {
            return ResultUtil.error(ResultEnum.ANSWER_MISS);
        }
        switch (order)
        {
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
        return ResultUtil.success(ans_list);
    }
}
