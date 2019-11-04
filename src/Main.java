import java.util.Scanner;

public class Main {

    public static final String ADD_CLIENT = "ADCLIENTE", REM_CLIENT = "REMCLIENTE", ADD_TROT = "ADTROT",
                                CLIENT_DATA = "DADOCLIENTE", TROT_DATA = "DADOSTROT", DEPOSIT_BALANCE = "CARRSALDO",
                                CLIENTS_TROT = "TROT", TROTS_CLIENT = "CLIENTE",
                                RENT = "ALUGAR", RELEASE = "LIBERTAR",
                                SYSTEM_STATE = "ESTADOSISTEMA", EXIT = "SAIR";

    public static final int RENT_COST = 100, RENT_LIMIT = 60, DELAY = 30, INITIAL_BALANCE = 200, MIN_FOR_RENTAL = 100;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Manager manager = new Manager(RENT_COST, RENT_LIMIT, DELAY, INITIAL_BALANCE, MIN_FOR_RENTAL);

        String command = "";
        while (!command.equals(EXIT))
        {
            command = s.next().toUpperCase(); // first token in this line

            handleOperation(command, manager, s);
            s.nextLine(); // consumes the "\n" (new line character)
        }

    }

    public static void handleOperation(String command, Manager manager, Scanner s) {

        switch (command) { //evaluates the value of the command

            case RELEASE:
                processRelease(manager, s);

            case RENT:
                processRental(manager, s);

            case DEPOSIT_BALANCE:
                processDeposit(manager, s);

            case TROT_DATA:
                processTrotData(manager, s);

            case CLIENT_DATA:
                processClientData(manager, s);

            case ADD_TROT:
                processAddTrot(manager, s);
            
            case REM_CLIENT:
                processRemoveClient(manager, s);
                break;

            case ADD_CLIENT:
                processAddClient(manager, s);
                break;

            default:
                System.out.println("Comando Invalido");
        }

    }

    private static void processRelease(Manager manager, Scanner s) {

        String idTrot = s.next();
        int minutes = s.nextInt();
        Trot trot = manager.fetchTrot(idTrot);

        if (minutes <= 0) {
            System.out.println("Valor invalido");
            return;
        }

        if (trot == null) {
            System.out.println("Trotinete inexistente.");
            return;
        }

        if (manager.finishRental(trot, minutes)) System.out.println("Aluguer terminado.");
        else System.out.println("Trotinete nao alugada");
    }

    private static void processRental(Manager manager, Scanner s) {

        String NIF = s.next();
        String idTrot = s.next();
        Client client = manager.fetchClient(NIF);
        Trot trot = manager.fetchTrot(idTrot);

        if (client == null) {
            System.out.println("Cliente inexistente");
            return;
        }

        if (trot == null) {
            System.out.println("Trotinete inexistente");
            return;
        }

        if (trot.isRented()) {
            System.out.println("Trotinete nao pode ser alugada.");
            return;
        }

        // The method return whether or not it was able to start the rental. Based on the minimum value to start the rental.
        if (manager.startRental(client, trot)) System.out.println("Aluguer efectuado com sucesso.");
        else System.out.println("Cliente sem saldo suficiente.");
    }

    private static void processDeposit(Manager manager, Scanner s) {

        String NIF = s.next();
        int value = s.nextInt();
        Client c = manager.fetchClient(NIF);

        if (value <= 0) {
            System.out.println("Valor invalido.");
            return;
        }

        if (c == null) System.out.println("Cliente inexistente.");
        else {
            c.deposit(value);
            System.out.println("Carregamento efectuado");
        }
    }

    private static void processTrotData(Manager manager, Scanner s) {

        String idTrot = s.next();
        Trot trot = manager.fetchTrot(idTrot);

        if (trot == null) System.out.println("Trotinete inexistente.");
        else {
            System.out.println(String.format("%s: %s, %s, %s", trot.getRegistration(), trot.getState(),
                    trot.getNumberOfRentals(), trot.getTotalTimeSpent()));
        }
    }

    private static void processClientData(Manager manager, Scanner s) {

        String NIF = s.next();
        Client client = manager.fetchClient(NIF);

        if (client == null) System.out.println("Cliente inexistente.");
        else {
            System.out.println(String.format("%s: %s, %s, %s, %s, %s, %s, %s, %s, %s", client.getName(), client.getNIF(),
                    client.getEmail(), client.getPhoneNumber(), client.getBalance(), client.getTotalTimeSpent(),
                    client.getNumberOfRentals(), client.getMaxTimeSpent(), client.getMinTimeSpent(),
                    client.getTotalSpent()));
        }
    }

    private static void processAddTrot(Manager manager, Scanner s) {

        String idTrot = s.next();
        String registration = s.next();

        if (manager.addTrot(idTrot, registration)) {
            System.out.println("Insercao de trotinete com sucesso.");
        }
        else {
            System.out.println("Trotinete existente");
        }

    }


    public static void processRemoveClient(Manager manager, Scanner s) {

        String NIF = s.next();

        if (manager.remClient(NIF)) {
            System.out.println("Cliente removido com sucesso.");
        }
        else {
            System.out.println("Cliente inexistente.");
        }
    }
    
    public static void processAddClient(Manager manager, Scanner s) {

        String NIF = s.next();
        String email = s.next();
        int phoneNumber = s.nextInt();

        s.useDelimiter("\n"); // Makes the scanner scan until "\n" on the next token instead of " " (space).
        String name = s.next();
        s.reset(); // Resets the delimiter to its default value.

        if (manager.addClient(NIF, email, phoneNumber, name)) {
            System.out.println("Insercao de cliente com sucesso.");
        }
        else {
            System.out.println("Cliente existente.");
            
        }
    }

    
}
