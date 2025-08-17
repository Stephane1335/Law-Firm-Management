package home;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Centralized DB connection utility.
 * Reads JDBC configuration from config.properties at project root (fallback to env vars).
 * Example properties:
 *   jdbc.url=jdbc:mysql://localhost:3306/your_db
 *   jdbc.user=user
 *   jdbc.password=secret
 */
public class Db {

    private static Properties loadProps() throws IOException {
        Properties p = new Properties();
        // Try project root config.properties then env
        Path rootConfig = Path.of(System.getProperty("user.dir"), "config.properties");
        if (Files.exists(rootConfig)) {
            try (FileInputStream in = new FileInputStream(rootConfig.toFile())) {
                p.load(in);
            }
        }
        // Fallback to env
        p.putIfAbsent("jdbc.url", System.getenv("JDBC_URL"));
        p.putIfAbsent("jdbc.user", System.getenv("JDBC_USER"));
        p.putIfAbsent("jdbc.password", System.getenv("JDBC_PASSWORD"));
        return p;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Properties props = loadProps();
            String url = props.getProperty("jdbc.url");
            String user = props.getProperty("jdbc.user");
            String password = props.getProperty("jdbc.password");
            if (url == null || url.isEmpty()) {
                throw new SQLException("Missing jdbc.url in config.properties or JDBC_URL env");
            }
            // Modern drivers auto-register; Class.forName not required in most cases.
            return DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            throw new SQLException("Failed to load DB config", e);
        }
    }
}
