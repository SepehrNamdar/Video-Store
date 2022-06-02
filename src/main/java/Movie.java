public abstract class Movie {
    private final String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    abstract int calculateFrequentRenterPoints(int daysRented);

    abstract double getRentalAmount(double rentalAmount, int daysRented);
}
