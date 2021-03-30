package DocuGen;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public abstract class Generator {

    // The document
    final Document doc = new Document();
    final String absPath, title;
    Paragraph ph;

    // The fonts
    final Font fontN = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
    final Font fontNb = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
    final Font fontT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
    final Font fontSt = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

    // Constructor
    public Generator(String absPath, String title) {
        this.absPath = absPath;
        this.title = title;
    }

    // Optional before the header Constructor
    public Generator(String absPath, boolean inPh, String title) {
        this(absPath, title);
        if (inPh) {
            ph = new Paragraph();
            ph.add(new Chunk("The Lab\n", fontT));
            ph.add(new Chunk("2, Wynyatt Stree\nLondon, EC1V 7HU\nPhone: 020 7235 7534", fontN));
            ph.setAlignment(Element.ALIGN_RIGHT);
        }
    }

    // The generation method
    public void generate() throws Exception {
        PdfWriter.getInstance(doc, new FileOutputStream(absPath));
        doc.open();
        header(title);
        layout();
        doc.close();
    }

    // the header
    protected void header(String title) throws Exception {
        if (ph != null) {
            doc.add(ph);
            doc.add(emptySpace(1));
        }
        Paragraph elmTitle = new Paragraph(title, fontT);
        elmTitle.setAlignment(Element.ALIGN_CENTER);
        doc.add(elmTitle);
    }

    /* HELPER METHODS */

    // the table generation with header titles
    protected PdfPTable table(int columns, String[] headerTitles, LinkedList<String> tData, float spacing) {
        PdfPTable t = new PdfPTable(columns);
        t.setWidthPercentage(95);
        t.setSpacingBefore(spacing);
        t.setSpacingAfter(spacing);
        for (String ht : headerTitles) {
            PdfPCell h = new PdfPCell(new Phrase(ht, fontNb));
            h.setHorizontalAlignment(Element.ALIGN_CENTER);
            t.addCell(h);
        }

        // for no border check
        int nbi = columns - 1;
        boolean nbc = false;

        // populating the table
        for (String sps : tData) {
            boolean spe = false;
            if (sps != null) {
                if (sps.equals("*")) {
                    sps =  "";
                    spe = true;
                }
            } else {
                sps = "N/A";
            }

            PdfPCell spSubCell = new PdfPCell(new Phrase(sps, fontN));
            spSubCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            // checks if the cell has * for top border removal
            if (nbi == 0 && nbc) {
                spSubCell.setUseVariableBorders(true);
                spSubCell.setBorderColorTop(BaseColor.WHITE);
                nbc = false;
            }

            if (spe) {
                spSubCell.setUseVariableBorders(true);
                spSubCell.setBorderColorBottom(BaseColor.WHITE);
                nbc = true;
            }

            // adds the cell to the table
            t.addCell(spSubCell);

            // not border cell index
            if (nbi < 1) {
                nbi = columns;
            }
            nbi--;
        }
        return t;
    }

    // white space
    protected Paragraph emptySpace(int lines) {
        String empties = "";
        for (int i = 0; i < lines; i++) {
            empties+="\n";
        }
        return new Paragraph(empties);
    }

    // format date
    public String formatDate(LocalDateTime dt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return dtf.format(dt);
    }

    // method implementations
    protected abstract void layout() throws Exception;

}
