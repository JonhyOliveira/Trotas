import java.util.Scanner;

public class Main {

    public static final String ADD_CLIENT = "ADCLIENTE", REM_CLIENT = "REMCLIENTE", ADD_TROT = "ADTROT",
                                CLIENT_DATA = "DADOCLIENTE", TROT_DATA = "DADOSTROT", DEPOSIT_BALANCE = "CARRSALDO",
                                CLIENTS_TROT = "TROT", TROTS_CLIENT = "CLIENTE",
                                RENT = "ALUGAR", RELEASE = "LIBERTAR",
                                SYSTEM_STATE = "ESTADOSISTEMA", EXIT = "SAIR";


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Manager manager = new Manager();

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
