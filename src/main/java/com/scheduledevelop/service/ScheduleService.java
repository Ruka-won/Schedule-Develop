package com.scheduledevelop.service;

import com.scheduledevelop.dto.*;
import com.scheduledevelop.entity.Schedule;
import com.scheduledevelop.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    //속성
    private final ScheduleRepository scheduleRepository;


    //생성자
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //기능
    // 1. 일정 생성
    @Transactional
    public ScheduleCreateResponseDto createSchedule(ScheduleCreateRequestDto scheduleCreateRequestDto) {
        /*
         * 1. 데이터 준비 하고 검증
         */
        String name =  scheduleCreateRequestDto.getName();
        String title = scheduleCreateRequestDto.getTitle();
        String contents = scheduleCreateRequestDto.getContents();
        System.out.println("Verification name = " + name);

        /*
         * 2. 일정을 저장해야겠다.
         * 2-1. 저장을 하기 위해선 DB 1대1 매핑이 되는 엔티티를 생성해야겠다.
         * 2-2. 데이터 저장을 해야하니까 레퍼지토리랑 의존성을 연결시켜줘야겠다.
         */
        Schedule schedule = new Schedule(name, title, contents);

        // 3. 일정 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);


        // 4. 데이터 준비
        Long savedScheduleId = savedSchedule.getId();
        LocalDateTime savedScheduleCreateAt = savedSchedule.getCreateAt();
        LocalDateTime savedScheduleUpdateAt = savedSchedule.getUpdateAt();

        // 5. dto 변환 후 반환
        ScheduleCreateResponseDto responseDto = new ScheduleCreateResponseDto(
                savedScheduleId, savedScheduleCreateAt, savedScheduleUpdateAt);

        // 6. 반환
        return responseDto;
    }
    // 2. 일정 단 건 조회
    @Transactional(readOnly = true)
    public ScheduleGetResponseDto getScheduleDetail(Long scheduleId) {
        // 1. 조회
       Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
       if (optionalSchedule.isEmpty()) {
           throw new RuntimeException("not found schedule");
         }
        Schedule foundSchedule = optionalSchedule.get();

        // 2. 데이터 준비
        Long foundScheduleId = foundSchedule.getId();
        String foundScheduleName = foundSchedule.getName();
        String foundScheduleTitle = foundSchedule.getTitle();
        String foundScheduleContents = foundSchedule.getContents();
        LocalDateTime createAt = foundSchedule.getCreateAt();
        LocalDateTime updateAt = foundSchedule.getUpdateAt();

        // 3. 반환 dto 준비
        ScheduleGetResponseDto responseDto = new ScheduleGetResponseDto(
                foundScheduleId,
                foundScheduleName,
                foundScheduleTitle,
                foundScheduleContents,
                createAt,
                updateAt
                );

        // 4. 반환
        return responseDto;

    }


    // 3. 일정 다 건 조회
    @Transactional(readOnly = true)
    public ScheduleGetAllResponseDto getScheduleAll() {
        // 1. 데이터 준비
        List<Schedule> listSchedule = scheduleRepository.findAll();

        if (listSchedule.isEmpty()) {
            throw new IllegalStateException("not found scheduleList");
        }
        // 2. 반환 데이터 담기
         List<ScheduleGetResponseDto> foundListSchedule = new ArrayList<>();
            for (Schedule schedule : listSchedule) {
                ScheduleGetResponseDto responseDto = new ScheduleGetResponseDto(
                     schedule.getId(),
                     schedule.getName(),
                     schedule.getTitle(),
                     schedule.getContents(),
                     schedule.getCreateAt(),
                     schedule.getUpdateAt()
                );
                foundListSchedule.add(responseDto);
        }
            // 3. 반환
            return new ScheduleGetAllResponseDto(foundListSchedule);
    }
    // 4. 일정 수정
    @Transactional
    public ScheduleUpdateResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        // 1. 데이터 조회
        Schedule foundSchedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("not found schedule")
        );

        // 2. 데이터 준비
        String updateName = requestDto.getName();
        String updateTitle = requestDto.getTitle();
        String updateContents = requestDto.getContents();

        // 3. 데이터 수정
        foundSchedule.updateSchedule(updateName, updateTitle, updateContents);
        // 3-1. 수정일 업데이트
        scheduleRepository.flush();

        // 4. 반환 데이터 준비
        Long id = foundSchedule.getId();
        LocalDateTime createAt = foundSchedule.getCreateAt();
        LocalDateTime updateAt = foundSchedule.getUpdateAt();

        // 4. 반환
        ScheduleUpdateResponseDto responseDto = new ScheduleUpdateResponseDto(
                id, updateName, updateTitle, updateContents, createAt, updateAt);
        return responseDto;
    }
    // 5. 일정 삭제
    @Transactional
    public ScheduleDeleteResponseDto deleteSchedule(Long scheduleId) {

        // 1. 데이터 조회
        Schedule foundSchedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("not found schedule")
        );
        // 2. 데이터 삭제
        scheduleRepository.delete(foundSchedule);

        // 3. 반환
        return new ScheduleDeleteResponseDto("일정이 성공적으로 삭제되었습니다.");

    }
}
