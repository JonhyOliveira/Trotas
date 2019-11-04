import java.util.ArrayList;

public class Client {

    private String NIF, name, email;
    private int phoneNumber;
    private int balance, totalSpent;
    private ArrayList<Integer> rentals;

    public Client(String NIF, String email, int phoneNumber, String name, int initialBalance) {
        this.NIF = NIF;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;

        balance = initialBalance;

        totalSpent = 0;
        rentals = new ArrayList<>();
    }

    /**
     * *!Warning!* This method does not error checking.
     * @param minutes_since_beginning Must be > 0
     * @param cost Value to deduct to the current balance
     */
    public void registerRental(int minutes_since_beginning, int cost) {

        balance -= cost;
        totalSpent += cost;

        rentals.add(minutes_since_beginning);
    }

    /**
     * *!Warning!* This method does no error checking.
     * @param value Value to be deposited. SHOULD be > 0
     */
    public void deposit(int value)
    {
        balance += value;
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
        return rentals.size();
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

        int totalTimeSpent = 0;

        for (int time : rentals) {
            totalTimeSpent += time;
        }

        return totalTimeSpent;
    }

    public int getMedTimeSpent() {

        int med = 0;
        if (getNumberOfRentals() != 0) med = getTotalTimeSpent()/getNumberOfRentals();

        return med;
    }

    public int getMaxTimeSpent() {

        int maxTimeSpent = 0;

        for (int time : rentals) {
            if (time > maxTimeSpent) maxTimeSpent = time;
        }

        return maxTimeSpent;
    }
}
