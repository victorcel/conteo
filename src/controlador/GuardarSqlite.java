/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.BillCassette;
import modelo.Ticket;

/**
 *
 * @author vbarrera
 */
public class GuardarSqlite {

    // BillCassette cassette = new BillCassette();
    // Ticket ticket = new Ticket();
    ConexionSqlite sqlite = new ConexionSqlite();

    public void guardarBill(BillCassette cassette, String bill100k, String bill2k, String bill5k, String bill10k, String bill20k, String bill50k) {
        sqlite.conectar();
        sqlite.Ejecutar("insert into Bill (slotNumber, date,bill100k,bill2k,bill5k,bill10k,bill20k,bill50k,totalUnidad,totalValor) values (" + cassette.getSlotNumber() + ",\"" + cassette.getDate() + "\"," + bill100k + "," + bill2k + "," + bill5k + "," + bill10k + "," + bill20k + "," + bill50k + "," + cassette.getTotalUnidad() + "," + cassette.getTotalValor() + ")");
        sqlite.desconectar();
    }

    public void guardarTicket(Ticket ticket) {
        sqlite.conectar();
        sqlite.Ejecutar("insert into Ticket (slotNumber,date,ticket) values (" + ticket.getSlotNumber() + ",\"" + ticket.getDate() + "\"," + ticket.getTicket() + ")");
        sqlite.desconectar();
    }

    public Boolean consultarTicket(String ticket) {
        boolean estado = false;
        sqlite.conectar();
        
        ResultSet resultSet= sqlite.Consultar("select * from Ticket where ticket="+ticket);
        try {
            if (resultSet.next()) {
                //System.err.println(""+resultSet.getString(1));
                sqlite.desconectar();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuardarSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
        sqlite.desconectar();
        return estado;
    }
    public Boolean consultarMaquina(int maquina,String date) {
        boolean estado = false;
        sqlite.conectar();
        //System.err.println(""+date+" "+maquina);
        ResultSet resultSet= sqlite.Consultar("select * from Bill where slotNumber="+maquina+" and date=\""+date+"\"");
        try {
            if (resultSet.next()) {
               //System.err.println(""+resultSet.getString(0));
                sqlite.desconectar();
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuardarSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
        sqlite.desconectar();
        return estado;
    }
}
