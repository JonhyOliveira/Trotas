public class Trot {

    private String idTrot, registration;
    private int rentals, totalMinutes;
    private Trot lastRentalState;

    private boolean rented, activated;

    public Trot(String idTrot, String registration) {

        this.idTrot = idTrot;
        this.registration = registration;

        this.rented = false;
        this.activated = true;

        rentals = totalMinutes = 0;

        lastRentalState = null;
    }

    private Trot(Trot trot) {

        idTrot = trot.idTrot;
        registration = trot.registration;

        rentals = trot.rentals;
        totalMinutes = trot.totalMinutes;
    }

    public void setActive(boolean state) {
        activated = state;
    }

    /**
     * Registers a new rental.
     * @param minutes amount of minutes to add.
     */
    public void registerRental(int minutes) {

        lastRentalState = new Trot(this);

        totalMinutes += minutes;
        rentals ++;
        this.rented = false;
    }

    /**
     * Sets the rented status to true if it's not yet rented.
     * @return Whether or not it changed the rented state.
     */
    public boolean rent() {

        boolean success = false;

        if (!this.isRented()) {
            success = true;
            rented = true;
        }

        return success;
    }

    public void applyPromotion() {

        if (lastRentalState != null) {
            totalMinutes = lastRentalState.totalMinutes;
            rentals = lastRentalState.rentals;
        }

        lastRentalState = null;
    }

    public boolean isRented() {
        return rented;
    }

    public String getIdTrot() {
        return idTrot;
    }

    public String getRegistration() {
        return registration;
    }

    public int getNumberOfRentals() {
        return rentals;
    }

    public int getTotalTimeSpent() {
        return totalMinutes;
    }

    public String getState() {
        if (!isActivated()) return "inactiva";
        else if (isRented()) return "alugada";
        return "parada";
    }

    public boolean isActivated() {
        return activated;
    }
}
