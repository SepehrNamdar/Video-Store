import java.util.LinkedList;
import java.util.List;

public class Statement {
    private final String customerName;
    private final List<Rental> rentals;
    private double totalAmount;
    private int frequentRenterPoints;
    private final StringBuilder statement;

    public Statement(String customerName) {
        this.customerName = customerName;
        rentals = new LinkedList<>();
        statement = new StringBuilder();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String statement() {
        initialize();
        statement.append(header());
        statement.append(rentals());
        statement.append(footer());
        return statement.toString();
    }

    private StringBuilder rentals() {
        StringBuilder statement = new StringBuilder();
        for (Rental rental : rentals) {
            statement.append(rentalStatement(rental));
        }
        return statement;
    }

    private StringBuilder footer() {
        StringBuilder statement = new StringBuilder();
        statement.append("You owed ").append(totalAmount).append("\n");
        statement.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return statement;
    }

    private StringBuilder header() {
        StringBuilder statement = new StringBuilder();
        statement.append("Rental records for ").append(getCustomerName()).append("\n");
        return statement;
    }

    private void initialize() {
        totalAmount = 0;
        frequentRenterPoints = 0;
    }

    private StringBuilder rentalStatement(Rental rental) {
        StringBuilder statement = new StringBuilder();
        statement.append("\t")
                .append(rental.getMovie().getTitle())
                .append("\t")
                .append(getRentalAmount(rental))
                .append("\n");
        return statement;
    }

    private double getRentalAmount(Rental rental) {
        double rentalAmount = 0;
        rentalAmount = rental.getRentalAmount(rentalAmount);
        frequentRenterPoints += rental.calculateFrequentRenterPoints();
        totalAmount += rentalAmount;
        return rentalAmount;
    }

}
