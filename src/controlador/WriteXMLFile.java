import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class WriteXMLFile {

    public static void main(String[] args) {

        try {

            Element company = new Element("STC");
            Document doc = new Document(company);
            doc.setRootElement(company);

            Element CountRoom = new Element("CountRoom");
            CountRoom.setAttribute("version", "");
            CountRoom.setAttribute("gamingDate", "2015-09-30T10:41:30");
            CountRoom.setAttribute("transactionDate", "2015-10-01T10:41:30");
            CountRoom.setAttribute("id", "R00001");
            doc.getRootElement().addContent(CountRoom);

            Element CurrencyCounter = new Element("CurrencyCounter");
            CurrencyCounter.setAttribute("model", "41015");
            CurrencyCounter.setAttribute("serialNumber", "00002");
            CurrencyCounter.setAttribute("manufacturer", "CUMMINS");
            CountRoom.addContent(CurrencyCounter);

            Element BillCassette = new Element("BillCassette");
            BillCassette.setAttribute(new Attribute("slotNumber", "10101"));
            BillCassette.setAttribute(new Attribute("id", ""));
            CurrencyCounter.addContent(BillCassette);

            Element CurrencySet = new Element("CurrencySet");
            CurrencySet.addContent(new Element("Currency")
                    .setAttribute("type", "0")
                    .setAttribute("count", "1")
                    .setAttribute("code", "COP")
                    .setAttribute("denomination", "2000.00"));
            BillCassette.addContent(CurrencySet);

            Element TicketSet = new Element("TicketSet");
            TicketSet.addContent(new Element("Ticket")
                    .setAttribute("strap", "")
                    .setAttribute("barcode", "528610101004778626"));
            BillCassette.addContent(TicketSet);

            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("file.xml"));

            //System.out.println("File Saved!");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}
