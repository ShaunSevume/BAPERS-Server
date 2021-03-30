package DocuGen;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Invoice extends Generator {

    HashMap<String, Float> tasks;
    float discount = 0, subTotal = 0, discountedAmount, totalPayable;
    String cName, accN, cConName, cAddr, cPhone, jNr, cDate;

    public Invoice(String absPath, String iNr, String iDate) {
        super(absPath, true, "Invoice " + iNr + " | " + iDate);
    }

    public Invoice(String absPath, String iNr, String iDate, String cName, String accN, String cConName, String cAddr, String cPhone, String jNr, String cDate, float discount) {
        super(absPath, true, "Invoice " + iNr + " | " + iDate);
        this.cName = cName;
        this.accN = accN;
        this.cConName = cConName;
        this.cAddr = cAddr;
        this.cPhone = cPhone;
        this.jNr = jNr;
        this.cDate = cDate;
        this.discount = discount;
    }

    public void populate(HashMap<String, Float> tasks) {
        this.tasks = tasks;
    }


    @Override
    protected void layout() throws Exception {
        doc.add(emptySpace(2));
        doc.add(new Paragraph("Account: ACC0001", fontN));
        doc.add(emptySpace(2));
        doc.add(genMainInfo());
        doc.add(emptySpace(1));
        doc.add(new Paragraph("Description of work:", fontNb));
        doc.add(genTTable());
        doc.add(genBTable());
        doc.add(new Paragraph("Make a payment within 30 days by cash or card.", fontN));
    }

    private PdfPTable genTTable() {
        PdfPTable tTable = new PdfPTable(2);
        tTable.setWidthPercentage(60);
        tTable.setSpacingBefore(15);
        tTable.setSpacingAfter(0);
        tTable.addCell(new PdfPCell(new Phrase("Tasks", fontNb)));
        tTable.addCell(new PdfPCell(new Phrase("Price (£)", fontNb)));
        for (Map.Entry<String, Float> t : tasks.entrySet()) {
            subTotal += t.getValue();
            tTable.addCell(new PdfPCell(new Phrase(t.getKey(), fontN)));
            PdfPCell pCell = new PdfPCell(new Phrase(t.getValue() + "", fontN));
            pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tTable.addCell(pCell);
        }
        discountedAmount = ((100 - discount) / 100) * subTotal;
        totalPayable = discountedAmount * (float)1.2;
        return tTable;
    }

    private PdfPTable genBTable() {
        PdfPTable bTable = new PdfPTable(2);
        bTable.setWidthPercentage(60);
        bTable.setSpacingBefore(0);
        bTable.setSpacingAfter(15);
        PdfPCell sttc = new PdfPCell(new Phrase("Sub-Total", fontNb));
        sttc.setBorderWidthTop(2);
        sttc.setPaddingTop(5);
        bTable.addCell(sttc);
        PdfPCell stc = new PdfPCell(new Phrase(subTotal + "", fontN));
        stc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        stc.setBorderWidthTop(2);
        stc.setPaddingTop(5);
        bTable.addCell(stc);
        bTable.addCell(new PdfPCell(new Phrase("Discount agreed", fontNb)));
        PdfPCell dac = new PdfPCell(new Phrase(discount + "", fontN));
        dac.setHorizontalAlignment(Element.ALIGN_RIGHT);
        bTable.addCell(dac);
        bTable.addCell(new PdfPCell(new Phrase("")));
        PdfPCell adc = new PdfPCell(new Phrase(discountedAmount + "", fontN));
        adc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        bTable.addCell(adc);
        bTable.addCell(new PdfPCell(new Phrase("Total payable (VAT at 20%)", fontNb)));
        PdfPCell tpc = new PdfPCell(new Phrase(totalPayable + "", fontN));
        tpc.setHorizontalAlignment(Element.ALIGN_RIGHT);
        bTable.addCell(tpc);
        return bTable;
    }

    private Paragraph genMainInfo() {
        Paragraph mainInfo = new Paragraph();
        mainInfo.add(new Chunk("Customer Name: ", fontNb));
        mainInfo.add(new Chunk("City University\n", fontN));
        mainInfo.add(new Chunk("Account No: ", fontNb));
        mainInfo.add(new Chunk("ACC0001\n", fontN));
        mainInfo.add(new Chunk("Contact Name: ", fontNb));
        mainInfo.add(new Chunk("Prof David Rhind\n", fontN));
        mainInfo.add(new Chunk("Address: ", fontNb));
        mainInfo.add(new Chunk("Northampton Square, London EC1V 0HB\n", fontN));
        mainInfo.add(new Chunk("Phone: ", fontNb));
        mainInfo.add(new Chunk("020 7040 8000\n", fontN));
        mainInfo.add(new Chunk("Job No: " + jNr + "                                 Completed: "+ cDate + "\n", fontN));
        return mainInfo;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setAccN(String accN) {
        this.accN = accN;
    }

    public void setcConName(String cConName) {
        this.cConName = cConName;
    }

    public void setcAddr(String cAddr) {
        this.cAddr = cAddr;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public void setjNr(String jNr) {
        this.jNr = jNr;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public void setTasks(HashMap<String, Float> tasks) {
        this.tasks = tasks;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
