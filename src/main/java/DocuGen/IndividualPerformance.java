package DocuGen;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.LinkedList;

public class IndividualPerformance extends Generator {

    String period, totalEffort;
    LinkedList<String> data;

    public IndividualPerformance(String absPath) {
        super(absPath, "Individual Performance Report");
    }

    public IndividualPerformance(String absPath, String period) {
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

        // the table
        String[] hTitles = {"Name", "Task IDs", "Department", "Date", "Start time", "Time taken", "Total"};
        PdfPTable ipTable = table(7, hTitles, data, 15);
        PdfPCell tec = new PdfPCell(new Phrase("Total Effort", fontN));
        tec.setUseVariableBorders(true);
        tec.setBorderColorRight(BaseColor.WHITE);
        ipTable.addCell(tec);
        for (int i = 0; i < 5; i++) {
            PdfPCell c = new PdfPCell();
            c.setUseVariableBorders(true);
            c.setBorderColorLeft(BaseColor.WHITE);
            c.setBorderColorRight(BaseColor.WHITE);
            ipTable.addCell(c);
        }
        PdfPCell tevc = new PdfPCell(new Phrase(totalEffort, fontN));
        tevc.setHorizontalAlignment(Element.ALIGN_CENTER);
        ipTable.addCell(tevc);
        doc.add(ipTable);


    }

    public void populate(LinkedList<String> data, String totalEffort) {
        this.data = data;
        this.totalEffort = totalEffort;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public void setTotalEffort(String totalEffort) {
        this.totalEffort = totalEffort;
    }
}
