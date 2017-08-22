/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vbarrera
 */
public class ConexionSqlite {

    public String url = "C://Users/Public/Documents/Conteo.db";
    //public String url = "sms.db";
    public Connection Link = null;

    public boolean conectar() {
        try {
            //Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("org.sqlite.JDBC");
            this.Link = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (this.Link != null) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR DE CONECCION A: " + this.url);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public void desconectar() {
       // this.Link = null;
        try {
            this.Link.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection GetConection() {
        return this.Link;
    }

    public boolean Ejecutar(String SQL) {
        try {
            PreparedStatement Query = this.GetConection().prepareStatement(SQL);
            Query.execute();
            return true;
        } catch (SQLException ex) {
            //System.out.println("ERROR: "+ex);
            return false;
        }

    }

    public ResultSet Consultar(String SQL) {
        try {
            PreparedStatement Query = this.GetConection().prepareStatement(SQL);
            ResultSet Resultado = Query.executeQuery();
            return Resultado;
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
            return null;
        }
    }
}
