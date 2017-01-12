package clean.code.chapter10.original;

/**
 * Created by xinke on 12/1/2017.
 */
class NumberPrinter {
    private int linesPerPage;
    private int columns;

    public NumberPrinter(int linesPerPage, int columns) {
        this.linesPerPage = linesPerPage;
        this.columns = columns;
    }

    public void print(int[] numbers, int numberOfNumbers) {
        int pagenumber = 1;
        int pageoffset = 1;
        while (pageoffset <= numberOfNumbers) {
            System.out.println("The First " + numberOfNumbers +
                    " Prime Numbers --- Page " + pagenumber);
            System.out.println("\n");
            for (int rowoffset = pageoffset; rowoffset < pageoffset + linesPerPage; rowoffset++) {
                for (columns = 0; columns < columns; columns++)
                    if (rowoffset + columns * linesPerPage <= numberOfNumbers)
                        System.out.format("%10d", numbers[rowoffset + columns * linesPerPage]);
                System.out.println();
            }
            System.out.println("\f");
            pagenumber = pagenumber + 1;
            pageoffset = pageoffset + linesPerPage * columns;
        }
    }
}
