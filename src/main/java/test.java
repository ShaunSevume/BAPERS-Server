import DocuGen.IndividualPerformance;
import DocuGen.Invoice;
import DocuGen.JobSheet;
import DocuGen.SummaryPerformance;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class test {

    public static void main(String[] args) throws Exception {

        /* THE JOB SHEET */
        LinkedList<String> data = new LinkedList<>();
        data.add("J20934565");
        data.add("19.00");
        data.add("1");
        data.add("Copy Room");
        data.add("12:00");
        data.add("110 min");
        data.add("John Nash");
        data.add("");
        data.add("49.50");
        data.add("2");
        data.add("Development");
        data.add("12:30");
        data.add("55 min");
        data.add("Lee Hon");
        data.add("J95869663");
        data.add("19.00");
        data.add("1");
        data.add("Copy Room");
        data.add("13:20");
        data.add("120 min");
        data.add("John Nash");

        JobSheet gen = new JobSheet("src/main/resources/final/test.pdf", "1245");
        gen.setcName("ACC3005");
        gen.setDesc("");
        gen.setColDate("4 pm, 13 January 2020");

        gen.populate(data);
        gen.generate();


        /* SUMMARY PERFORMANCE */
        SummaryPerformance sm = new SummaryPerformance("src/main/resources/final/sp.pdf");
        sm.setPeriod("13/01/2020 - 20/01/2020");

        LinkedHashMap<String[], LinkedList<String>> spData = new LinkedHashMap<>();

        String[] sptData1T = {"Day Shift 1", "5 am - 2:30 pm"};
        LinkedList<String> spTData1 = new LinkedList<>();
        spTData1.add("13/01/2020");
        spTData1.add("8 h 20 min");
        spTData1.add("10 h 45 min");
        spTData1.add("8 h 30 min");
        spTData1.add("2 h 30 min");
        spTData1.add("14/01/2020");
        spTData1.add("10 h 20 min");
        spTData1.add("11 h 30 min");
        spTData1.add("10 h 40 min");
        spTData1.add("1 h 30 min");

        String[] sptData2T = {"Day Shift 2", "2:30 pm - 10 pm"};
        LinkedList<String> spTData2 = new LinkedList<>();
        spTData2.add("13/01/2020");
        spTData2.add("8 h 20 min");
        spTData2.add("13 h 45 min");
        spTData2.add("12 h 30 min");
        spTData2.add("2 h 30 min");

        String[] sptData3T = {"Day Shift 3", "10 am - 2:30 pm"};
        LinkedList<String> spTData3 = new LinkedList<>();
        spTData3.add("14/01/2020");
        spTData3.add("8 h 20 min");
        spTData3.add("13 h 45 min");
        spTData3.add("12 h 30 min");
        spTData3.add("2 h 30 min");

        spData.put(sptData1T, spTData1);
        spData.put(sptData2T, spTData2);
        spData.put(sptData3T, spTData3);

        sm.populate(spData);
        sm.generate();



        /* INDIVIDUAL PERFORMANCE */
        IndividualPerformance ip = new IndividualPerformance("src/main/resources/final/ip.pdf");
        ip.setPeriod("13/01/2020 - 13/01/2020");

        LinkedList<String> ipData = new LinkedList<>();
        ipData.add("John Nash");
        ipData.add("1");
        ipData.add("Copy Room");
        ipData.add("13/01/2020");
        ipData.add("12:00");
        ipData.add("20 min");
        ipData.add("*");
        ipData.add("");
        ipData.add("1");
        ipData.add("Copy Room");
        ipData.add("13/01/2020");
        ipData.add("12:20");
        ipData.add("35 min");
        ipData.add("*");
        ipData.add("");
        ipData.add("1");
        ipData.add("Copy Room");
        ipData.add("13/01/2020");
        ipData.add("12:55");
        ipData.add("1 h 40 min");
        ipData.add("2 h 55 min");
        ipData.add("Lee Hong");
        ipData.add("2");
        ipData.add("Development");
        ipData.add("13/01/2020");
        ipData.add("12:30");
        ipData.add("40 min");
        ipData.add("*");
        ipData.add("");
        ipData.add("4");
        ipData.add("Development");
        ipData.add("13/01/2020");
        ipData.add("13:10");
        ipData.add("40 min");
        ipData.add("2 h 10 min");

//        ip.populate(ipData);
//        ip.generate();


        /* THE INVOICE */
        Invoice inv = new Invoice("src/main/resources/final/inv.pdf", "30123", "13/01/2020");
        inv.setAccN("ACC001");
        inv.setcName("City University");
        inv.setcConName("Prof David Rhind");
        inv.setcAddr("Northampton Square, London EC1V 0HB");
        inv.setcAddr("0207 040 8000");
        inv.setjNr("J1245637");
        inv.setcDate("13 January 2020");
        inv.setDiscount(5);

        HashMap<String, Float> inData = new HashMap<>();
        inData.put("1", (float)19.00);
        inData.put("2", (float)45.50);
        inData.put("3", (float)80.00);
        inData.put("4", (float)6.45);

        inv.populate(inData);
        inv.generate();

    }

}
