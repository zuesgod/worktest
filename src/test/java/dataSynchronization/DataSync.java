package dataSynchronization;

/**
 * 不同数据库同步测试
 *
 * @author zeus
 * @date 2023-07-31 17:19
 **/
public class DataSync {

    // MySQL连接配置
    String mysqlUrl = "jdbc:mysql://your_mysql_host:your_mysql_port/your_mysql_database";
    String mysqlUsername = "your_mysql_username";
    String mysqlPassword = "your_mysql_password";

    // PostgreSQL连接配置
    String postgresUrl = "jdbc:postgresql://your_postgres_host:your_postgres_port/your_postgres_database";
    String postgresUsername = "your_postgres_username";
    String postgresPassword = "your_postgres_password";

//        try {
//        // 连接MySQL数据库
//        Connection mysqlConn = DriverManager.getConnection(mysqlUrl, mysqlUsername, mysqlPassword);
//        Statement mysqlStmt = mysqlConn.createStatement();
//
//        // 连接PostgreSQL数据库
//        Connection postgresConn = DriverManager.getConnection(postgresUrl, postgresUsername, postgresPassword);
//        Statement postgresStmt = postgresConn.createStatement();
//
//        // 从MySQL表读取数据
//        String mysqlQuery = "SELECT * FROM your_mysql_table";
//        ResultSet resultSet = mysqlStmt.executeQuery(mysqlQuery);
//
//        // 将数据插入到PostgreSQL表
//        while (resultSet.next()) {
//            // 这里根据你的表结构进行调整
//            String col1Value = resultSet.getString("col1");
//            int col2Value = resultSet.getInt("col2");
//            double col3Value = resultSet.getDouble("col3");
//
//            // 这里根据你的表结构进行调整
//            String insertQuery = "INSERT INTO your_postgres_table (col1, col2, col3) VALUES (?, ?, ?)";
//            PreparedStatement insertStmt = postgresConn.prepareStatement(insertQuery);
//            insertStmt.setString(1, col1Value);
//            insertStmt.setInt(2, col2Value);
//            insertStmt.setDouble(3, col3Value);
//            insertStmt.executeUpdate();
//        }
//
//        System.out.println("Data migration completed successfully!");
//
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}


}
