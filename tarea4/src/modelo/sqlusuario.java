package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sqlusuario extends conexion {

    public boolean registrar(usuario usr) throws SQLException {
        PreparedStatement Ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO usuario(usuario, password, nombre, apellido,correo, telefono) VALUES(?,?,?,?,?,?) ";

        try {

            Ps = con.prepareStatement(sql);
            Ps.setString(1, usr.getUsuario());
            Ps.setString(2, usr.getPassword());
            Ps.setString(3, usr.getNombre());
            Ps.setString(4, usr.getApellido());
            Ps.setString(5, usr.getCorreo());
            Ps.setString(6, usr.getTelefono());
            Ps.execute();

            return true;
        } catch (SQLException ex) {
            ex = null;
            Logger.getAnonymousLogger(sqlusuario.class.getName()).log(Level.SEVERE, null, ex);

            return false;

        }
    }

    public boolean loguin(usuario usr) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT id, usuario, password, nombre, apellido, telefono, correo FROM usuario WHERE usuario = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {

                if (usr.getPassword().equals(rs.getString(3))) {
                    

                   
                    ps.execute();

                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setApellido(rs.getString(5));
                    usr.setCorreo(rs.getString(6));
                    usr.setTelefono(rs.getString(7));
                   
                    return true;
                } else {
                    return false;
                }

            }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(sqlusuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public int existeusuario(String usuario) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(id) FROM usuario WHERE usuario = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getByte(1);
            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(sqlusuario.class
                    .getName()).log(Level.SEVERE, null, ex);

            return 1;
        }
    }

    public boolean esEmail(String correo) {

        Pattern pattern = Pattern.compile("[_A-Za-z0-9-\\+]+(\\.[A_Za-z0-9-])@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        return mather.find();

    }
}
