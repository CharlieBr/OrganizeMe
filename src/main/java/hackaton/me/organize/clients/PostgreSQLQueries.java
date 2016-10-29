package hackaton.me.organize.clients;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLQueries {
    public void createParticipantsTable(final Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS participants (id serial PRIMARY KEY,  name VARCHAR(25));");
    }

    public void addUser(final Statement statement, final String name) throws SQLException {
        statement.execute("INSERT INTO participants(name) VALUES('" + name + "');");
    }

    public ResultSet getParticipantsQuery(final Statement st) throws SQLException {
        return st.executeQuery("SELECT * from participants");
    }
}
