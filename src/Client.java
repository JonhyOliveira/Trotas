import java.util.ArrayList;

public class Client {

    // Own variables
    private String NIF, name, email;
    private int phoneNumber;
    private int balance, totalSpent;

    //Rental variables
    private int totalMinutes, maxMinutes, rentals;
    private int lastRentalMinutes, lastRentalCost;
    Client lastRentalState;

    public Client(String NIF, String email, int phoneNumber, String name, int initialBalance) {
        this.NIF = NIF;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        lastRentalState = null;

        balance = initialBalance;

        totalSpent = 0;
        rentals = totalMinutes = maxMinutes = 0;
        lastRentalMinutes = lastRentalCost = 0;
    }

    public Client(Client client) {
        NIF = client.NIF;
        email = client.email;
        phoneNumber = client.phoneNumber;
        name = client.name;

        balance = client.balance;

        totalSpent = client.totalSpent;
        rentals = client.rentals;
        totalMinutes = client.totalMinutes;
        maxMinutes = client.maxMinutes;
        lastRentalCost = client.lastRentalCost;
        lastRentalMinutes = client.lastRentalMinutes;
    }

    /**
     * *!Warning!* This method does not error checking.
     * @param minutes Must be > 0
     * @param cost Value to deduct to the current balance
     */
    public void registerRental(int minutes, int cost) {

        lastRentalState = new Client(this);

        balance -= cost;
        totalSpent += cost;
        lastRentalCost = cost;

        rentals++;
        totalMinutes += minutes;
        lastRentalMinutes = minutes;

        if (maxMinutes < minutes) maxMinutes = minutes;

    }

    /**
     * *!Warning!* This method does no error checking.
     * @param value Value to be deposited. SHOULD be > 0
     */
    public void deposit(int value)
    {
        balance += value;
    }

    public void applyPromotion() {

        balance = lastRentalState.balance;
        totalMinutes = lastRentalState.totalMinutes;
        maxMinutes = lastRentalState.maxMinutes;
        totalSpent = lastRentalState.totalSpent;
        rentals = lastRentalState.rentals;


        lastRentalState = null;
    }

    public String getNIF() {
        return NIF;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getNumberOfRentals() {
        return rentals;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getBalance() {
        return balance;
    }

    public int getTotalSpent() {
        return totalSpent;
    }

    public int getTotalTimeSpent() {

        return totalMinutes;
    }

    public int getMedTimeSpent() {

        return getNumberOfRentals() > 0 ? getTotalTimeSpent()/getNumberOfRentals() : 0;
    }

    public int getMaxTimeSpent() {

        return maxMinutes;
    }

    public boolean canApplyPromotion() {
        return lastRentalState != null;
    }

    public int getLastRentalMinutes() {
        return lastRentalMinutes;
    }

    public int getLastRentalCost() {
        return lastRentalCost;
    }
}
