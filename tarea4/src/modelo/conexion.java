package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexion {
    private final String base = "usuarios";
    private final String user = "root";
    private final String password = "123456789";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    Connection con = null;

    public Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url,user, password);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;

    }

   public class getConexion {
       public getConexion() {
           
       }
   }
}
