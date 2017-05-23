package com.romanov.repository;

import com.romanov.model.User;
import com.romanov.model.UserStudent;
import com.romanov.model.UserTeacher;
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
        final String insertUser = "INSERT INTO heroku_2f77cfed4c2105d.users (`username`, `password`, `name`, `surname`, `patronymic`, `mail`, `role`) VALUES (:username, :password, :name, :surname, :patronymic, :mail, 'ROLE_STUDENT');";
        final String insertStudent = "INSERT INTO heroku_2f77cfed4c2105d.students (users_id, group_id) VALUES(:users_id, :group_id) ";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("username", user.getUserName());
        namedParameters.addValue("password", user.getPassword());
        namedParameters.addValue("name", user.getName());
        namedParameters.addValue("surname", user.getSurname());
        namedParameters.addValue("patronymic", user.getPatronymic());
        namedParameters.addValue("mail", user.getEmail());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertUser, namedParameters, keyHolder, new String[]{"id"});
        Map parameters = new HashMap();
        parameters.put("users_id", keyHolder.getKey());
        parameters.put("group_id", userStudent.getGroup());
        namedParameterJdbcTemplate.update(insertStudent, parameters);
    }

    @Transactional
    public void createNewTeacher(User user, UserTeacher userTeacher) {
        final String insertUser = "INSERT INTO heroku_2f77cfed4c2105d.users (`username`, `password`, `name`, `surname`, `patronymic`, `mail`, `role`) VALUES (:username, :password, :name, :surname, :patronymic, :mail, 'ROLE_TEACHER');";
        final String insertTeacher = "INSERT INTO heroku_2f77cfed4c2105d.teachers (users_id, post, academic_degree, cathedra_id, academic_title) VALUES(:users_id, :post, :academic_degree, :cathedra_id, :academic_title) ";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("username", user.getUserName());
        namedParameters.addValue("password", user.getPassword());
        namedParameters.addValue("name", user.getName());
        namedParameters.addValue("surname", user.getSurname());
        namedParameters.addValue("patronymic", user.getPatronymic());
        namedParameters.addValue("mail", user.getEmail());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertUser, namedParameters, keyHolder, new String[]{"id"});
        Map parameters = new HashMap();
        parameters.put("users_id", keyHolder.getKey());
        parameters.put("post", userTeacher.getPost());
        parameters.put("academic_degree", userTeacher.getAcademicDegree());
        parameters.put("academic_title", userTeacher.getAcademicTitle());
        parameters.put("cathedra_id", userTeacher.getIdCathedra());
        namedParameterJdbcTemplate.update(insertTeacher, parameters);
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

    public User getUserByEmail(String email) {
        final String sql = "select * from heroku_2f77cfed4c2105d.users where mail = :mail";
        Map namedParameters = new HashMap<>();
        namedParameters.put("mail", email);
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

    public User getUserByUserId(int id) {
        String sql = "select * from heroku_2f77cfed4c2105d.users where id = :id";
        Map namedParameters = new HashMap<>();
        namedParameters.put("id", id);
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

    public List<UserStudent> getAllStudent() {
        final String sql = "select id from users inner join heroku_2f77cfed4c2105d.students on users.id = students.users_id";
        List<Integer> userIDs = jdbcTemplate.queryForList(sql, Integer.class);
        List<User> students = new ArrayList<>();
        userIDs.stream().forEach(id -> {
            students.add(getUserByUserId(id));
        });

        final String getGroup = "select users_id, group_id from heroku_2f77cfed4c2105d.students where users_id=?";
        List<UserStudent> students2 = new ArrayList<>();
        students.stream().forEach(student -> {
            jdbcTemplate.query(getGroup, new Object[]{student.getId()}, new RowMapper<UserStudent>() {
                @Override
                public UserStudent mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserStudent userStudent = new UserStudent();
                    userStudent.setUser(student);
                    userStudent.setGroup(resultSet.getInt("group_id"));
                    userStudent.setId(resultSet.getInt("users_id"));
                    students2.add(userStudent);
                    return userStudent;
                }
            });
        });

        final String getGroupName = "SELECT id ,name FROM heroku_2f77cfed4c2105d.`group` WHERE id =?; ";
        students2.stream().forEach(student -> {
            jdbcTemplate.query(getGroupName, new Object[]{student.getGroup()}, new RowMapper<UserStudent>() {
                @Override
                public UserStudent mapRow(ResultSet resultSet, int i) throws SQLException {
                    student.setNameGroup(resultSet.getString("name"));
                    return null;
                }
            });
        });
        return students2;
    }

}
