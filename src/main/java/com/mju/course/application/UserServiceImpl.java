package com.mju.course.application;

import com.mju.course.domain.model.User;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.mju.course.domain.model.other.Exception.ExceptionList.*;

@Service
@Slf4j
public record UserServiceImpl(UserRepository userRepository) {

    /**
     * Header 에서 X-AUTH 로 유저 정보 추출
     * @return String
     */
    public String getHeader() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-AUTH");
    }

    public void checkUser(String userType) {
        String userId = getHeader();

        if (userId == null || userId.length() == 0) {
            throw new CourseException(EMPTY_USER);
        }

        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_USER));

        String a = String.valueOf(user.getUserType());
        if (!a.equals(userType)) {
            throw new CourseException(NOT_ACCESS_USER);
        }
    }
}
