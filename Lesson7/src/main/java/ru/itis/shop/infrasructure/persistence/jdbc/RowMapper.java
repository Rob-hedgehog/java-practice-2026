package ru.itis.shop.infrasructure.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    public T mapRow(ResultSet row) throws SQLException;
}
