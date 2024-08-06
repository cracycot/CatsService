package com.exampleLogic.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TruncateTable extends DataBaseConnection {
    public  void truncate(String tableName) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "TRUNCATE TABLE " + tableName + " RESTART IDENTITY CASCADE";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
