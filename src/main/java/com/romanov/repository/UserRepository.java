package com.romanov.repository;

import com.romanov.model.User;
import com.romanov.model.UserStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Transactional
    public void createNewStudent(User user, UserStudent userStudent) {
        final String sql = "INSERT INTO heroku_2f77cfed4c2105d.user (`username`, `password`, `name`, `surname`, `patronymic`, `mail`, `role`) VALUES (:userName, :password, :name, :surname, :patronymic, :mail, 'ROLE_STUDENT');";
        final String insertStudent = "INSERT INTO";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource ();
        namedParameters.addValue("username", user.getUserName());
        namedParameters.addValue("password", user.getPassword());
        namedParameters.addValue("name", user.getName());
        namedParameters.addValue("surname", user.getSurname());
        namedParameters.addValue("patronymic", user.getPatronymic());
        namedParameters.addValue("mail", user.getEmail());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,namedParameters,keyHolder, new String[]{"id"});
        keyHolder.getKey();
    }

    public User getUserByUserName(String name) {
        String sql = "select * from heroku_2f77cfed4c2105d.users where username = :name";
        Map namedParameters = new HashMap<>();
        namedParameters.put("name", name);
        List<User> users;
        users = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setPassword(resultSet.getString("password"));
                user.setPatronymic(resultSet.getString("patronymic"));
                user.setEmail(resultSet.getString("mail"));
                user.setSurname(resultSet.getString("surname"));
                user.setUserName(resultSet.getString("username"));
                user.setName(resultSet.getString("name"));
                user.setRole(resultSet.getString("role"));
                return user;
            }
        });
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }
}