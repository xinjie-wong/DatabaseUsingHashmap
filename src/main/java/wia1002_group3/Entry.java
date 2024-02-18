/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wia1002_group3;
/**
 *
 * @author Xin Jie
 */
public class Entry<E>{
    
    //key and value store as pair in the hashmap
    final String key;
    DataValue value;

    //pointer system to traverse within the hashmap
    Entry next;

    public Entry(String k, DataValue v){
        this.key = k;
        this.value = v;           
    }

    //getters and setters
    public DataValue getValue(){
        return value;
    }

    public void setValue(DataValue newValue){
        this.value = newValue; 
    }

    public String getKey(){
        return key;
    }
    
}

