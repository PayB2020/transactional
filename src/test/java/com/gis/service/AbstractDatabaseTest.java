package com.gis.service;

import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Базовый класс для тестов с использованием базы данных.
 */
public abstract class AbstractDatabaseTest {

    /**
     * База данных, с которой работаем в тест-кейсе.
     *
     * @return бд
     */
    protected abstract DataSource dataSource();

    /**
     * Исполнить скрипт в бд.
     *
//     * @param scriptPath путь ло скрипта
     */
//    public void runScript(final String scriptPath) {
//        try (final InputStream stream = Thread.currentThread()
//                .getContextClassLoader()
//                .getResourceAsStream(scriptPath);
//             final Connection connection = dataSource().getConnection();
//             final InputStreamReader inputStreamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
//             ResultSet rs = RunScript.execute(connection, inputStreamReader)) {
//            //всё в try/catch с ресурсами, т.к. всё надо закрывать.
//        } catch (IOException | SQLException e) {
//            throw new ApplicationException(e, "test runScript error");
//        }
//    }

    public void initDB() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setLocations("h2");
        flyway.clean();
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();
    }

    /**
     * Почистить бд.
     */
    public void clearDb() {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.update("DROP ALL OBJECTS");
    }

}
