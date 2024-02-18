/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wia1002_group3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Xin Jie
 */

public class HashMap {
    
    //sizing based on 2^4 (bit size)
    static final int SIZE = 16;

    //creating hashmap
    Entry table[] = new Entry[SIZE];
    
    //keep track of the no. of element in the hashmap
    private int num;

    //check if the hashmap is empty
    public boolean isEmpty(){
        return num == 0;
    }

    //putting a key and its value into the hashmap
    public void put(String k, DataValue v){
        int hash = k.hashCode() % SIZE;
        if(hash<0){
            hash = hash*-1;
        }
        Entry e = table[hash];

        if (e != null){     //if there's an existing entry in table[hash] position
            //the entry's key matched k
            if (e.key.equals(k)){
                //override (update) the existing value of the key
                e.value = v;
            }else{
                while (e.next != null){
                    e = e.next;
                }
                //if there's no matched key, add a new entry
                Entry entryInOldBucket = new Entry(k, v);
                e.next = entryInOldBucket;
                num++;
            }
        }else{
            //create a new entry with key and its value
            Entry entryInNewBucket = new Entry(k, v);
            //adding the entry into the hashmap
            table[hash] = entryInNewBucket;
            num++;
        }
    }

    //getting the entry with matching key
    public Entry get(String k){
        int hash = k.hashCode() % SIZE;
        if(hash<0){
            hash = hash*-1;
        }
        Entry e = table[hash];

        while (e != null){      //if there's an existing entry in table[hash] position
            //check if the entry's key matches k
            //if existing, return the entry (not entry's value)
            if (e.key.equals(k)){
                return e;
            }
            
            //if it doesn't match, iterate and check every entry in the 'linked list' in that position
            e = e.next;
        }

        // return null if there's no entry with key 'k'
        return null;
    }
    
    //check if a key is in the hashmap
    public boolean containsKey(String k){
        int hash = k.hashCode() % SIZE;
        if(hash<0){
            hash = hash*-1;
        }
        Entry e = table[hash];

        //if there's an existing entry in that location
        while (e != null){
            //check if the key of that entry matches k
            //if it doesn't match, iterate and check every entry in the 'linked list' in that position
            if (e.key.equals(k)){
                return true;
            }
            e = e.next;
        }

        //return false if there is no entry in the location
        return false;    
    }
    
    //remove the key and its value if matched
    public void remove(String k){
        int hash = k.hashCode() % SIZE;
        if(hash<0){
            hash = hash*-1;
        }
        Entry e = table[hash];
        Entry previous = null;
        
        while (e != null){
            if (e.key.equals(k)){
                //if the entry e is not the first entry in the list
                if(previous != null){
                    previous.next = e.next;
                }else{
                    //if the entry to be removed is the first entry in the list
                    //make the next entry to replace the first entry
                    table[hash] = e.next;
                }
                e = null;
                num--;
                return;
            }
            
            previous = e;
            e = e.next;
        }
    }
    
    //clear all entries stored in the hashmap
    public void clear(){
        if(!isEmpty()){
            num = 0;
            for(int i=0; i<table.length; i++){
                table[i] = null;
            }
        }
    }
    
    //display all entries
    public void display(PrintStream printStream) {
        System.out.println("\nDisplay all data");
        
        //if the list is empty
        if (table == null) {
            System.out.println("[Database] Empty! Please insert data!");
            return;
        }
        
        printStream.printf("%-25s%-15s%-20s\n", "index", "type", "value");
        for(int i = 0; i < SIZE; i++) {
            if(table[i] != null) {
                Entry e = table[i];
                while(e != null) {
                    printStream.printf("%-25s%-15s%-20s\n", e.getKey(), e.getValue().getDataType(), e.getValue().getValue());
                    e = e.next;
                }
            }
        }
    }
    
    // Add this method to get all entries
    public List<Entry> getAllEntries() {
        List<Entry> allEntries = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            Entry e = table[i];
            while (e != null) {
                allEntries.add(e);
                e = e.next;
            }
        }

        return allEntries;
    }
    
    //filter by datatype
    public DefaultTableModel filter(String userInput){
        //follow the display code above
        //but check if e.getValue().getDataType() == datatype
        
        String datatype="";
        if(userInput.equalsIgnoreCase("num")){
            datatype = "number";
        }
        else if(userInput.equalsIgnoreCase("str")){
            datatype = "string";
        }
        else if(userInput.equalsIgnoreCase("char")){
            datatype = "character";
        }
        String[] columnNames = {"Index", "Type", "Value"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        System.out.println("\nDisplay all data of datatype("+datatype+")");
        
        //if the list is empty
        if (table == null) {
            System.out.println("[Database] Empty! Please insert data!");
            return model;
        }
       
        for(int i = 0; i < SIZE; i++) {
            if(table[i] != null) {
                Entry e = table[i];
                while(e != null) {
                    String index = e.getKey();
                    String type = e.getValue().getDataType();
                    String value = e.getValue().getValue().toString(); // Use toString() for a generic conversion
                    
                    if(type.equals(datatype)){
                        Object[] rowData = {index, type, value};
                        model.addRow(rowData);
                    }
                    e = e.next;
                }
            }
        }
        return model;
    }
    
    //save data into txt
    public void save(){
        try{
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("data.txt"));
            if(isEmpty()){
                System.out.println("Database is empty. No data saved.");
            }else{
                System.out.println("Storing data....");
                System.out.println("Data stored in data.txt!");
                for(int i = 0; i < SIZE; i++) {
                    if(table[i] != null) {
                        Entry e = table[i];
                        while(e != null) {
                            String output = e.key + ";" + e.getValue().getDataType() + ";" + e.getValue().getValue();
                            outputStream.println(output);
                            e = e.next;
                        }
                    }
                }
            }
            outputStream.close();
        }catch(IOException e){
            System.out.println("Problem with storing data");
        }
    }
   
}


