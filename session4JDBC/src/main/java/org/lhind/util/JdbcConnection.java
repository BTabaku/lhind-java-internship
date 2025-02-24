package org.lhind.util;

import org.lhind.model.DBProperties;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class JdbcConnection {

    private JdbcConnection() {
    }

    public static Connection connect() {
        Connection connection = null;
        try (InputStream stream = JdbcConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (stream == null) {
                throw new IOException("Could not find db.properties");
            }

            Properties properties = new Properties();
            properties.load(stream);

            DBProperties dbProperties = getDbProperties(properties);

            connection = DriverManager.getConnection(dbProperties.getUrl(), dbProperties.getUser(), dbProperties.getPassword());

        } catch (IOException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }

    private static DBProperties getDbProperties(Properties properties) {
        final DBProperties dbProperties = new DBProperties();
        dbProperties.setUrl(properties.getProperty("jdbc.url"));
        dbProperties.setUser(properties.getProperty("jdbc.user"));
        dbProperties.setPassword(properties.getProperty("jdbc.password"));
        return dbProperties;
    }
}
