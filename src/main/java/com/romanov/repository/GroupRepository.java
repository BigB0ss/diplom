package com.romanov.repository;

import com.romanov.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kirill on 03.05.2017.
 */
@Repository
public class GroupRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Group> getAllGroup() {
        String sql = "SELECT * FROM heroku_2f77cfed4c2105d.group;";
        List<Group> groups = jdbcTemplate.query(sql, new RowMapper<Group>() {
            @Override
            public Group mapRow(ResultSet resultSet, int i) throws SQLException {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                group.setCathedra(resultSet.getInt("cathedra_id"));
                return group;
            }
        });
        return groups;
    }
}