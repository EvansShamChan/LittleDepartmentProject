import execution.CommandDistributor;
import reader.InputReader;
import writer.ConsoleWriter;


public class Main {
    static InputReader reader = new InputReader();
    static CommandDistributor distributor = new CommandDistributor();

    public static void main(String[] args) {
        while(true){
            ConsoleWriter.writeAvailableFunctions();
            String command = reader.readInputCommand();
            if(command == null){
                System.out.println("Invalid command: please try again");
                continue;
            }
            distributor.distributeCommand(command);
            System.out.println("Press ENTER to continue");
            reader.readInputCommand();
        }
    }
}
