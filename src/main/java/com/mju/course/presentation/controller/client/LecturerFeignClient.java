package com.mju.course.presentation.controller.client;

import com.mju.course.presentation.dto.response.LecturerInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "lecture-feign", url = "http://15.165.249.107:8080")  // 15.165.249.107
public interface LecturerFeignClient {

    @GetMapping("/company-service/lecturer/{lecturer_index}")
    LecturerInfoDto readLecturer(@PathVariable("lecturer_index") Long lecturer_index);

    @GetMapping("/company-service/lecture/user")
    LecturerInfoDto readLecturerByUserId(@RequestParam("id") String user_index);

}
