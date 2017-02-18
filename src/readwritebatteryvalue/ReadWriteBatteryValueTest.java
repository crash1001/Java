/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwritebatteryvalue;


public class ReadWriteBatteryValueTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReadLogFile test = new ReadLogFile();
        BatteryRecord record = new BatteryRecord();
        record.setLogFileName("charge test.txt");
        record.setOutputCsvFile("output.csv");
        test.readFile(record); 
    }
    
}
