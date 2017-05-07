package com.romanov.repository;

import com.romanov.model.User;
import com.romanov.model.UserStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kirill on 07.05.2017.
 */
@Repository
public class UserRepository {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createStudent(User user, UserStudent userStudent) {
        String sql = "INSERT INTO heroku_2f77cfed4c2105d.user () VALUES ();";

    }

    public User getUserByUserName (String name) {
        String sql = "select * from heroku_2f77cfed4c2105d.users where = :name";
        Map namedPatameters = new HashMap<>();
        namedPatameters.put("name", name);
        namedParameterJdbcTemplate.queryForObject(sql, namedPatameters, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User(resultSet.getInt(""))
            }
        });
    }
}
