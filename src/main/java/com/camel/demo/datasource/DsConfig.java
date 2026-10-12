package com.camel.demo.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.common.util.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix = "camel.db")
public class DsConfig extends HikariConfig {
    private static final String DATASOURCE_NAME = "CAMEL";

    @Bean(DATASOURCE_NAME)
    public DataSource dataSource() throws IOException {
        HikariDataSource hikariDataSource = null;

        try {
            hikariDataSource = new HikariDataSource();

            if(this.getConnectionTimeout() !=0){
                hikariDataSource.setConnectionTimeout(this.getConnectionTimeout());
            } else {
                throw new IOException(DATASOURCE_NAME + " connection timeout non configurata correttamente");
            }

            if (StringUtils.isNotBlank(this.getUsername())) {
                hikariDataSource.setUsername(this.getUsername());
            } else {
                throw new IOException(DATASOURCE_NAME + " username non configurata");
            }
            if (StringUtils.isNotBlank(this.getDriverClassName())) {
                hikariDataSource.setDriverClassName(this.getDriverClassName());
            } else {
                throw new IOException(DATASOURCE_NAME + " driver class non configurata");
            }
            if (StringUtils.isNotBlank(this.getJdbcUrl())) {
                hikariDataSource.setJdbcUrl(this.getJdbcUrl());
            } else {
                throw new IOException(DATASOURCE_NAME + " jdbc url non configurata");
            }
            //Num max connessioni totali (attive e non)
            if (this.getMaximumPoolSize() !=0) {
                hikariDataSource.setMaximumPoolSize(this.getMaximumPoolSize());
            } else {
                throw new IOException(DATASOURCE_NAME + " maximum pool size non configurata");
            }
            //Num minimo di connessioni inattive messe a disposizione
            if (this.getMinimumIdle() !=0) {
                hikariDataSource.setMinimumIdle(this.getMinimumIdle());
            }  else {
                throw new IOException(DATASOURCE_NAME + " minimum idle non configurata");
            }
            hikariDataSource.setPassword(this.getPassword());
            hikariDataSource.setInitializationFailTimeout(-1);
            return hikariDataSource;

        } catch (Exception e) {
            if (hikariDataSource != null) {
                hikariDataSource.close();
            }
            throw e;
        }
    }

}
