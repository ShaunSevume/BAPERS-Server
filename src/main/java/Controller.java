import DocuGen.IndividualPerformance;
import DocuGen.JobSheet;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

// Contains the code to generate the different documents

public class Controller {

    public Controller() {

    }

    public void genIndPerf() throws Exception { // need to implement the based on time

        ResultSet rs = getFromDB(indPerfQ());
        int prevStaff = 1;
        int totalStaff = 0;
        int totalEffort = 0;
        LinkedList<String> ipd = new LinkedList<>();

        while (rs.next()) {
            int curStaff = rs.getInt("s_id");

            if (prevStaff != curStaff) {
                ipd.set(ipd.size() - 1, totalStaff + "");
                totalStaff = 0;
            }

            totalStaff += rs.getInt("t_duration");
            totalEffort += rs.getInt("t_duration");
            prevStaff = curStaff;

            ipd.add(rs.getString("s_name"));
            ipd.add(rs.getInt("tt_id") + "");
            ipd.add(rs.getString("tt_location"));
            ipd.add(convertDateToString(rs.getDate("t_start_time")));
            ipd.add(convertTimeToString(rs.getTime("t_start_time")));
            ipd.add(rs.getInt("t_duration") + "");
            ipd.add(rs.isLast() ? totalStaff + "" : "*");
        }

        // generating the document
        IndividualPerformance ip = new IndividualPerformance("src/main/resources/final/IP.pdf", "will implement later");
        ip.populate(ipd, totalEffort + "");
        ip.generate();
    }


    // generate job sheet
    public void genJobSheet(int custNo, int jsNr) throws Exception {

        // Getting result set from the Database
        ResultSet rs = getFromDB(jobSheetQ(custNo));

        int prevJobId = 0;
        LinkedList<String> jsd = new LinkedList<>();

        // getting the data from the db and putting it to a linked list for JobSheet to read
        while (rs.next()) {
            int curJobId = rs.getInt("j_id");
            jsd.add((curJobId != prevJobId) ? curJobId + "" : "" );
            jsd.add(rs.getFloat("tt_price") + "");
            jsd.add(rs.getInt("tt_id") + "");
            jsd.add(rs.getString("tt_location"));
            jsd.add(convertTimeToString(rs.getTime("t_start_time")));
            jsd.add(rs.getInt("t_duration") + " min");
            jsd.add((rs.getString("s_name") != null) ? rs.getString("s_name") : "" );
            prevJobId = curJobId;
        }

        // generating the pdf file based on data retrieved
        JobSheet js = new JobSheet("src/main/resources/final/JS" + jsNr + ".pdf", jsNr + "", custNo + "", "", "{idk_will_figure_out_later}");
        js.populate(jsd);
        js.generate();
    }

    // method to get data from the database
    private ResultSet getFromDB(String q) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bapers", "root", "");
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(q);
        con.close();
        return rs;
    }

    private String convertTimeToString(Time t) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m");
        return dtf.format(t.toLocalTime());
    }

    private String convertDateToString(Date d) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return dtf.format(d.toLocalDate());
    }

    private String jobSheetQ(int custNo) {
        return "SELECT j.j_id, tt.tt_id, tt.tt_price, tt.tt_location, t.t_start_time, t.t_duration, s.s_name FROM jobs j LEFT JOIN tasks t on j.j_id = t.job_id " +
                "LEFT JOIN staff s on t.staff_id = s.s_id " +
                "LEFT JOIN tasktypes tt on t.tasktype_id = tt.tt_id WHERE j.customer_no = " + custNo + " ORDER BY j.j_id";
    }

    private String indPerfQ() {
        return "SELECT s.s_id, s.s_name, tt.tt_id, tt.tt_location, t.t_start_time, t.t_duration FROM staff s LEFT JOIN tasks t on s.s_id = t.staff_id " +
                "LEFT JOIN tasktypes tt on t.tasktype_id = tt.tt_id WHERE tt.tt_id IS NOT NULL ORDER BY s.s_id";
    }

}
