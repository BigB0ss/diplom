package com.romanov.repository;


import com.romanov.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 12.03.2017.
 */

@Repository
public class TypeTechnicalTaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Type> getAllTypes() {
        List<Type> types = new ArrayList<>();
        String sql = "SELECT * FROM heroku_2f77cfed4c2105d.type;";
        types = jdbcTemplate.query(sql, new RowMapper<Type>() {
            @Override
            public Type mapRow(ResultSet resultSet, int i) throws SQLException {
                Type type = new Type();
                type.setId(resultSet.getInt("id"));
                type.setType(resultSet.getString("type"));
                return type;
            }
        });
        return types;
    }

    public Type getTypeById(Long id) {
        String sql = "SELECT * FROM heroku_2f77cfed4c2105d.type where id=?";
        Type type = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Type>() {
            @Override
            public Type mapRow(ResultSet resultSet, int i) throws SQLException {
                Type type1 = new Type();
                type1.setId(resultSet.getInt("id"));
                type1.setType(resultSet.getString("type"));
                return type1;
            }
        });
        return type;
    }
}


