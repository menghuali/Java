package myjava.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRental {

    private class Rental {
        private String name;
        private int daysRented;

        public Rental(String name, int daysRented) {
            this.name = name;
            this.daysRented = daysRented;
        }

    }

    private List<Rental> rentals = new ArrayList<>();

    void addRental(String name, int daysRented) {
        rentals.add(new Rental(name, daysRented));
    }

    private String composeFooter(double totalAmount, int frequentRenterPoints) {
        return String.format("Total amount owed: %s\nFrequent renter points earned: %d", totalAmount,
                frequentRenterPoints);
    }

    private String computeStatementeLine(Rental rental) {
        return String.format("\t%s: %s\n", rental.name, computeRentalAmount(rental));
    }

    private int getFrequentRenterPoints(Rental rental) {
        return 1;
    }

    private double computeRentalAmount(Rental rental) {
        return rental.daysRented * 1.5;
    }

    private String composeHeader() {
        return String.format("Statement for the rental of %d movies\n", rentals.size());
    }

    private String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String statement = composeHeader();
        for (Rental rental : rentals) {
            totalAmount += computeRentalAmount(rental);
            frequentRenterPoints += getFrequentRenterPoints(rental);
            statement += computeStatementeLine(rental);
        }
        statement += composeFooter(totalAmount, frequentRenterPoints);
        return statement;
    }

    private String statementStream() {
        // Principle of refactoring for-loopt to stream:
        // A stream does one thing at a time
        double totalAmount = rentals.stream().mapToDouble(this::computeRentalAmount).sum();
        int frequentRenterPoints = rentals.stream().mapToInt(this::getFrequentRenterPoints).sum();
        String statement = composeHeader()
                + rentals.stream().map(this::computeStatementeLine).collect(Collectors.joining())
                + composeFooter(totalAmount, frequentRenterPoints);
        return statement;
    }

    public static void main(String[] args) {
        MovieRental mvRental = new MovieRental();
        mvRental.addRental("Balde Runner", 2);
        mvRental.addRental("Frozen", 3);
        mvRental.addRental("Star Wars", 1);

        System.out.println(mvRental.statement());
        System.out.println();
        System.out.println(mvRental.statementStream());
    }

}
