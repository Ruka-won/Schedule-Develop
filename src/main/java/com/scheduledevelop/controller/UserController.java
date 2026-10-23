package com.scheduledevelop.controller;

import com.scheduledevelop.dto.UserCreateRequestDto;
import com.scheduledevelop.dto.UserCreateResponseDto;
import com.scheduledevelop.dto.UserGetDetailResponseDto;
import com.scheduledevelop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    //속성
    private final UserService userService;


    //생성자
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //기능

    /*
     * 1. 유저 생성
     * 1-1. 유저 생성을 하기 위해선 클라이언트에게 무엇을 제공 받아야하는지(@RequestBody)
     */
    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUser(@RequestBody UserCreateRequestDto requestDto) {

        // 1-2. 서비스 처리
        UserCreateResponseDto responseDto = userService.createUser(requestDto);

        // 1-3. 응답 객체 생성
        ResponseEntity<UserCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);

        // 1-4. 반환
        return response;
    }

    // 2. 유저 단 건 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserGetDetailResponseDto> getDetailUser(@PathVariable Long userId) {
        System.out.println("userId = " + userId);

        // 2-1. 서비스 처리 - 유저 단 건 조회
        UserGetDetailResponseDto responseDto = userService.getDetailUser(userId);

        // 2-2. 응답 객체 생성
        ResponseEntity<UserGetDetailResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);

        // 2-3. 반환
        return response;

    }
}
