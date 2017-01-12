package clean.code.chapter10.original;

/**
 * Created by xinke on 12/1/2017.
 */
public class PrimePrinter {
    private static final int numberOfPrimes = 1000;
    private static int linesPerPage = 50;
    private static int columns = 4;
    public static void main(String[] args){
        PrimeGenerator primeGenerator = new PrimeGenerator();
        NumberPrinter numberPrinter = new NumberPrinter(linesPerPage, columns);

        int primes[] = primeGenerator.generatePrimes(numberOfPrimes);
        numberPrinter.print(primes, numberOfPrimes);
    }
}

