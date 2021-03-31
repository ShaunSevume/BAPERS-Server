// Bapers Server

// the main the class is the router, using the interpretation
// it will pass the data on to the controller

import DocuGen.IndividualPerformance;
import DocuGen.Invoice;
import DocuGen.JobSheet;
import DocuGen.SummaryPerformance;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) throws Exception {

        DBHelper db = new DBHelper();
        Controller ctr = new Controller(db.getCon());
        ctr.genJobSheet(1, 512);
        ctr.genIndPerf();

    }

    public void createReport(Controller ctr, String type, String [] args){
        switch(type){
            case "jobsheet":
                ctr.genJobSheet(args[0]);
                break;
            case "invoice":
                ctr.genInvoice(args[0]);
                break;
            case "individualPerformance":
                ctr.genIndPerf(args[0],args[1]);
                break;
            case "summaryPerformance":
                ctr.genSumPerf(args[0],args[1]);
                break;
        }
    }


}
