package com.romanov.repository;

import com.romanov.model.Cathedra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kirill on 11.05.2017.
 */
@Repository
public class CathedraRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Cathedra> getAllCathedres(){
        final String sql = "Select * from heroku_2f77cfed4c2105d.cathedra;";
        return jdbcTemplate.query(sql, new RowMapper<Cathedra>() {
            @Override
            public Cathedra mapRow(ResultSet resultSet, int i) throws SQLException {
                Cathedra cathedra = new Cathedra();
                cathedra.setId(resultSet.getInt("id"));
                cathedra.setName(resultSet.getString("name"));
                cathedra.setIdFaculty(resultSet.getInt("faculty_id"));
                return cathedra;
            }
        });
    }
}
