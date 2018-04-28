package service;

import dao.JdbcDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleService {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static JdbcDao jdbcDao = new JdbcDao();

    // ----------------------------------------------
    // check command correctness

    public static void writeAvailableFunctions(){
        System.out.println("-----------------------------------");
        System.out.println("Functions:");
        System.out.println("HEAD department_name - returns department heads name");
        System.out.println("STATISTIC department_name - shows department statistic");
        System.out.println("AVERAGE department_name - show the average salary for department");
        System.out.println("COUNT department_name - shows the number of employee for department");
        System.out.println("GLOBAL key_word - global search by key word");
        System.out.println("-----------------------------------");
    }

    public static String readInputCommand(){
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

    public static boolean isCommandValid(String command) {
        ArrayList<String> funcList = new ArrayList<String>();
        fillUpList(funcList);
        String[] arr = command.split(" ");
        String func = arr[0].toUpperCase();
        for (String s : funcList) {
            if(func.equals(s)){
                return true;
            }
        }
        return false;
    }

    public static void fillUpList(List<String> list){
        list.add("HEAD");
        list.add("STATISTIC");
        list.add("AVERAGE");
        list.add("COUNT");
        list.add("GLOBAL");
    }

    //----------------------------------------------
    //execute methods

    public static void execute(String command){
        ArrayList<String> funcList = new ArrayList<String>();
        fillUpList(funcList);
        String[] arr = command.split(" ");
        String func = arr[0].toUpperCase();
        String value = arr[1] + ((arr.length <= 2)? "": " " + arr[2]);
        if(func.equals("HEAD")){
            executeHeadCommand(value);
        } else if(func.equals("STATISTIC")){
            executeStatisticCommand(value);
        } else if(func.equals("AVERAGE")){
            executeAverageCommand(value);
        } else if(func.equals("COUNT")){
            executeCountCommand(value);
        } else if(func.equals("GLOBAL")){
            executeGlobalCommand(value);
        }
    }

    public static void executeHeadCommand(String value){
        String head = jdbcDao.getHeadOfDepartment(value);
        if(head == null){
            System.out.println("Error: department doesn't exist");
            return;
        }
        System.out.println("Head of " + value + " department is " + head + ".");
    }

    public static void executeStatisticCommand(String value){
        System.out.println(jdbcDao.getStatisticOfDepartment(value));
    }

    public static void executeAverageCommand(String value){
        System.out.println("The average salary of " + value + " is " + jdbcDao.getAverageSalary(value));
    }

    public static void executeCountCommand(String value){
        System.out.println(jdbcDao.getNumberOfEmployee(value));
    }

    public static void executeGlobalCommand(String value){
        List<String> searchLectors = jdbcDao.getSearchLectors(value);
        for (String searchLector : searchLectors) {
            System.out.println(searchLector);
        }
    }
}
