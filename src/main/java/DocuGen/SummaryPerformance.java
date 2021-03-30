package DocuGen;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class SummaryPerformance extends Generator {

    String period;
    LinkedHashMap<String[], LinkedList<String>> data;

    public SummaryPerformance(String absPath) {
        super(absPath, "Summary Performance Report");
    }

    public SummaryPerformance(String absPath, String period) {
        this(absPath);
        this.period = period;
    }

    @Override
    protected void layout() throws Exception {

        // the subtitle
        Paragraph spSubT = new Paragraph("Period: " + period, fontSt);
        spSubT.setAlignment(Element.ALIGN_CENTER);
        doc.add(spSubT);
        doc.add(emptySpace(1));

        // iterating the data
        for (Map.Entry<String[], LinkedList<String>> spe : data.entrySet()) {
            genShiftTable(spe.getKey()[0], spe.getKey()[1], spe.getValue());
        }
    }

    private void genShiftTable(String spTTitle, String spTPeriod, LinkedList<String> spTData) throws Exception {

        // the table title
        Chunk pc = new Chunk(spTTitle, fontSt);
        Chunk tc = new Chunk(" (" + spTPeriod + ")", fontN);
        Paragraph t = new Paragraph();
        t.add(pc);
        t.add(tc);
        t.setAlignment(Element.ALIGN_CENTER);
        doc.add(t);

        // generating the table adn title
        String[] hTitles = {"Date", "Copy Room", "Development", "Finishing", "Packing"};
        doc.add(table(5, hTitles, spTData, 5));
    }

    public void populate(LinkedHashMap<String[], LinkedList<String>> data) {
        this.data = data;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
}
