// Bapers Server | coded by Safat

// the main the class is the router, using the interpretation
// it will pass the data on to the controller

import java.sql.Connection;

public class Main {

    public static void main(String[] args) throws Exception {

        DBHelper db = new DBHelper();
        Controller ctr = new Controller(db.getCon());
        ctr.genJobSheet(1, 512);
        ctr.genIndPerf();

    }

}
