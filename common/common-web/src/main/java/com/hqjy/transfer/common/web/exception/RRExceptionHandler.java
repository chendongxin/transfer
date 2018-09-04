package com.hqjy.transfer.common.web.exception;

import com.hqjy.transfer.common.base.exception.RRException;
import com.hqjy.transfer.common.base.utils.R;
import com.hqjy.transfer.common.base.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 *
 * @author : heshuangshuang
 * @date : 2018/3/23 13:25
 */
@RestControllerAdvice
@Slf4j
public class RRExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public R handleRRException(RRException e) {
        return R.ok(e.getCode(), e.getMsg());
    }

    /**
     * 数据库唯一主键冲突
     */
 /*   @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        return R.error(StatusCode.DATABASE_DUPLICATEKEY);
    }
*/

    /**
     * 实体验证
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R validException(MethodArgumentNotValidException mnve) {
        return R.error(mnve.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MultipartException.class)
    public R handleMultipartException(MultipartException ex, HttpServletRequest request) {
        // 处理文件上传过大异常
        log.error("文件上传异常:", ex.getMessage());
        log.error(ex.getMessage());
        return R.error("上传文件大小超出限制:2MB");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error("服务器异常，请将此信息发送管理员").add("exception", Tools.exceptionInfo(e));
    }
}
