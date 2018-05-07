package reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    BufferedReader reader = null;

    public ArrayList<String> readAvailableCommands(){
        String text;
        ArrayList<String> resultList = new ArrayList<String>();
        try {
            reader = new BufferedReader(new java.io.FileReader("src/main/resources/commands.txt"));
            while((text = reader.readLine()) != null){
                resultList.add(text);
            }
            return resultList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> readProfessions(){
        String text;
        ArrayList<String> resultList = new ArrayList<String>();
        try {
            reader = new BufferedReader(new java.io.FileReader("src/main/resources/profession.txt"));
            while((text = reader.readLine()) != null){
                resultList.add(text);
            }
            return resultList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 0 elem - url
     * 1 elem - user
     * 2 elem - password
     * */
    public List<String> readConnectionData(){
        String text;
        ArrayList<String> resultList = new ArrayList<String>();
        try {
            reader = new BufferedReader(new java.io.FileReader("src/main/resources/URL.txt"));
            while((text = reader.readLine()) != null){
                resultList.add(text);
            }
            return resultList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
