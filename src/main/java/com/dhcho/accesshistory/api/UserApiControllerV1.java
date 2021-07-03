package com.dhcho.accesshistory.api;

import com.dhcho.accesshistory.config.security.JwtTokenProvider;
import com.dhcho.accesshistory.dto.UserRequest;
import com.dhcho.accesshistory.entity.User;
import com.dhcho.accesshistory.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/user")
public class UserApiControllerV1 {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     * @param request
     * @return
     */
    @PostMapping("/signup")
    public Result signup(@RequestBody @Valid UserRequest request) {

        User createUser = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시에는 ROLE_USER 로 설정
                .build();

        userRepository.save(createUser);

        return new Result("success");
    }

    /**
     * 로그인
     * @param request
     * @return
     */
    @PostMapping("/signin")
    public Result signin(@RequestBody @Valid UserRequest request) {
        User findUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email 입니다."));

        if (!passwordEncoder.matches(request.getPassword(), findUser.getPassword())) {
            throw new IllegalStateException("잘못된 비밀번호 입니다.");
        }

        return new Result(jwtTokenProvider.createJwtAccessToken(findUser.getUsername(), findUser.getRoles()));
    }

    /**
     * 로그아웃
     */
    @GetMapping("/signout")
    public void signout() {

    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
