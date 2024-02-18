/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wia1002_group3;
/**
 *
 * @author Xin Jie
 */

//for int, float, double, long
public class NumericValue extends DataValue{
    private String num;
    private String dataType;    //integer, double, float, long
    
    public NumericValue(String dataType, String num){
        this.num = num;
        this.dataType = dataType;
    }
    
    @Override
    //public String getValue(){
    public Object getValue(){
        return num;
    }
    
    @Override
    public String getDataType(){
        return dataType;
    }
    
}
