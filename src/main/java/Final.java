import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Final {

    public static void main(String[] args) {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Instituto", "root", "");

            // Retrieve data from the NotasFinales table
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("SELECT ALUMNOS.APEL_NOM, ASIGNATURAS.NOMBRE, NOTAS.NOTA1, NOTAS.NOTA2, NOTAS.NOTA3, NotasFinales.NotaMedia " +
                    "FROM ALUMNOS " +
                    "JOIN NotasFinales ON ALUMNOS.MAT = NotasFinales.Mat " +
                    "JOIN ASIGNATURAS ON NotasFinales.COD = ASIGNATURAS.COD " +
                    "JOIN NOTAS ON NotasFinales.Mat = NOTAS.MAT AND NotasFinales.COD = NOTAS.COD");

            // Print the header
            System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s%n", "Nombre Alumno", "Asignatura", "Nota 1", "Nota 2", "Nota 3", "NotaMedia");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");

            // Print the data
            while (resultSet.next()) {
                System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s%n",
                        resultSet.getString("APEL_NOM"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getString("NOTA1"),
                        resultSet.getString("NOTA2"),
                        resultSet.getString("NOTA3"),
                        resultSet.getDouble("NotaMedia"));
            }

            // Close the resources
            resultSet.close();
            selectStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
