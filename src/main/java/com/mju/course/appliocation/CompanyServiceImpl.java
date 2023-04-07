package com.mju.course.appliocation;

import com.mju.course.domain.model.Company;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.CompanyRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.CompanyEnrollmentDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final ResponseService responseService;

    @Transactional
    public CommonResult enrollment(CompanyEnrollmentDto companyEnrollmentDto) {
        Company company = new Company(companyEnrollmentDto.getName(), companyEnrollmentDto.getUrl());
        companyRepository.save(company);
        return responseService.getSuccessfulResult();
    }
}
