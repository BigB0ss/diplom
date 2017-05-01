package com.romanov.repository;

import com.romanov.model.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirill on 22.03.2017.
 */

@Repository
public class DisciplineRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Discipline> getAll(){
        List<Discipline> types=new ArrayList<>();
        String sql="SELECT * FROM tt_v2.discipline;";
        types=jdbcTemplate.query(sql, new RowMapper<Discipline>() {
            @Override
            public Discipline mapRow(ResultSet resultSet, int i) throws SQLException {
                Discipline discipline=new Discipline();
                discipline.setId(resultSet.getInt("id"));
                discipline.setName(resultSet.getString("name"));
                discipline.setDescription(resultSet.getString("description"));
                return discipline;
            }
        });
        return types;
    }
}
