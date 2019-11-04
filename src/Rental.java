public class Rental {

    private Client client;
    private Trot trot;

    public Rental(Client c, Trot t) {
        client = c;
        trot = t;
    }

    public Client getClient() {
        return client;
    }

    public Trot getTrot() {
        return trot;
    }
}
