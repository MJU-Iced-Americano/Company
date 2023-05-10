package com.mju.course.domain.service;

import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Exception.ExceptionList;
import com.mju.course.domain.model.other.Result.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionService {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult unknown(Exception e){
        log.error("unknown exception", e);
        return responseService.getFailResult(ExceptionList.UNKNOWN.getCode(), ExceptionList.UNKNOWN.getMessage());
    }

    @ExceptionHandler(CourseException.class)
    protected CommonResult courseException(CourseException e){
        log.error("duplication course exception", e);
        ExceptionList exceptionList = e.getExceptionList();
        return responseService.getFailResult(exceptionList.getCode(),exceptionList.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected CommonResult validException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException : " + e.getMessage());
        return responseService.getFailResult(HttpStatus.BAD_REQUEST.value(), e.getFieldError().getDefaultMessage());
    }

}
