public class NewReleaseMovie extends Movie {

    public NewReleaseMovie(String movieName) {
        super(movieName);
    }

    @Override
    int calculateFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }

    @Override
    double getRentalAmount(double rentalAmount, int daysRented) {
        rentalAmount += daysRented * 3;
        return rentalAmount;
    }
}
