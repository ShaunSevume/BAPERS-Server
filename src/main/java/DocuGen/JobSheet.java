package DocuGen;

import com.itextpdf.text.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class JobSheet extends Generator {

    String cName;
    String desc;
    String colDate;

    LinkedList<String> data;

    // The Constructor
    public JobSheet(String absPath, String jNr) {
        super(absPath, "Job Sheet " + jNr);
    }

    public JobSheet(String absPath, String jNr, String cName, String desc, String colDate) {
        this(absPath, jNr);
        this.cName = cName;
        this.desc = desc;
        this.colDate = colDate;
    }

    // the layout
    protected void layout() throws Exception {

        // The Date
        Paragraph jsDate = new Paragraph("Date: " + formatDate(LocalDateTime.now()), fontNb);
        jsDate.setAlignment(Element.ALIGN_RIGHT);
        doc.add(jsDate);

        // Job Sheet Info
        doc.add(new Paragraph("Customer: " + cName + "\nDescription of work: " + desc + "\nEstimated time for collection: " + colDate + "\n\nDescription of work in progress: ", fontNb));

        // Generating the table with headers
        String[] hTitles = {"Job", "Price, £", "Task", "Department", "Start Time", "Time Taken", "Completed by"};
        doc.add(table(7, hTitles, data, 15));

    }

    public void populate(LinkedList<String> data) {
        this.data = data;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setColDate(String colDate) {
        this.colDate = colDate;
    }
}
