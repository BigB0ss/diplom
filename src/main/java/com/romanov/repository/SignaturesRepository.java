package com.romanov.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Kirill_Romanov1 on 24-May-17.
 */
@Repository
public class SignaturesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void addSignaturesForStudent(int idTechnicalTask, int idStudent) {
        final String sql = "UPDATE `heroku_2f77cfed4c2105d`.`signatures` SET `students_users_id`=:idStudent WHERE `techincal_task_id`=:idTechnicalTask ";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idTechnicalTask", idTechnicalTask);
        source.addValue("idStudent", idStudent);
        namedParameterJdbcTemplate.update(sql, source);
    }
}
