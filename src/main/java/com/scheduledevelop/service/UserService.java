package com.scheduledevelop.service;

import com.scheduledevelop.dto.*;
import com.scheduledevelop.entity.User;
import com.scheduledevelop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        User savedUser = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("not found user")
        );

        // 2-2. 반환 할 값 꺼내오기
        Long id = savedUser.getId();
        String name = savedUser.getName();
        String email = savedUser.getEmail();
        LocalDateTime createAt = savedUser.getCreateAt();
        LocalDateTime updateAt = savedUser.getUpdateAt();

        // 2-3. 반환 값 dto 담기
        UserGetDetailResponseDto responseDto = new UserGetDetailResponseDto(id,name,email,createAt,updateAt);

        // 2-4. 반환
        return responseDto;





    }
    // 3. 유저 전체 조회
    @Transactional(readOnly = true)
    public UserGetAllResponseDto getUsers() {

        // 1. 조회
        List<User> savedUserList = userRepository.findAll();

        // 2. 검증
        if (savedUserList.isEmpty()) {
            throw new IllegalStateException("not found userList");
        }
        // 3. 데이터 준비
        List<UserGetDetailResponseDto> filterUserList = new ArrayList<>();
        for (User user : savedUserList) {
            UserGetDetailResponseDto responseDto = new UserGetDetailResponseDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreateAt(),
                    user.getUpdateAt()
            );
            filterUserList.add(responseDto);

        }
        // 4. 반환
        return new UserGetAllResponseDto(filterUserList);

    }
    // 4. 유저 수정
    @Transactional
    public UserUpdateResponseDto userUpdate(Long userId, UserUpdateRequestDto request) {

        // 1. 조회
        User savedUser = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("not found user")
        );

        // 2. 수정할 데이터 준비
        String newName = request.getName();
        String newEmail = request.getEmail();

        // 3. 수정
        User updateUser = savedUser.update(newName, newEmail);
        userRepository.flush();

        // 4. 수정 데이터 준비 및 dto 변환
        UserUpdateResponseDto responseDto = new UserUpdateResponseDto(
                updateUser.getId(),
                updateUser.getName(),
                updateUser.getEmail(),
                updateUser.getCreateAt(),
                updateUser.getUpdateAt()
        );
        // 5. 반환
        return responseDto;


    }
    // 5. 유저 삭제
    @Transactional
    public UserDeleteResponseDto userDelete(Long userId) {

        // 1. 조회
        User savedUser = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("not found user")
        );

        // 2. 삭제 - soft delete
        userRepository.delete(savedUser);

        // 3. 반환
        return new UserDeleteResponseDto("유저가 성공적으로 삭제되었습니다.");

    }
}
