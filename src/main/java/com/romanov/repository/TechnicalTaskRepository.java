package com.romanov.repository;

import com.romanov.model.Demand;
import com.romanov.model.TechnicalTask;
import com.romanov.model.User;
import com.romanov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Signature;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Transactional
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
            msqp.addValue("techincal_task_id", keyHolder.getKey());
            namedParameterJdbcTemplate.update(insertTarget, msqp);
        }
        addSubSection(task, keyHolder.getKey());
        addSignatures(keyHolder.getKey());
    }

    @Transactional
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

    @Transactional
    public void addSignatures(Number idTechnicalTask) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUserName(authentication.getName());
        if (user.getRole().equals(ROLE_TEACHER) || user.getRole().equals(ROLE_ADMIN)) {
            final String sql = "Insert into heroku_2f77cfed4c2105d.signatures (techincal_task_id, teachers_users_id) VALUES(:techincal_task_id,:teachers_users_id)";
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("techincal_task_id", idTechnicalTask);
            source.addValue("teachers_users_id", user.getId());
            namedParameterJdbcTemplate.update(sql, source);
        } else {
            final String sql = "Insert into heroku_2f77cfed4c2105d.signatures (techincal_task_id, students_users_id) VALUES(:techincal_task_id,:students_users_id)";
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("techincal_task_id", idTechnicalTask);
            source.addValue("students_users_id", user.getId());
            namedParameterJdbcTemplate.update(sql, source);
        }
    }

    @Transactional
    public List<TechnicalTask> getAllTaskForUser(User user) {
        List<TechnicalTask> ttList = new ArrayList<>();
        if (user.getRole().equals("ROLE_STUDENT")) {
            final String sql = "Select techincal_task_id from heroku_2f77cfed4c2105d.signatures where students_users_id = ?;";
            List<Integer> techTaskId = jdbcTemplate.query(sql, new Object[]{user.getId()}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt("techincal_task_id");
                }
            });
            for (Integer ttId : techTaskId) {
                ttList.add(getTechnicalTaskById(ttId));
            }
        }
        if (user.getRole().equals("ROLE_TEACHER") || user.getRole().equals("ROLE_ADMIN")) {
            final String sql = "Select techincal_task_id from heroku_2f77cfed4c2105d.signatures where teachers_users_id = ?;";
            List<Integer> techTaskId = jdbcTemplate.query(sql, new Object[]{user.getId()}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt("techincal_task_id");
                }
            });
            for (Integer ttId : techTaskId) {
                ttList.add(getTechnicalTaskById(ttId));
            }
        }
        return ttList;
    }

    public TechnicalTask getTechnicalTaskById(Integer id) {
        final String sql = "Select * from heroku_2f77cfed4c2105d.techincal_task where id = ?";
        TechnicalTask task = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<TechnicalTask>() {
            @Override
            public TechnicalTask mapRow(ResultSet resultSet, int i) throws SQLException {
                TechnicalTask technicalTask = new TechnicalTask();
                technicalTask.setId(resultSet.getInt("id"));
                technicalTask.setName(resultSet.getString("name"));
                technicalTask.setTarget(resultSet.getString("target"));
                technicalTask.setTypeTechnicalTask(resultSet.getInt("type_id"));
                technicalTask.setDateCreated(resultSet.getDate("date_create"));
                technicalTask.setDiscipline(resultSet.getInt("discipline_id"));
                return technicalTask;
            }
        });
        task.setTasks(getTasksInTechinicalTask(id));
        task.setDemands(getDemandsInTechnicalTask(id));
        return task;
    }

    public List<String> getTasksInTechinicalTask(Integer id) {
        final String sql = "Select * from heroku_2f77cfed4c2105d.tasks where id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("description");
            }
        });
    }

    public Map<String, List<String>> getDemandsInTechnicalTask(Integer id) {
        Map<String, List<String>> result = new HashMap<>();
        final String sql = "SELECT * FROM heroku_2f77cfed4c2105d.subsections where techincal_task_id =?";

        List<Demand> demands = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<Demand>() {
            @Override
            public Demand mapRow(ResultSet resultSet, int i) throws SQLException {
                Demand demand = new Demand();
                demand.setId(resultSet.getInt("id"));
                demand.setDescription(resultSet.getString("description"));
                demand.setSectionId(resultSet.getInt("section_id"));
                demand.setTechnicalTaskId(resultSet.getInt("techincal_task_id"));
                return demand;
            }
        });

        String getSection = "Select* from heroku_2f77cfed4c2105d.section where id=?";
        List<Integer> sectionIds = demands.stream().map(demand -> demand.getSectionId()).collect(Collectors.toList());
        List<Section> sections = new ArrayList<>();
        for (Integer idSection : sectionIds) {
            Section section = jdbcTemplate.queryForObject(getSection, new Object[]{idSection}, new RowMapper<Section>() {
                @Override
                public Section mapRow(ResultSet resultSet, int i) throws SQLException {
                    Section section1 = new Section();
                    section1.setId(resultSet.getInt("id"));
                    section1.setDescription(resultSet.getString("description"));
                    return section1;
                }
            });
            sections.add(section);
        }
        sections.stream().forEach(section -> {
            int idSection = section.getId();
            List<String> subSection = demands.stream().filter(demand -> demand.getSectionId().equals(idSection)).map(demand -> demand.getDescription()).collect(Collectors.toList());
            result.put(section.getDescription(), subSection);
        });
        return result;
    }

    public boolean checkApointemnt(long idTechnicalTask) {
        String sql = "SELECT * FROM heroku_2f77cfed4c2105d.signatures Where techincal_task_id=?;";
        com.romanov.model.Signature signature = jdbcTemplate.queryForObject(sql, new Object[]{idTechnicalTask}, new RowMapper<com.romanov.model.Signature>() {
            @Override
            public com.romanov.model.Signature mapRow(ResultSet resultSet, int i) throws SQLException {
                com.romanov.model.Signature signature1 = new com.romanov.model.Signature();
                signature1.setTechnicalTaskId(resultSet.getInt("techincal_task_id"));
                signature1.setTeacherId(resultSet.getInt("teachers_users_id"));
                signature1.setStudentId(resultSet.getInt("students_users_id"));
                return signature1;
            }
        });
        if (signature.getStudentId() != 0 && signature.getTeacherId() != 0) {
            return true;
        } else {
            return false;
        }
    }

    private class Section {
        private int id;
        private String description;

        public Section() {
        }

        public Section(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public void updateTechnicalTask() {
        String sql = "UPDATE `heroku_2f77cfed4c2105d`.`techincal_task` SET `name`='тестовая работаfd', `target`='тестовая работаfd' WHERE `id`='204' and`type_id`='4';";
    }


}
