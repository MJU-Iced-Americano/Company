package com.mju.course.presentation.controller;

import com.mju.course.application.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;

class CourseControllerTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testReadSearch() {
//        HttpCookie cookie = new HttpCookie("ssoToken", "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJSbjRROGRvdTRSdlQ0dDFxUWNEeFFhZjF3RVVhNG9vSW9CRUg2aHpoNVg4In0.eyJleHAiOjE2ODU0NzMxODUsImlhdCI6MTY4NTQ3MzEyNSwiYXV0aF90aW1lIjoxNjg1NDczMTI1LCJqdGkiOiIzMWE2YWE5Yi1jZmM4LTQ1MTctOTY1ZS0wNjAwNmM1NDBhYjciLCJpc3MiOiJodHRwOi8vc29jb2EtbG9naW4uc2l0ZTo4MDgwL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZjo0MjAwZmU4Yi1jNDQ5LTQwZmQtYTU3Mi00ZTU2MDE0M2RlY2Q6MGYxODdlNmUtMGVjMi00Mjc2LWE5ZTctNzljYWZlYjhkYTE1IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYWRtaW4tY2xpZW50Iiwibm9uY2UiOiJhc2IzIiwic2Vzc2lvbl9zdGF0ZSI6ImQzNWVlNjUyLWEwNmItNDUzNi1iMWI1LTA2OWEyMzliMTk4MSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL3NvY29hLWxvZ2luLnNpdGUvKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJzaWQiOiJkMzVlZTY1Mi1hMDZiLTQ1MzYtYjFiNS0wNjlhMjM5YjE5ODEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImdlbmRlciI6IkZFTUFMRSIsIm5pY2tuYW1lIjoiaGFwcHkiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJoYXBweSIsImVtYWlsIjoiaGFwcHlAbmF2ZXIuY29tIn0.BeeMvhLdHZq-ZMBRqhlUaHXrCBKYs0XpengkSkhUVId_cC-HgCCGSwsOp09zghDmoae5mFQF3JalOoP7tUc_gDl-2vcwGXCXjwjWT5KGlATZf1pme2aSOBMbm822_YPvE9pKfV-4g0ZoSpKMAn1-Ox0w4eLXMxXTh9n4H2Ircw-AaX2B5ZkAJJ-M4J9KlWzvnwxol2kz-ksLp83by0myIEYXdSy8g9uaRxnmojG4qlHhpZ4ZKXZf3jAUlp3WuUpPdgy0rK-04MUzonEpL7oWSlj2ZJ8M74F48n1khnkbL3DYxBzMs1qdjKa4P1YzNqegQVNCzNGpC-SGa6tfeLcz1Q");
//
//        String a = userService.getUserId(cookie);
//
//        Assertions.assertEquals("f:4200fe8b-c449-40fd-a572-4e560143decd:0f187e6e-0ec2-4276-a9e7-79cafeb8da15", a);

        // 테스트 결과에 대한 검증 로직 추가
    }

}