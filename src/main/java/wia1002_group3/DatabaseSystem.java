package wia1002_group3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintStream;
import static java.lang.Integer.SIZE;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Xin Jie
 */

public class DatabaseSystem{
    private HashMap databaseSpine = new HashMap();
    
    //add data into the hashmap
    public void add(String index, String dataType, String value){
                
        switch(dataType){
            case "num":
                if(validateInput("num", value)){
                    System.out.println("Data inserted!");
                    databaseSpine.put(index, new NumericValue("number", value));
                }else{
                    System.out.println("Error! Invalid input. Please try again.");
                }
                break;
                
            case "str":
                //no need to validate
                System.out.println("Data inserted!");
                databaseSpine.put(index, new StringValue("string", value));
                break;
                
            case "char":
                if(validateInput("char", value)){
                    System.out.println("Data inserted!");
                    databaseSpine.put(index, new StringValue("character", value));
                }else{
                    System.out.println("Error! Invalid input. Please try again.");
                }
                break;
                
            default:
                System.out.println("Invalid datatype. Please try again.");
                break;
        }
        
    }
    
    //retrieve data into the hashmap
    public void get(String index){
        if(!databaseSpine.isEmpty()){
            Entry e = databaseSpine.get(index);
            if(e != null){
                System.out.println("Index: " + index);
                System.out.println("Value: " + e.getValue().getValue());
            }else{
                System.out.println("Data with index " + index + " does not exist.");
            }
        }else{
            System.out.println("The database is empty. No data retrieved. ");
        }
    }
    
    //delete data from the hashmap
    public void delete(String index){
        if(!databaseSpine.isEmpty()){
            if(databaseSpine.containsKey(index)){
                databaseSpine.remove(index);
                System.out.println("Data deleted!");
            }else
                System.out.println("No data with index " + index +" . No data deleted.");
        }else{
            System.out.println("The database is empty. No data deleted.");
        }
    }
    
    //clear all data in the hashmap
    public void clear(){
        if(!databaseSpine.isEmpty()){
            System.out.println("All data has been cleared!");
            databaseSpine.clear();
        }else{
            System.out.println("The database is empty. No data being cleared.");
        }
    }
    
    //display all elements in the hashmap
    public void display(PrintStream printStream){
        databaseSpine.display(printStream);
    }
    
    // Modified display method for JTable
    public DefaultTableModel display() {
        String[] columnNames = {"Index", "Type", "Value"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Get all entries from the HashMap
        List<Entry> allEntries = databaseSpine.getAllEntries();

        for (int i = 0; i < HashMap.SIZE; i++) {
        Entry e = databaseSpine.table[i];
        while (e != null) {
            String index = e.getKey();
            String type = e.getValue().getDataType();
            String value = e.getValue().getValue().toString(); // Use toString() for a generic conversion

            Object[] rowData = {index, type, value};
            model.addRow(rowData);

            e = e.next;
        }
    }

        return model;
    }

    
    //print data of selected datatype
    public DefaultTableModel filter(String datatype){
        //check if dataspine is empty
        //if not, proceed to filter
        DefaultTableModel model=null;
        
        if(!databaseSpine.isEmpty()){
            model = databaseSpine.filter(datatype);
        }
        else{
            System.out.println("databaseSpine is empty");
        }
        
        return model;
    }
    
    //save data into txt
    public void save(){
        databaseSpine.save();
    }
    
    //restore data from txt
    public void restore(){
        try{
            Scanner inputStream = new Scanner(new FileInputStream("data.txt"));
            if(inputStream.hasNextLine()){
                System.out.println("Data restored!");
                while(inputStream.hasNextLine()){
                    String[] input = inputStream.nextLine().split(";");
                    switch(input[1]){
                        case "number":
                            databaseSpine.put(input[0], new NumericValue("number", input[2]));
                            break;

                        case "string":
                            databaseSpine.put(input[0], new StringValue("string", input[2]));
                            break;

                        case "character":
                            databaseSpine.put(input[0], new StringValue("character", input[2]));
                            break;

                        default:
                            break;
                    }
                }
            }else{
                System.out.println("No historical data.");
            }
            inputStream.close();
        }catch(FileNotFoundException e){
            System.out.println("No historical data. No data restored.");
        }
    }
    
    //validate input based on data type
    private static boolean validateInput(String dataType, String input) {
        if (input.length() == 0) return false;
        
        //this is for collections, but macam got a bit error (or maybe no)
        if (input.contains(",")) {
            String[] inputs = input.split(",");
            for (String s : inputs) {
                if(!validateInput(dataType, s))
                    return false;
            }
            return true;
        }

        switch (dataType) {
            case "num":
                try {
                    Double.parseDouble(input);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
                
            case "char":
                return input.length() == 1;
                
            default:
                return false;
        }
    }


}


