package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(ScheduleRequestDto dto) {
        String sql = "INSERT INTO schedule (title, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, dto.getTitle(), dto.getUsername(), dto.getPassword());
    }

    public List<ScheduleResponseDto> findAll() {
        String sql = "SELECT * FROM schedule ORDER BY modified_at DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("username"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                )
        );
    }

    public ScheduleResponseDto findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("username"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                ), id
        );
    }

    public String findPasswordById(Long id) {
        String sql = "SELECT password FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    public void update(Long id, ScheduleRequestDto dto) {
        String sql = "UPDATE schedule SET title = ?, username = ?, modified_at = NOW() WHERE id = ?";
        jdbcTemplate.update(sql, dto.getTitle(), dto.getUsername(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
