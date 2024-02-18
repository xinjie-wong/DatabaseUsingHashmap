/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wia1002_group3;
/**
 *
 * @author Xin Jie
 */

//for string, char
public class StringValue extends DataValue{
    private String s;
    private String dataType;    //string, char
    
    public StringValue(String dataType, String s){
        this.s = s;
        this.dataType = dataType;
    }
    
    @Override
    //public String getValue(){
    public Object getValue(){
        return s;
    }
    
    @Override
    public String getDataType(){
        return dataType;
    }
    
}
