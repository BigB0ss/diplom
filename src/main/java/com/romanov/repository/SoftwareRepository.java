package com.romanov.repository;

import com.romanov.model.Software;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill_Romanov1 on 30-Mar-17.
 */

@Repository
public class SoftwareRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Software> getAll() {
        List<Software> softwares = new ArrayList<>();
        String sql = "SELECT * FROM tt_v2.software;";
        softwares = jdbcTemplate.query(sql, new RowMapper<Software>() {
            @Override
            public Software mapRow(ResultSet resultSet, int i) throws SQLException {
                Software soft = new Software();
                soft.setId(resultSet.getInt("id"));
                soft.setDescription(resultSet.getString("description"));
                return soft;
            }
        });
        return softwares;
    }

    public Software add(Software software) {
        String sql = "INSERT INTO tt_v2.software (description,categorysoft_id) VALUES (:description, :categorysoft_id);";
        Map namedParameter = new HashMap();
        namedParameter.put("description", software.getDescription());
        namedParameter.put("categorysoft_id", software.getCategorySoft());
        namedParameterJdbcTemplate.update(sql, namedParameter);
        return software;
    }
}
