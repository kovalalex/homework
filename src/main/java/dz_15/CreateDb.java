package dz_15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDb {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dz_14", "postgres", "postgres");
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS  \"user\" Cascade ;\n" +
                    "CREATE TABLE \"user\" (\n" +
                    "\t\"id\" serial,\n" +
                    "\t\"name\" varchar(100),\n" +
                    "\t\"birthday\" DATE NOT NULL,\n" +
                    "\t\"login_ID\" integer NOT NULL,\n" +
                    "\t\"city\" varchar(100) NOT NULL,\n" +
                    "\t\"email\" varchar(100) NOT NULL,\n" +
                    "\t\"description\" TEXT NOT NULL,\n" +
                    "\tCONSTRAINT \"user_pk\" PRIMARY KEY (\"id\")\n" +
                    ") WITH (\n" +
                    "  OIDS=FALSE\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS  \"user_role\" cascade ;\n" +
                    "CREATE TABLE \"user_role\" (\n" +
                    "\t\"id\" serial,\n" +
                    "\t\"user_id\" integer NOT NULL,\n" +
                    "\t\"role_id\" integer NOT NULL,\n" +
                    "\tCONSTRAINT \"user_role_pk\" PRIMARY KEY (\"id\")\n" +
                    ") WITH (\n" +
                    "  OIDS=FALSE\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS  \"role\" cascade ;\n" +
                    "CREATE TABLE \"role\" (\n" +
                    "\t\"id\" serial NOT NULL,\n" +
                    "\t\"name\" varchar(100) NOT NULL,\n" +
                    "\t\"description\" TEXT NOT NULL,\n" +
                    "\tCONSTRAINT \"role_pk\" PRIMARY KEY (\"id\")\n" +
                    ") WITH (\n" +
                    "  OIDS=FALSE\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "ALTER TABLE \"user_role\" ADD CONSTRAINT \"user_role_fk0\" FOREIGN KEY (\"user_id\") REFERENCES \"user\"(\"id\") ON DELETE CASCADE;\n" +
                    "ALTER TABLE \"user_role\" ADD CONSTRAINT \"user_role_fk1\" FOREIGN KEY (\"role_id\") REFERENCES \"role\"(\"id\") ON DELETE CASCADE;\n" +
                    "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
