public class Trot {

    private String idTrot, registration;
    private int rentals, totalMinutes;

    private boolean rented;

    public Trot(String idTrot, String registration) {
        this.idTrot = idTrot;
        this.registration = registration;

        this.rented = false;

        rentals = totalMinutes = 0;
    }

    /**
     * Registers a new rental.
     * @param minutes amount of minutes to add.
     */
    public void registerRental(int minutes) {

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

    public boolean isRented() {
        return rented;
    }

    public String getIdTrot() {
        return idTrot;
    }

    public String getRegistration() {
        return registration;
    }

    public int getRentals() {
        return rentals;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }
}
