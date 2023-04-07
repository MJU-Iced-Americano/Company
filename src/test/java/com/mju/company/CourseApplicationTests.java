package com.mju.company;

import com.mju.course.domain.model.Company;
import com.mju.course.domain.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseApplicationTests {

	@Autowired
	CompanyRepository companyRepository;

	@Test
	void contextLoads() {
		Company company = new Company("test", "test");
		companyRepository.save(company);
	}

}
