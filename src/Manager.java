public class Manager {

    private int rentCost, rentLimit, delay, initialBalance, minForRental;

    private Client client;
    private Trot trot;
    private Rental rental;

    private int rentals, totalEarned, totalDelays;

    public int getNumberOfRentals() {
        return rentals;
    }

    public int getTotalEarned() {
        return totalEarned;
    }

    public int getTotalDelays() {
        return totalDelays;
    }

    /**
     * This class keeps track of all interactions with the client and does some Boolean error checking to let the
     * UI (Main) know if the interaction was successful or not.
     * @param rentCost The rent cost. Also the value to increment when finishing the rental and the user surpasses the
     *                 delay
     * @param rentLimit The limit of the rental. Can be surpassed.
     * @param delay When the user surpasses the rental limit. This is the interval between applying the rentCost to the
     *              cost
     * @param initialBalance The balance which the user starts with.
     * @param minForRental The balance that the user needs to have to perform a rental.
     */
    public Manager(int rentCost, int rentLimit, int delay, int initialBalance, int minForRental) {

        client = null;
        trot = null;
        rental = null;

        this.rentCost = rentCost;
        this.rentLimit = rentLimit;
        this.delay = delay;
        this.initialBalance = initialBalance;
        this.minForRental = minForRental;
    }

    /**
     * Adds the client if there isn't already a client with the same NIF
     * @return If the operation was successful or not. If there is no client with the same NIF returns "false".
     */
    public boolean addClient(String NIF, String email, int phoneNumber, String name) {

        boolean added = false;

        if (fetchClient(NIF) == null)
        {
            client = new Client(NIF, email, phoneNumber, name, initialBalance);
            added = true;
        }

        return added;
    }

    /**
     * Removes the client if it is able to match the NIF to the client.
     * @param NIF parameter to compare to the NIF of the current client.
     * @return whether or not it was able to remove the client (was found or not).
     */
    public boolean remClient(String NIF) {

        boolean removed = false;

        Client c = fetchClient(NIF);
        if (c != null)
        {
            client = null;
            removed = true;
        }

        return removed;
    }

    public Client fetchClient(String NIF)
    {
        Client c = null;

        if (client != null) if (client.getNIF().toUpperCase().equals(NIF.toUpperCase())) c = client;

        return c;
    }

    public Client getTrotsClient(String idTrot) {
        return getTrotsClient(fetchTrot(idTrot));
    }

    public Client getTrotsClient(Trot t) {

        Client client = null;

        if (rental != null) if (rental.getTrot().equals(t)) client = rental.getClient();

        return client;
    }


    /**
     * TROT
     */

    /**
     * Adds the trot if it isn't able to find a trot with the same idTrot
     * @return Whether or not it was able to add the trot.
     */
    public boolean addTrot(String idTrot, String registration) {

        boolean added = false;

        if (fetchTrot(idTrot) == null) {
            added = true;
            trot = new Trot(idTrot, registration);
        }

        return added;
    }

    public Trot fetchTrot(String idTrot) {

        Trot t = null;

        if (trot != null) if (trot.getIdTrot().toUpperCase().equals(idTrot.toUpperCase())) t = trot;

        return t;
    }

    public Trot getClientsTrot(String NIF) {

        return getClientsTrot(fetchClient(NIF));

    }

    public Trot getClientsTrot(Client client) {

        Trot trot = null;

        if (rental != null) if (rental.getClient().equals(client)) trot = rental.getTrot();

        return trot;
    }

    /**
     * RENTALS
     */

    /**
     * Registers a rental between the Client and the Trot.
     * @param c Client to register in the rental
     * @param t Trot to register in the rental
     * @return Whether or not the Client has enough balance to start the rental.
     */
    public boolean startRental(Client c, Trot t) {

        boolean success = false;

        if (c.getBalance() >= minForRental) {
            rental = new Rental(c, t);
            t.rent();
            success = true;
        }

        return success;
    }

    public boolean finishRental(Trot t, int minutes) {

        boolean finished = false;

        if (t.isRented() && rental != null) {

            finished = true;

            int cost = rentCost;
            int tempMin = minutes;
            
            while (tempMin - rentLimit > 0) {
                cost += rentCost;
                tempMin -= delay;
            }

            // modify client, trot and rental
            rental.getClient().registerRental(minutes, cost);
            rental.getTrot().registerRental(minutes);
            rental = null;

            totalEarned += cost;
            totalDelays += Math.max(minutes - rentLimit, 0);
            rentals++;
        }

        return finished;
    }
}
