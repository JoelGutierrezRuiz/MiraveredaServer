package ieslavereda.es.repository.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class MyDataSource {
    @Bean(name="mysqlDataSource")
    public static DataSource getMySQLDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/javaAccess");
        dataSource.setUser("xrosillo");
        dataSource.setPassword("1111");
        return dataSource;
    }

    @Bean(name="oracleDataSource")
    public static DataSource getMyOracleDataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@172.28.201.239:1521:xe");
        dataSource.setUser("C##XAVIER");
        dataSource.setPassword("11111111");
        return dataSource;
    }

}
