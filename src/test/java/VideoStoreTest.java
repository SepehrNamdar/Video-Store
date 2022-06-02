import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoStoreTest {

    private Statement statement;

    @BeforeEach
    void setUp() {
        statement = new Statement("Fred");
    }

    @Test
    void testSingleNewReleaseStatement() {
        final Movie the_cell = new NewReleaseMovie("The Cell");
        final Rental rental = new Rental(the_cell, 3);

        statement.addRental(rental);
        final String statement = this.statement.statement();

        assertThat("Rental records for Fred\n" +
                "\tThe Cell\t9.0\n" +
                "You owed 9.0\n" +
                "You earned 2 frequent renter points").isEqualTo(statement);
    }

    @Test
    void testDualNewReleaseStatement() {
        final Movie the_cell = new NewReleaseMovie("The Cell");
        final Rental rental1 = new Rental(the_cell, 3);
        final Movie the_tigger_movie = new NewReleaseMovie("The Tigger Movie");
        final Rental rental2 = new Rental(the_tigger_movie, 1);

        statement.addRental(rental1);
        statement.addRental(rental2);
        final String statement = this.statement.statement();

        assertThat("Rental records for Fred\n" +
                "\tThe Cell\t9.0\n" +
                "\tThe Tigger Movie\t3.0\n" +
                "You owed 12.0\nYou earned 3 frequent renter points").isEqualTo(statement);
    }

    @Test
    void testSingleChildrenStatement() {
        final Movie the_tigger_movie = new ChildrenMovie("The Tigger Movie");
        final Rental rental = new Rental(the_tigger_movie, 3);

        statement.addRental(rental);
        final String statement = this.statement.statement();

        assertThat("Rental records for Fred\n" +
                "\tThe Tigger Movie\t1.5\n" +
                "You owed 1.5\n" +
                "You earned 1 frequent renter points").isEqualTo(statement);
    }

    @Test
    void testMultipleRegularStatement() {
        final Movie movie1 = new RegularMovie("Plan 9 from Outer Space");
        final Rental rental1 = new Rental(movie1, 1);
        final Movie movie2 = new RegularMovie("8 1/2");
        final Rental rental2 = new Rental(movie2, 2);
        final Movie movie3 = new RegularMovie("Eraserhead");
        final Rental rental3 = new Rental(movie3, 3);

        statement.addRental(rental1);
        statement.addRental(rental2);
        statement.addRental(rental3);
        final String statement = this.statement.statement();

        assertThat("Rental records for Fred\n" +
                "\tPlan 9 from Outer Space\t2.0\n" +
                "\t8 1/2\t2.0\n" +
                "\tEraserhead\t3.5\n" +
                "You owed 7.5\n" +
                "You earned 3 frequent renter points").isEqualTo(statement);
    }

    @Test
    public void testMultipleMixStatement() {
        Statement customer = new Statement("Ali Daei");
        final Movie zire_nour_mah = new NewReleaseMovie("Zire Nour Mah");
        final Rental rental1 = new Rental(zire_nour_mah, 6);
        final Movie marmoulak = new ChildrenMovie("Marmoulak");
        final Rental rental2 = new Rental(marmoulak, 7);
        final Movie offside = new RegularMovie("Offside");
        final Rental rental3 = new Rental(offside, 5);

        customer.addRental(rental1);
        customer.addRental(rental2);
        customer.addRental(rental3);
        final String statement = customer.statement();

        String expected = "Rental records for Ali Daei\n"
                + "	Zire Nour Mah	18.0\n"
                + "	Marmoulak	7.5\n"
                + "	Offside	6.5\n"
                + "You owed 32.0\n"
                + "You earned 4 frequent renter points";
        assertThat(expected).isEqualTo(statement);
    }

    @Test
    void testNull() {
        final Movie the_cell = new RegularMovie("The Cell");
        final Rental badRental = new Rental(the_cell, 3);

        statement.addRental(badRental);
        final String statement = this.statement.statement();

        assertThat("Rental records for Fred\n" +
                "\tThe Cell\t3.5\n" +
                "You owed 3.5\n" +
                "You earned 1 frequent renter points").isEqualTo(statement);
    }
}
