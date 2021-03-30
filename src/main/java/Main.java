// Bapers Server | coded by Safat

// the main the class is the router, using the interpretation
// it will pass the data on to the controller

public class Main {

    public static void main(String[] args) throws Exception {

        Controller ctr = new Controller();
        ctr.genJobSheet(1, 512);
        ctr.genIndPerf();

    }

}
