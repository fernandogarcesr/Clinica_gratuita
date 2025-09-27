/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * Clase para la conexion a la base de datos
 */
public class ConexionJDBC {

    private static String URL  ;
    private static String USER  ;
    private static String PASS  ;
    private static String DRIVER  ;

    static {
        try(InputStream input = ConexionJDBC.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties proper= new Properties();
            if (input == null) {
                throw new RuntimeException("No se logró obtener el archivo de propiedades");
            }
            
            proper.load(input);
            URL=proper.getProperty("db.url");
            USER=proper.getProperty("db.user");
            PASS=proper.getProperty("db.pass");
            DRIVER=proper.getProperty("db.driver");

            System.out.println(URL);
            System.out.println(USER);
            System.out.println(PASS);
            System.out.println(DRIVER);
            Class.forName(DRIVER);
            
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
                    
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
