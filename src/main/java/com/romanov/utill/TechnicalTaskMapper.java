package com.romanov.utill;


import com.romanov.model.TechnicalTask;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kirill_Romanov1 on 03-Apr-17.
 */
public class TechnicalTaskMapper implements RowMapper<TechnicalTask> {
    @Override
    public TechnicalTask mapRow(ResultSet resultSet, int i) throws SQLException {
        TechnicalTask task=new TechnicalTask();
        return null;
    }
}
