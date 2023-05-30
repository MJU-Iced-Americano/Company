package com.mju.course.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.course.presentation.controller.UserFeignClient;
import com.mju.course.presentation.dto.response.UserInfoDto;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.HttpCookie;
import java.text.ParseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl {

    private final UserFeignClient userFeignClient;
    private final ObjectMapper objectMapper;

    public UserInfoDto readUserInfo(){
        UserInfoDto userInfoDto = userFeignClient.getUserInfo("651e95af-53ea-4458-96f4-2d7fd244b42d");
        return userInfoDto;
    }

    /**
     * Header에서 X-ACCESS-TOKEN 으로 JWT 추출
     * @return String
    */
    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    private String extractLoginUserId(final HttpCookie ssoToken) throws ParseException, JsonProcessingException {
        String tokenPayload = SignedJWT.parse(ssoToken.getValue()).getPayload().toString();
        return objectMapper.readTree(tokenPayload).path("sub").asText();
    }

}
