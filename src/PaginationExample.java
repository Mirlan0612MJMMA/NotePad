import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Arrays;
import java.util.Collection;

public class PaginationExample implements Printable {

    private String textForPrinting;
    int[] pageBreaks;
    String[] textLines;

    public PaginationExample(String textForPrinting) {
        this.textForPrinting = textForPrinting;
    }

    public void printMyDocument() {

        int row = 0;
        for (int i = 0; i < textForPrinting.length(); i++) {
            char symbol = textForPrinting.charAt(i);
            if (symbol == '\n') {
                row = row + 1;
            }
        }
        textLines = new String[row];
        String line = "";
        int index = 0;

        for (int i = 0; i < textForPrinting.length(); i++) {
            char symbol = textForPrinting.charAt(i);
            if (symbol == '\n') {
                textLines[index] = line;
                index = index + 1;
                line = "";
            } else {
                line = line + symbol;
            }
        }

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }
        System.out.println("PaginationExample.printMyDocument()-end");
    }

    private void initTextLines() {
        System.out.println("initTextLines start--" + textLines);
        if (textLines == null) {
            int numLines = 200;
            textLines = new String[numLines];
            for (int i = 0; i < numLines; i++) {
                System.out.println("line: " + i + "\n");
                textLines[i] = "This is line number " + i;
            }
        }
        System.out.println("initTextLines end--" + textLines);
    }

    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {

        Font font = new Font("Serif", Font.PLAIN, 10);
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHeight = metrics.getHeight();
        if (pageBreaks == null) {
            initTextLines();
            int linesPerPage = (int) (pf.getImageableHeight() / lineHeight);
            int numBreaks = (textLines.length - 1) / linesPerPage;
            pageBreaks = new int[numBreaks];

            for (int b = 0; b < numBreaks; b++) {
                pageBreaks[b] = (b + 1) * linesPerPage;
            }
        }
        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        int y = 0;
        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex - 1];
        int end = (pageIndex == pageBreaks.length)
                ? textLines.length : pageBreaks[pageIndex];
        for (int line = start; line < end; line++) {
            y += lineHeight;
            g.drawString(textLines[line], 50, y);
        }
        return PAGE_EXISTS;
    }
//    public void actionPerformed(ActionEvent e) {
//        PrinterJob job = PrinterJob.getPrinterJob();
//        job.setPrintable(this);
//        boolean ok = job.printDialog();
//        if (ok) {
//            try {
//                job.print();
//            } catch (PrinterException ex) {
//                /* The job did not successfully complete */
//            }
//        }
//    }

//    public static void main(String args[]) {
//
//        try {
//            String cn = UIManager.getSystemLookAndFeelClassName();
//            UIManager.setLookAndFeel(cn); // Use the native L&F
//        } catch (Exception cnf) {
//        }
//        JFrame f = new JFrame("Printing Pagination Example");
//        f.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {System.exit(0);}
//        });
//        JButton printButton = new JButton("Print Pages");
//        printButton.addActionListener(new PaginationExample());
//        f.add("Center", printButton);
//        f.pack();
//        f.setVisible(true);
//    }
}
