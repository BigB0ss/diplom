package com.romanov.repository;

import com.romanov.model.Hardware;
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
 * Created by Kirill_Romanov1 on 31-Mar-17.
 */

@Repository
public class HardwareRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Hardware> getAll() {
        List<Hardware> hardwares = new ArrayList<>();
        String sql = "SELECT * FROM heroku_2f77cfed4c2105d.hardware;";
        hardwares = jdbcTemplate.query(sql, new RowMapper<Hardware>() {
            @Override
            public Hardware mapRow(ResultSet resultSet, int i) throws SQLException {
                Hardware hardware = new Hardware();
                hardware.setId(resultSet.getInt("id"));
                hardware.setDescription(resultSet.getString("description"));
                return hardware;
            }
        });
        return hardwares;
    }

    public Hardware add(Hardware hardware) {
        String sql = "INSERT INTO heroku_2f77cfed4c2105d.hardware (description,id_hardware_cat) VALUES (:description, :id_hardware_cat);";
        Map namedParameter = new HashMap();
        namedParameter.put("description", hardware.getDescription());
        namedParameter.put("id_hardware_cat", hardware.getIdCategory());
        namedParameterJdbcTemplate.update(sql, namedParameter);
        return hardware;
    }
}
