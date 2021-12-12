package com.youpass.util.ReturnType.Result;

import java.util.List;

public class ResultUtil {

    /**
     *
     * @param object 待返回给前端的数据
     * @return Result类型对象，包括状态码code，信息message，数据data
     */
    public static Result<Object> success(Object object){
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result<Object> success(){
        return success(null);
    }
    public static Result<Object> error(Integer code ,String msg){
        Result<Object>result =new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static Result<Object> error(ResultEnum resultEnum){
        Result<Object>result =new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

}
