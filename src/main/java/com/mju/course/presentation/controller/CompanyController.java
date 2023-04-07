package com.mju.course.presentation.controller;

import com.mju.course.appliocation.CompanyService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.CompanyEnrollmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company-service")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/enrollment")
    public CommonResult enrollment(@RequestBody CompanyEnrollmentDto companyEnrollmentDto) {
        return companyService.enrollment(companyEnrollmentDto);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
