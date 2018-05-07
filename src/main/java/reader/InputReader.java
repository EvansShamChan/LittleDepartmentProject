package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InputReader {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readInputCommand(){
        try {
            String command = reader.readLine();
            if(isCommandValid(command)){
                return command;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //todo: write file fullfil
    public boolean isCommandValid(String command) {
        ArrayList<String> funcList = new FileReader().readAvailableCommands();
        String[] arr = command.split(" ");
        String func = arr[0].toUpperCase();
        for (String s : funcList) {
            if(func.equals(s)){
                return true;
            }
        }
        return false;
    }
}
