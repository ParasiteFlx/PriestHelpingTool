import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
class TextPrintable implements Printable {
    private final ArrayList<String> lines;
    // Al doilea element din layer 2 , se ocupa cu accesarea default printer ului de la user si formatarea paginii in functie
    //de input ul dat de printStrings()
    TextPrintable(ArrayList<String> lines) {
        this.lines = lines;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        int linesPerPage = (int) ((pageFormat.getImageableHeight() - (2 * pageFormat.getImageableY())) / graphics.getFontMetrics().getHeight());
        if (pageIndex * linesPerPage >= this.lines.size()) {
            return NO_SUCH_PAGE;
        }

        int x = (int) pageFormat.getImageableX();
        int y = (int) pageFormat.getImageableY() + graphics.getFontMetrics().getAscent();
        for (int i = pageIndex * linesPerPage; i < Math.min((pageIndex + 1) * linesPerPage, this.lines.size()); i++) {
            graphics.drawString(this.lines.get(i), x, y);
            y += graphics.getFontMetrics().getHeight();
        }
        return PAGE_EXISTS;
    }
}


