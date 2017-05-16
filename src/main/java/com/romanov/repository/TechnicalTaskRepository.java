package com.romanov.repository;

import com.romanov.model.TechnicalTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
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

    public List<TechnicalTask> getAll() {
        List<TechnicalTask> tasks = new ArrayList<>();
        return tasks;
    }

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
        System.out.println(keyHolder.getKey());
        addSubSection(task, keyHolder.getKey());
    }

    @Transactional
    public void addSubSection(TechnicalTask task, Number idTechnicalTask) {
        final String insertDemand = "Insert into heroku_2f77cfed4c2105d.section (description) VALUES (:description);";
        for (Map.Entry<String, List<String>> entry : task.getDemands().entrySet()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String demand = entry.getKey();
            List<String> subDemand = entry.getValue();
            MapSqlParameterSource namedParametrs = new MapSqlParameterSource();
            namedParametrs.addValue("description",demand);
            namedParameterJdbcTemplate.update(insertDemand,namedParametrs,keyHolder, new String[]{"ID"});
            for (String elem : subDemand) {
                final String inserSubDemands = "Insert into heroku_2f77cfed4c2105d.subsections (description, techincal_task_id, section_id) Values (:description, :techincal_task_id,:section_id ); ";
                MapSqlParameterSource subNamedParametrs = new MapSqlParameterSource();
                subNamedParametrs.addValue("description", elem);
                subNamedParametrs.addValue("techincal_task_id", idTechnicalTask);
                subNamedParametrs.addValue("section_id",keyHolder.getKey());
            }
        }

        String sql = "INSERT INTO heroku_2f77cfed4c2105d.subsections (description,techincal_task_id,section_id, demand) VALUES (:description, :techincal_task_id, 1, :demand);";
/*        for (Map.Entry<String, String> entry : task.getDemands().entrySet()) {
            Map namedParametrs = new HashMap();
            namedParametrs.put("description", entry.getValue());
            namedParametrs.put("demand", entry.getKey());
            namedParametrs.put("techincal_task_id", idTechnicalTask);
            namedParameterJdbcTemplate.update(sql, namedParametrs);
        }*/
    }
   /* public void addTechnicalTask(TechnicalTask task){
        String sql="INSERT INTO heroku_2f77cfed4c2105d.techincal_task (name, target, type_id, date_create,  discipline_id) VALUES (:name,:target, :type_id, :date_create, :discipline_id);";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        MapSqlParameterSource  namedParametrs= new MapSqlParameterSource();
        namedParametrs.addValue("name",task.getName());
        namedParametrs.addValue("target",task.getTarget());
        namedParametrs.addValue("type_id",task.getTypeTechnicalTask());
        namedParametrs.addValue("date_create",task.getDateCreated());
        namedParametrs.addValue("discipline_id",task.getDiscipline());
        namedParameterJdbcTemplate.update(sql,namedParametrs,keyHolder, new String[]{"ID"});
        System.out.println(keyHolder.getKey());
        addSubSection(task,keyHolder.getKey());
    }

    public void addSubSection(TechnicalTask task, Number idTechnicalTask){
        List<String> demand=new ArrayList<>(task.getDemands().keySet());
        List<String> description=new ArrayList<>(task.getDemands().values());
        String sql="INSERT INTO heroku_2f77cfed4c2105d.subsections (description,techincal_task_id,section_id, demand) VALUES (:description, :techincal_task_id, 1, :demand);";
        for(Map.Entry<String,String> entry: task.getDemands().entrySet()){
            Map namedParametrs=new HashMap();
            namedParametrs.put("description",entry.getValue());
            namedParametrs.put("demand",entry.getKey());
            namedParametrs.put("techincal_task_id",idTechnicalTask);
            namedParameterJdbcTemplate.update(sql,namedParametrs);
        }
    }*/

}
