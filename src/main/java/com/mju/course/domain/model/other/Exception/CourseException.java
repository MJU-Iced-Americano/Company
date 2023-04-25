package com.mju.course.domain.model.other.Exception;

import com.mju.course.domain.model.other.Exception.ExceptionList;
import lombok.Getter;

@Getter
public class CourseException extends RuntimeException{
    private final ExceptionList exceptionList;

    public CourseException(ExceptionList exceptionList) {
        super(exceptionList.getMessage());
        this.exceptionList = exceptionList;
    }

}
