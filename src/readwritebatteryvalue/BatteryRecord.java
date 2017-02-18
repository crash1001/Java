/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwritebatteryvalue;

/**
 *
 * @author ROOT
 */
public class BatteryRecord {
   
    
    int time=-5;                                                                //Interval in which readings where taken
    
    private String logFileName ="";                                             //Log File on which we would be working
    private String outputCsvFile="";
   
    private String[] batteryVoltage = new String[15];                           //BatteryNUmber as index+1 and value as BatteryVoltage

    private float columbCounter;
    private int targetVoltage;
    
    private final String searchBattery = "voltage of cell ";
    private final String searchColumbCounter = "columb counter";
    private final String searchTargetVoltage = "TARGET VOLTAGE";
    private final String delimiter = ": ";
    
    public void setOutputCsvFile(String path)
    {
        outputCsvFile = path;
    }
    public String getOutputCsvFile()
    {
        return outputCsvFile;
    }
    public void setLogFileName(String path)
    {
        logFileName = path;
    }
    public String getLogFileName()
    {
        return logFileName;
    }
    
    public String getSearchBatteryString()
    {
        return searchBattery;
    }
    
    public String getDelimiter()
    {
        return delimiter;
    }
    
    public String getSearchColumbCounterString()
    {
        return searchColumbCounter;
    }
    
    public String getSearchTargetVoltageString()
    {
        return searchTargetVoltage;
    }
         
    public void setBatteryVoltage(int batNum, String batVolt )
    {
       // int voltage = Integer.parseInt(batVolt);
        batteryVoltage[batNum-1] = batVolt;
    }
    public int getBatteryVoltage(int batNum)
    {
        int voltage = Integer.parseInt(batteryVoltage[batNum]);
        return voltage;
    }
    
  
    public void setColumbCounter(float colCount)
    {
//        float current=Float.parseFloat(colCount);
        columbCounter = colCount;
    }
    public float getColumbCounter()
    {
        return columbCounter;
    }
    
    public void setTargetVoltage(int tarVolt)
    {
//        int voltage = Integer.parseInt(tarVolt);
        targetVoltage = tarVolt;
    }
    public float getTargetVoltage()
    {
        return targetVoltage;
    }
    
    
}
