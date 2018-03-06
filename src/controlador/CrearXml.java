/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author vbarrera
 */
public class CrearXml {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    SimpleDateFormat sdfSqlite = new SimpleDateFormat("yyyy-MM-dd");
    Date dateActual = new Date();
    ConexionSqlite sqlite = new ConexionSqlite();

    public void generarXml(String fecha) {
        System.err.println(fecha);
        sqlite.conectar();
        try {
            Date date = sdf.parse(fecha + " " + dateActual.getHours() + ":" + dateActual.getMinutes() + ":" + dateActual.getSeconds());
            Element company = new Element("STC");
            Document doc = new Document(company);
            doc.setRootElement(company);

            Element CountRoom = new Element("CountRoom");
            CountRoom.setAttribute("version", "");
            CountRoom.setAttribute("gamingDate", (fecha + " " + "10:40:01").replace(" ", "T"));
            CountRoom.setAttribute("transactionDate", sdf.format(new Date()).replace(" ", "T"));
            CountRoom.setAttribute("id", "R00001");
            doc.getRootElement().addContent(CountRoom);

            Element CurrencyCounter = new Element("CurrencyCounter");
            CurrencyCounter.setAttribute("model", "41015");
            CurrencyCounter.setAttribute("serialNumber", "00001");
            CurrencyCounter.setAttribute("manufacturer", "CUMMINS");
            CountRoom.addContent(CurrencyCounter);

            ResultSet resultSetBill = sqlite.Consultar("select slotNumber,bill100k,bill2k,bill5k,bill10k,bill20k,bill50k from bill where date=\"" + fecha + "\"");

            try {
                while (resultSetBill.next()) {
                    Element BillCassette = new Element("BillCassette");
                    BillCassette.setAttribute(new Attribute("slotNumber", "" + resultSetBill.getInt("slotNumber")));
                    BillCassette.setAttribute(new Attribute("id", ""));

                    Element CurrencySet = new Element("CurrencySet");

                    if (resultSetBill.getInt("bill2k") >= 1) {
                        CurrencySet.addContent(new Element("Currency")
                                .setAttribute("type", "0")
                                .setAttribute("count", "" + resultSetBill.getInt("bill2k"))
                                .setAttribute("code", "COP")
                                .setAttribute("denomination", "2000.00"));

                    }
                    if (resultSetBill.getInt("bill5k") >= 1) {
                        CurrencySet.addContent(new Element("Currency")
                                .setAttribute("type", "0")
                                .setAttribute("count", "" + resultSetBill.getInt("bill5k"))
                                .setAttribute("code", "COP")
                                .setAttribute("denomination", "5000.00"));

                    }
                    if (resultSetBill.getInt("bill10k") >= 1) {
                        CurrencySet.addContent(new Element("Currency")
                                .setAttribute("type", "0")
                                .setAttribute("count", "" + resultSetBill.getInt("bill10k"))
                                .setAttribute("code", "COP")
                                .setAttribute("denomination", "10000.00"));

                    }
                    if (resultSetBill.getInt("bill20k") >= 1) {
                        CurrencySet.addContent(new Element("Currency")
                                .setAttribute("type", "0")
                                .setAttribute("count", "" + resultSetBill.getInt("bill20k"))
                                .setAttribute("code", "COP")
                                .setAttribute("denomination", "20000.00"));

                    }
                    if (resultSetBill.getInt("bill50k") >= 1) {
                        CurrencySet.addContent(new Element("Currency")
                                .setAttribute("type", "0")
                                .setAttribute("count", "" + resultSetBill.getInt("bill50k"))
                                .setAttribute("code", "COP")
                                .setAttribute("denomination", "50000.00"));

                    }

                    if (resultSetBill.getInt("bill100k") >= 1) {
                        CurrencySet.addContent(new Element("Currency")
                                .setAttribute("type", "0")
                                .setAttribute("count", ""+ resultSetBill.getInt("bill100k"))
                                .setAttribute("code", "COP")
                                .setAttribute("denomination", "100000.00"));

                    }

                    BillCassette.addContent(CurrencySet);
                    ResultSet resultTicket = sqlite.Consultar("select count(*) as num from ticket where date=\"" + fecha + "\"" + " and slotNumber=" + resultSetBill.getInt("slotNumber") + "");
                    if (resultTicket.getInt("num") > 0) {
                        ResultSet resultSetTicket = sqlite.Consultar("select slotNumber,date,ticket from ticket where date=\"" + fecha + "\"" + " and slotNumber=" + resultSetBill.getInt("slotNumber") + "");

                        Element TicketSet = new Element("TicketSet");
                        while (resultSetTicket.next()) {
                            System.out.println("" + resultSetTicket.getString("ticket"));

                            TicketSet.addContent(new Element("Ticket")
                                    .setAttribute("strap", "")
                                    .setAttribute("barcode", resultSetTicket.getString("ticket")));
                        }
                        BillCassette.addContent(TicketSet);

                    }
                    CurrencyCounter.addContent(BillCassette);
                }

                // CurrencyCounter.addContent(BillCassette);
            } catch (SQLException ex) {
                Logger.getLogger(CrearXml.class.getName()).log(Level.SEVERE, null, ex);
            }
            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("C:\\Users\\Public\\Documents\\csbatchR0001.xml"));

            //System.out.println("File Saved!");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(CrearXml.class.getName()).log(Level.SEVERE, null, ex);
        }

        sqlite.desconectar();
    }
}
