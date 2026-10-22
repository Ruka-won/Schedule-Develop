package com.scheduledevelop.controller;

import com.scheduledevelop.dto.*;
import com.scheduledevelop.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    //속성
    private final ScheduleService scheduleService;


    //생성자
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //기능
    // 1. 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleCreateResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto scheduleCreateRequestDto) {

        // 1. 서비스 처리
        ScheduleCreateResponseDto responseDto = this.scheduleService.createSchedule(scheduleCreateRequestDto);

        // 2. 응답 객체 생성
        ResponseEntity<ScheduleCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);

        // 3. 반환
        return response;
    }
    // 2. 일정 단 건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleGetResponseDto> getDetailSchedule(@PathVariable("scheduleId") Long scheduleId) {

        // 1. 서비스 처리 - 일정 조회
        ScheduleGetResponseDto responseDto = this.scheduleService.getScheduleDetail(scheduleId);

        // 2. 응답 객체 생성
        ResponseEntity<ScheduleGetResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);

        // 3. 반환
        return response;
    }

    // 3. 일정 다 건 조회
    @GetMapping
    public ResponseEntity<ScheduleGetAllResponseDto> getAllSchedule() {

        // 1. 서비스 처리
        ScheduleGetAllResponseDto responseDto =  scheduleService.getScheduleAll();

        // 2. 응답 객체 생성
        ResponseEntity<ScheduleGetAllResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);

        // 3. 반환
        return response;

    }

    // 4. 일정 수정
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto requestDto) {

        // 1. 서비스 처리
        ScheduleUpdateResponseDto responseDto = scheduleService.updateSchedule(scheduleId, requestDto);

        // 2. 응답 객체 생성
        ResponseEntity<ScheduleUpdateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);

        // 3. 반환
        return response;



    }
    // 5. 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDeleteResponseDto> deleteSchedule(@PathVariable Long scheduleId) {

        // 1. 서비스 처리
        ScheduleDeleteResponseDto responseDto = scheduleService.deleteSchedule(scheduleId);

        // 2. 응답 객체 생성
        ResponseEntity<ScheduleDeleteResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);

        // 3. 반환
        return response;

    }
}