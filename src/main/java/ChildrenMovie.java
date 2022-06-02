public class ChildrenMovie extends Movie {

    public ChildrenMovie(String movieName) {
        super(movieName);
    }

    @Override
    int calculateFrequentRenterPoints(int daysRented) {
        return 1;
    }

    @Override
    double getRentalAmount(double rentalAmount, int daysRented) {
        rentalAmount += 1.5;
        if (daysRented > 3) {
            rentalAmount += (daysRented - 3) * 1.5;
        }
        return rentalAmount;
    }
}
