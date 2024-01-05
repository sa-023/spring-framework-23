package com.company;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringCinemaLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCinemaLabApplication.class, args);
    }



    /*
     * 🔹 Flyway Migration
     * · Flyway is another open-source library used for Database Migration / Version Control for the DB scripts.
     * · It allows us to migrate the changes to DB incrementally by versioning, and it supports migrations in SQL or Java migration
     *
     * 🖍️...
     * When "application.properties" -> spring.flyway.enabled=false :
     * 1. Flyway is looking for a data source, and it doesn't know where it is. We should manually tell Flyway where the data source is.
     * 2. Entities must be created first, and then Flyway must perform the migration. Since our tables are not ready, we need to tell Flyway our data source.
     * · Flyway works with SQL files under resources/db/migration.
     */
    @Bean
    public MigrateResult migrateResult(DataSource dataSource){
        return Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
    }

}
