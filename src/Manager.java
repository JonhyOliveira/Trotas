import javafx.util.Pair;

public class Manager {

    public static final int RENT_COST = 100, DELAY = 30, INITIAL_BALANCE = 200;

    private Client client;
    private Trot trot;
    private Pair rental;

    /**
     * This class keeps track of all interactions with the client and does some Boolean error checking to let the
     * UI (Main) know if the interaction was successful or not.
     */
    public Manager() {

        client = null;
        trot = null;
        rental = null;
    }

    /**
     * Adds the client if there isn't already a client with the same NIF
     * @return If the operation was successful or not. If there is no client with the same NIF returns "false".
     */
    public boolean addClient(String NIF, String email, int phoneNumber, String name) {

        boolean added = false;

        if (fetchClient(NIF) == null)
        {
            client = new Client(NIF, email, phoneNumber, name, INITIAL_BALANCE);
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
}
