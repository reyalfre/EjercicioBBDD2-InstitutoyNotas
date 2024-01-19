import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InstitutoProgram {

    public static void main(String[] args) {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Instituto", "root", "");

            // Create the NotasFinales table
            Statement createStatement = connection.createStatement();
            createStatement.executeUpdate("CREATE TABLE NotasFinales (Mat VARCHAR(10) NOT NULL, Cod INT(2) NOT NULL, NotaMedia DECIMAL(4, 2), PRIMARY KEY (Mat, Cod))");

            // Calculate and insert average grades into NotasFinales
            Statement insertStatement = connection.createStatement();
            insertStatement.executeUpdate("INSERT INTO NotasFinales (Mat, Cod, NotaMedia) " +
                    "SELECT Mat, Cod, ROUND((NOTA1 + NOTA2 + NOTA3) / 3.0, 2) AS NotaMedia FROM Notas");

            // Print the list of all students with their grades
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("SELECT ALUMNOS.APEL_NOM, ASIGNATURAS.NOMBRE, NOTAS.NOTA1, NOTAS.NOTA2, NOTAS.NOTA3, NotasFinales.NotaMedia " +
                    "FROM ALUMNOS " +
                    "JOIN NOTAS ON ALUMNOS.MAT = NOTAS.MAT " +
                    "JOIN ASIGNATURAS ON NOTAS.COD = ASIGNATURAS.COD " +
                    "JOIN NotasFinales ON NOTAS.MAT = NotasFinales.Mat AND NOTAS.COD = NotasFinales.Cod");

            // Print the header
            System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %-25s%n", "Nombre Alumno", "Nombre", "Asignatura", "Nota 1", "Nota 2", "Nota 3", "NotaMedia");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            // Print the data
            while (resultSet.next()) {
                System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %-25s%n",
                        resultSet.getString("APEL_NOM"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getInt("NOTA1"),
                        resultSet.getInt("NOTA2"),
                        resultSet.getInt("NOTA3"),
                        resultSet.getDouble("NotaMedia"));
            }

            // Close the resources
            resultSet.close();
            selectStatement.close();
            insertStatement.close();
            createStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
