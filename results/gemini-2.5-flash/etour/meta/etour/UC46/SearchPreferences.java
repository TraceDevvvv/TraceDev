package com.example.touristapp;

import java.util.Objects;

/**
 * Represents the search preferences of a tourist.
 * This class encapsulates various criteria a tourist might use for searching,
 * such as destination, price range, number of travelers, and travel dates.
 */
public class SearchPreferences {
    private String preferredDestination;
    private double minPrice;
    private double maxPrice;
    private int numberOfTravelers;
    private String preferredTravelDates; // Could be a more complex Date object in a real app

    /**
     * Constructs a new SearchPreferences object with default values.
     */
    public SearchPreferences() {
        this.preferredDestination = "Anywhere";
        this.minPrice = 0.0;
        this.maxPrice = Double.MAX_VALUE;
        this.numberOfTravelers = 1;
        this.preferredTravelDates = "Anytime";
    }

    /**
     * Constructs a new SearchPreferences object with specified values.
     *
     * @param preferredDestination The desired destination.
     * @param minPrice The minimum price for the search.
     * @param maxPrice The maximum price for the search.
     * @param numberOfTravelers The number of travelers.
     * @param preferredTravelDates The preferred travel dates.
     */
    public SearchPreferences(String preferredDestination, double minPrice, double maxPrice,
                             int numberOfTravelers, String preferredTravelDates) {
        this.preferredDestination = preferredDestination;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.numberOfTravelers = numberOfTravelers;
        this.preferredTravelDates = preferredTravelDates;
    }

    /**
     * Gets the preferred destination.
     *
     * @return The preferred destination.
     */
    public String getPreferredDestination() {
        return preferredDestination;
    }

    /**
     * Sets the preferred destination.
     *
     * @param preferredDestination The new preferred destination.
     */
    public void setPreferredDestination(String preferredDestination) {
        this.preferredDestination = preferredDestination;
    }

    /**
     * Gets the minimum price.
     *
     * @return The minimum price.
     */
    public double getMinPrice() {
        return minPrice;
    }

    /**
     * Sets the minimum price.
     *
     * @param minPrice The new minimum price.
     */
    public void setMinPrice(double minPrice) {
        if (minPrice < 0) {
            throw new IllegalArgumentException("Minimum price cannot be negative.");
        }
        this.minPrice = minPrice;
    }

    /**
     * Gets the maximum price.
     *
     * @return The maximum price.
     */
    public double getMaxPrice() {
        return maxPrice;
    }

    /**
     * Sets the maximum price.
     *
     * @param maxPrice The new maximum price.
     */
    public void setMaxPrice(double maxPrice) {
        if (maxPrice < 0) {
            throw new IllegalArgumentException("Maximum price cannot be negative.");
        }
        this.maxPrice = maxPrice;
    }

    /**
     * Gets the number of travelers.
     *
     * @return The number of travelers.
     */
    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }

    /**
     * Sets the number of travelers.
     *
     * @param numberOfTravelers The new number of travelers.
     */
    public void setNumberOfTravelers(int numberOfTravelers) {
        if (numberOfTravelers <= 0) {
            throw new IllegalArgumentException("Number of travelers must be at least 1.");
        }
        this.numberOfTravelers = numberOfTravelers;
    }

    /**
     * Gets the preferred travel dates.
     *
     * @return The preferred travel dates.
     */
    public String getPreferredTravelDates() {
        return preferredTravelDates;
    }

    /**
     * Sets the preferred travel dates.
     *
     * @param preferredTravelDates The new preferred travel dates.
     */
    public void setPreferredTravelDates(String preferredTravelDates) {
        this.preferredTravelDates = preferredTravelDates;
    }

    @Override
    public String toString() {
        return "SearchPreferences{" +
               "preferredDestination='" + preferredDestination + '\'' +
               ", minPrice=" + minPrice +
               ", maxPrice=" + maxPrice +
               ", numberOfTravelers=" + numberOfTravelers +
               ", preferredTravelDates='" + preferredTravelDates + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchPreferences that = (SearchPreferences) o;
        return Double.compare(that.minPrice, minPrice) == 0 &&
               Double.compare(that.maxPrice, maxPrice) == 0 &&
               numberOfTravelers == that.numberOfTravelers &&
               Objects.equals(preferredDestination, that.preferredDestination) &&
               Objects.equals(preferredTravelDates, that.preferredTravelDates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preferredDestination, minPrice, maxPrice, numberOfTravelers, preferredTravelDates);
    }
}