package hackaton.me.organize.clients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgreSQLClient {
    private static final Logger LOG = LoggerFactory.getLogger(PostgreSQLClient.class);
    private final String url;
    private final String username;
    private final String password;
    private final PostgreSQLQueries postgreSQLQueries;

    public PostgreSQLClient(final String url, final String username, final String password, final PostgreSQLQueries postgreSQLQueries) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.postgreSQLQueries = postgreSQLQueries;
        prepareTables();
    }

    private void prepareTables() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(this.url, this.username, this.password);
            statement = connection.createStatement();
            this.postgreSQLQueries.createParticipantsTable(statement);
        } catch (final SQLException e) {
            LOG.error("Fail to create database tables: {}", e.getMessage());
        } finally {
            closeQuietly(connection, statement);
        }
    }

    public void addParticipant(final String username) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(this.url, this.username, this.password);
            statement = connection.createStatement();
            addUser(statement, username);
        }
        catch(final SQLException e) {
            LOG.error("Fail to add user: {}, {}", e.getSQLState(), e.getMessage());
        }
        finally {
            closeQuietly(connection, statement);
        }
    }

    private void addUser(final Statement statement, final String name) throws SQLException {
        this.postgreSQLQueries.addUser(statement, name);
    }

    public List<String> fetchParticipants() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        final List<String> result = new LinkedList<>();
        try {
            connection = DriverManager.getConnection(this.url, this.username, this.password);
            statement = connection.createStatement();
            resultSet = this.postgreSQLQueries.getParticipantsQuery(statement);
            while(resultSet.next()) {
                final String name = resultSet.getString("name");
                result.add(name);
            }
        }
        catch(final SQLException e) {
            LOG.error("Fail to fetch participants from database: {}", e.getMessage());
        }
        finally {
            closeQuietly(connection, statement, resultSet);
        }
        return result;
    }



    private void closeQuietly(final Connection connection, final Statement statement, final ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }

        }
        catch(final SQLException ex) {
            LOG.error("OMG!");
        }
    }

    private void closeQuietly(final Connection connection, final Statement statement) {
        try {
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }

        }
        catch(final SQLException ex) {
            LOG.error("OMG!");
        }
    }
}
