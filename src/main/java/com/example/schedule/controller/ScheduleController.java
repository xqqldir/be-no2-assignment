package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        scheduleService.create(requestDto);
        return ResponseEntity.ok("일정이 성공적으로 생성되었습니다.");
    }

    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAll();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto getScheduleById(@PathVariable Long id) {
        return scheduleService.getById(id);
    }

    // Lv2: 일정 수정 API (비밀번호 일치 시 수정 허용)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        try {
            scheduleService.update(id, dto);
            return ResponseEntity.ok("일정이 성공적으로 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    // Lv2: 일정 삭제 API (비밀번호 일치 시 삭제 허용)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        try {
            scheduleService.delete(id, dto);
            return ResponseEntity.ok("일정이 성공적으로 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
