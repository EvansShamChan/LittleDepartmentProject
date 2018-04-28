import service.ConsoleService;


public class Main {
    public static void main(String[] args) {
        while(true){
            ConsoleService.writeAvailableFunctions();
            String command = ConsoleService.readInputCommand();//todo: write null check
            if(command == null){
                System.out.println("Invalid command: please try again");
                continue;
            }
            ConsoleService.execute(command);
            System.out.println("Press ENTER to continue");
            ConsoleService.readInputCommand();
        }
    }
}
