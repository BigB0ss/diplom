package com.romanov.repository;

import com.romanov.model.TechnicalTask;
import com.romanov.model.User;
import com.romanov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Kirill on 08.03.2017.
 */
@Repository
public class TechnicalTaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserService userService;

    public List<TechnicalTask> getAll() {
        List<TechnicalTask> tasks = new ArrayList<>();
        return tasks;
    }

    private final static String ROLE_STUDENT = "ROLE_STUDENT";
    private final static String ROLE_TEACHER = "ROLE_TEACHER";
    private final static String ROLE_ADMIN = "ROLE_ADMIN";

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addTechnicalTask(TechnicalTask task) {
        final String sql = "INSERT INTO heroku_2f77cfed4c2105d.techincal_task (name, target, type_id, date_create,  discipline_id) VALUES (:name,:target, :type_id, :date_create, :discipline_id);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParametrs = new MapSqlParameterSource();
        namedParametrs.addValue("name", task.getName());
        namedParametrs.addValue("target", task.getTarget());
        namedParametrs.addValue("type_id", task.getTypeTechnicalTask());
        namedParametrs.addValue("date_create", task.getDateCreated());
        namedParametrs.addValue("discipline_id", task.getDiscipline());
        namedParameterJdbcTemplate.update(sql, namedParametrs, keyHolder, new String[]{"ID"});
        final String insertTarget = "Insert into heroku_2f77cfed4c2105d.tasks (description,techincal_task_id) VAlUES(:description,:techincal_task_id)";
        for (String t : task.getTasks()) {
            MapSqlParameterSource msqp = new MapSqlParameterSource();
            msqp.addValue("description", t);
            msqp.addValue("techincal_task_id",keyHolder.getKey());
            namedParameterJdbcTemplate.update(insertTarget,msqp);
        }
        addSubSection(task, keyHolder.getKey());
        addSignatures(keyHolder.getKey());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addSubSection(TechnicalTask task, Number idTechnicalTask) {
        final String insertDemand = "Insert into heroku_2f77cfed4c2105d.section (description) VALUES (:description);";
        for (Map.Entry<String, List<String>> entry : task.getDemands().entrySet()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String demand = entry.getKey();
            List<String> subDemand = entry.getValue();
            MapSqlParameterSource namedParametrs = new MapSqlParameterSource();
            namedParametrs.addValue("description", demand);
            namedParameterJdbcTemplate.update(insertDemand, namedParametrs, keyHolder, new String[]{"ID"});
            for (String elem : subDemand) {
                final String inserSubDemands = "Insert into heroku_2f77cfed4c2105d.subsections (description, techincal_task_id, section_id) Values (:description, :techincal_task_id,:section_id ); ";
                MapSqlParameterSource subNamedParametrs = new MapSqlParameterSource();
                subNamedParametrs.addValue("description", elem);
                subNamedParametrs.addValue("techincal_task_id", idTechnicalTask);
                subNamedParametrs.addValue("section_id", keyHolder.getKey());
                namedParameterJdbcTemplate.update(inserSubDemands, subNamedParametrs);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addSignatures(Number idTechnicalTask) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUserName(authentication.getName());
        if (user.getRole().equals(ROLE_TEACHER)|| user.getRole().equals(ROLE_ADMIN)) {
            final String sql = "Insert into heroku_2f77cfed4c2105d.signatures (techincal_task_id, teachers_users_id) VALUES(:techincal_task_id,:teachers_users_id)";
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("techincal_task_id",idTechnicalTask);
            source.addValue("teachers_users_id",user.getId());
            namedParameterJdbcTemplate.update(sql,source);
        } else {
            final String sql = "Insert into heroku_2f77cfed4c2105d.signatures (techincal_task_id, students_users_id) VALUES(:techincal_task_id,:students_users_id)";
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("techincal_task_id",idTechnicalTask);
            source.addValue("students_users_id",user.getId());
            namedParameterJdbcTemplate.update(sql,source);
        }
    }

}
