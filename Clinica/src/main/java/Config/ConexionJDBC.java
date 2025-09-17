/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class ConexionJDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/Activistas";
    private static final String USER = "root";
    private static final String PASS = "Ensalada";
    private static final String DRIVER = "com.mysql.cj.jdbc.driver";

//    static {
//        try(InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("db.properties")) {
//            Properties proper= new Properties();
//            if (input == null) {
//                throw new RuntimeException("No se logró obtener el archivo de propiedades");
//            }
//            proper.load(input);
//            
//            URL=proper.getProperty("db.url");
//            USER=proper.getProperty("db.USER");
//            PASS=proper.getProperty("db.PASS");
//            DRIVER=proper.getProperty("db.driver");
//            
//            Class.forName(DRIVER);
//            
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e.getMessage());
//                    
//        }
//    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
