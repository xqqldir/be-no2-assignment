package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public void create(ScheduleRequestDto dto) {
        scheduleRepository.save(dto);
    }

    public List<ScheduleResponseDto> getAll() {
        return scheduleRepository.findAll();
    }

    public ScheduleResponseDto getById(Long id) {
        return scheduleRepository.findById(id);
    }

    // Lv2: 수정 로직 추가
    public void update(Long id, ScheduleRequestDto dto) {
        String password = scheduleRepository.findPasswordById(id);
        if (!dto.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.update(id, dto);
    }

    // Lv2: 삭제 로직 추가
    public void delete(Long id, ScheduleRequestDto dto) {
        String password = scheduleRepository.findPasswordById(id);
        if (!dto.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.delete(id);
    }
}
