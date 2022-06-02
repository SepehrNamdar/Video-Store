public class RegularMovie extends Movie {

    public RegularMovie(String movieName) {
        super(movieName);
    }

    @Override
    int calculateFrequentRenterPoints(int daysRented) {
        return 1;
    }

    @Override
    double getRentalAmount(double rentalAmount, int daysRented) {
        rentalAmount += 2;
        if (daysRented > 2) {
            rentalAmount += (daysRented - 2) * 1.5;
        }
        return rentalAmount;
    }
}
