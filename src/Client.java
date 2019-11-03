public class Client {

    private String NIF, name, email;
    private int numberOfRentals, phoneNumber;
    private int balance, totalSpent;
    private int totalTimeSpent, minTimeSpent, maxTimeSpent;

    public Client(String NIF, String email, int phoneNumber, String name, int initialBalance) {
        this.NIF = NIF;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;

        balance = initialBalance;

        numberOfRentals = totalSpent = totalTimeSpent = minTimeSpent = maxTimeSpent = 0;
    }

    /**
     * @param minutes_since_beginning Must be > 0
     * @param cost Value to deduct to the current balance
     */
    public void registerRental(int minutes_since_beginning, int cost) {

        balance -= cost;
        totalSpent += cost;

        totalTimeSpent += minutes_since_beginning;
        if (maxTimeSpent < minutes_since_beginning) maxTimeSpent = minutes_since_beginning;
        if (minTimeSpent > minutes_since_beginning || minTimeSpent == 0) minTimeSpent = minutes_since_beginning;

        numberOfRentals++;
    }

    /**
     * *!Warning!* This method does no error checking.
     * @param value Value to be deposited
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
        return numberOfRentals;
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
        return totalTimeSpent;
    }

    public int getMinTimeSpent() {
        return minTimeSpent;
    }

    public int getMaxTimeSpent() {
        return maxTimeSpent;
    }
}
