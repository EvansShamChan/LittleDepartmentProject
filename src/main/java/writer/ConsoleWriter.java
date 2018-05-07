package writer;

import reader.FileReader;

import java.util.List;

public class ConsoleWriter {

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

    public String convertIntoValidResult(List<Integer> numbers){
        List<String> professions = new FileReader().readProfessions();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < professions.size(); i++) {
            builder.append(professions.get(i) + " - " + numbers.get(i) + "\n");
        }
        String result = builder.toString();
        return result;
    }


}
