public class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int calculateFrequentRenterPoints() {
        return movie.calculateFrequentRenterPoints(daysRented);
    }

    public double getRentalAmount(double rentalAmount) {
        return movie.getRentalAmount(rentalAmount, daysRented);
    }
}
