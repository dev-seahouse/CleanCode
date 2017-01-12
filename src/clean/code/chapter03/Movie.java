package clean.code.chapter03;

public abstract class Movie {
    private String title;
    int priceCode;

    public Movie(String title) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    abstract double determineAmount(int daysRented);

    abstract int determineFrequentRenterPoints(int daysRented);
}