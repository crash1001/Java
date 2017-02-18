/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwritebatteryvalue;


import com.csvreader.CsvWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;


public class ReadLogFile {
    
    
    private int counter;    
        private int numberOfLines;
        private String searchString;
        String logBuffer;
        String[] inputBuffer;
        String[] checkBatteryNumber;

    public ReadLogFile() {
        
        
        this.counter = 0;
        this.numberOfLines=0;
        this.searchString="";
        this.logBuffer="";
        
    }
   
    public void readFile(BatteryRecord record)
    {
      
        try
        {
            FileInputStream input = new FileInputStream(record.getLogFileName());
            
            BufferedReader buffer = new BufferedReader (new InputStreamReader(input));
                    
            numberOfLines=0;
                                 
            while ((buffer.readLine()) != null)
            {
                numberOfLines++;
            }
            
            input.getChannel().position(0);
            
            inputBuffer = new String[numberOfLines]; 
            
             for(int j=0;j <numberOfLines;j++)
            {
                inputBuffer[j]=buffer.readLine();
            }
            input.close();
            buffer.close();
        }
        catch(IOException e)
        {
            System.err.println("Error While opening the file");
            System.exit(1);
        }
        catch(Exception e)
        {
            System.err.println("Some other exception occured");
            System.exit(1);
        }
        
        for (String inputBuffer1 : inputBuffer) {
            //Get each battery cell voltage
            if (inputBuffer1.contains(record.getSearchBatteryString())) {
                if(counter != 15)
                {
                    counter++;
                }
                else if(counter==15)
                {
                    counter =1;
                    csvFileWriter(record);
                   
                }
                String[] split = inputBuffer1.split(record.getDelimiter());
                {
                    searchString = record.getSearchBatteryString() + counter;
                    if(split[0].contains(searchString))
                    {
                        record.setBatteryVoltage(counter, split[1]);                        
                    }
                
                }
            }
            //get columb counter value
            if (inputBuffer1.contains(record.getSearchColumbCounterString())) {
                String[] split = inputBuffer1.split(record.getDelimiter());
                float val = Float.parseFloat(split[1]);
                record.setColumbCounter(val);
            }
            //get Charging TARGET VOLTAGE VALUE
            if (inputBuffer1.contains(record.getSearchTargetVoltageString())) {
                String[] split = inputBuffer1.split(record.getDelimiter());
                int val = Integer.parseInt(split[1]);
                record.setTargetVoltage(val);   
            }
        }    
    }

    
   
    public void csvFileWriter(BatteryRecord record)
    {
       
        //String outputFile = "charge test.csv";
        boolean alreadyExists = new File(record.getOutputCsvFile()).exists();
    
        try
        {
            CsvWriter csvOutputFile = new CsvWriter(new FileWriter(record.getOutputCsvFile(), true), ',');
            
            if(!alreadyExists)
            {

                csvOutputFile.write("Time (minutes)");
                for(int c=0;c<15;c++)
                {
                   
                    csvOutputFile.write("Cell " + Integer.toString(c+1) + " (mV)");
//                    csvOutputFile.endRecord();
                }
                csvOutputFile.endRecord();
            }
            
            record.time=record.time+5;
            csvOutputFile.write(Integer.toString(record.time));
             for(int c=0;c<15;c++)
            {
                
                csvOutputFile.write(Integer.toString(record.getBatteryVoltage(c)));
//                csvOutputFile.endRecord();
                
            }
             csvOutputFile.endRecord();
             csvOutputFile.close();
            
        }    
        catch(IOException e)
        {
            System.err.println("Error While opening the file");
            System.exit(1);
        }

    }
    
    
    
//    public static void main(String args[])
//    {
//        ReadLogFile test = new ReadLogFile();
//        BatteryRecord record = new BatteryRecord();
//        record.setLogFileName("charge test.txt");
//        record.setOutputCsvFile("output.csv");
//        test.readFile(record);
//     
//               
//    }
}
