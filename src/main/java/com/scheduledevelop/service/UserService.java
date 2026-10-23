package com.scheduledevelop.service;

import com.scheduledevelop.dto.UserCreateRequestDto;
import com.scheduledevelop.dto.UserCreateResponseDto;
import com.scheduledevelop.dto.UserGetDetailResponseDto;
import com.scheduledevelop.entity.User;
import com.scheduledevelop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

    //속성
    private final UserRepository userRepository;

    //생성자
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    //기능

    // 1. 유저 생성
    @Transactional
    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {

        /*
         * 1. 데이터 저장을 하기 위해선 1대1 매핑이 되는 엔티티를 이용한다.
         * 1-1. 데이터 저장을 하기 위해서 클라이언트 요청으로 받아온 걸 꺼내온다.
         */
        String name = requestDto.getName();
        String email = requestDto.getEmail();
        User user = new User(name, email);

        // 1-2. 저장한 값을 반환 해주기 위해 받아오기
        User savedUser = userRepository.save(user);

        // 1-3. 반환 할 값 꺼내오기
        Long id = savedUser.getId();

        // 1-4. 반환 해줄 값 dto로 구분해서 담아주기
        UserCreateResponseDto responseDto = new UserCreateResponseDto(id);

        // 1.5 반환
        return responseDto;

    }

    // 2. 유저 단 건 조회
    @Transactional(readOnly = true)
    public UserGetDetailResponseDto getDetailUser(Long userId) {

        // 2-1. 조회 후 반환 된 데이터 받기
        User foundUser = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("not found user")
        );

        // 2-2. 반환 할 값 꺼내오기
        Long id = foundUser.getId();
        String name = foundUser.getName();
        String email = foundUser.getEmail();
        LocalDateTime createAt = foundUser.getCreateAt();
        LocalDateTime updateAt = foundUser.getUpdateAt();

        // 2-3. 반환 값 dto 담기
        UserGetDetailResponseDto responseDto = new UserGetDetailResponseDto(id,name,email,createAt,updateAt);

        // 2-4. 반환
        return responseDto;





    }
}
