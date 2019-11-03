import javafx.util.Pair;

public class Manager {

    public static final int RENT_COST = 100, DELAY = 30, INITIAL_BALANCE = 200;

    private Client client;
    // private Trot trot; todo: create "Trot" Object
    private Pair rental;

    /**
     * This class keeps track of all interactions with the client and does some Boolean error checking to let the
     * UI (Main) know if the interaction was successful or not.
     */
    public Manager() {

        client = null;
        // trot = null;
        rental = null;
    }

    /**
     * @return If the operation was successful or not. If there is no client with the same NIF returns "false"
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
}
