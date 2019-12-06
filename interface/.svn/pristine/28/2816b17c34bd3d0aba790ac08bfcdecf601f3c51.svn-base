package com.handongkeji.config.handler;

import com.handongkeji.config.exception.JsonException;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 错误回调
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    // 拦截API异常
    @ExceptionHandler(value = JsonException.class)
    public ResultVo handlerJsonException(JsonException e) {
        // 返回对应的错误信息
        return ResultVOUtils.error(e.getCode(), e.getMessage());
    }

    // 拦截API异常
    @ExceptionHandler(value = RuntimeException.class)
    public ResultVo handlerRuntimeException(RuntimeException e) {
        //log.error(e.getMessage());
        e.printStackTrace();
        // 返回对应的错误信息
        return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
    }
    
}
