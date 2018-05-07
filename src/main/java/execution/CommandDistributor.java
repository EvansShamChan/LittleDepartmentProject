package execution;

import reader.FileReader;

import java.util.ArrayList;

public class CommandDistributor {
    Executor executor = null;

    public void distributeCommand(String command){
        ArrayList<String> funcList = new FileReader().readAvailableCommands();
        String[] arr = command.split(" ");
        String func = arr[0].toUpperCase();
        String value = arr[1] + ((arr.length <= 2)? "": " " + arr[2]);
        for (String s : funcList) {
            if(func.equals(s)){
                //make first char uppercase
                String name = func.substring(0, 1).toUpperCase() + func.substring(1).toLowerCase();

                String classpath = "execution." + name + "Executor";
                executor = howdy(classpath);
                executor.execute(value);
            }
        }
    }

    public static Executor howdy(String className) {
        try {
            Class clazz = Class.forName(className);
            return (Executor) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
