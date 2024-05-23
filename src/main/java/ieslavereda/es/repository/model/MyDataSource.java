package ieslavereda.es.repository.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class MyDataSource {

    @Bean(name="oracleDataSource")
    public static DataSource getMyOracleDataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@172.30.134.233:1539:xe"); //ip de la maquina Virtual
        dataSource.setUser("C##Aray"); //Poner de user sys as sysdba
        dataSource.setPassword("1234"); //misma
        return dataSource;
    }

}
