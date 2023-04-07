package com.mju.course.appliocation;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.CompanyEnrollmentDto;

public interface CompanyService {
    public CommonResult enrollment(CompanyEnrollmentDto companyEnrollmentDto);
}
