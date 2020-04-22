package dataBase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {

    private static HikariDataSource hikariDataSource;

    public static HikariDataSource getHikariDataSource(){
        if(null != hikariDataSource)
            return hikariDataSource;

        synchronized (ConnectionPool.class){
            if(null == hikariDataSource){
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/HomeworkSystem?serverTimezone=GMT");
                hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
                hikariConfig.setUsername("root");
                hikariConfig.setPassword("");

                hikariDataSource = new HikariDataSource(hikariConfig);
                return  hikariDataSource;
            }
        }

        return null;
    }
}

