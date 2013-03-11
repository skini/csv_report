package edu.mit.star.csv_report;

import java.util.*;
//Written by Shloka Kini
//This class parses a report into a HashMap, with the event count and
//average time for each person


public class Report {
	private String[] records;
	private HashMap<String, int[]> table = new HashMap();
	public Report(String s){
		records = s.split("\n");
		
	}
	//Fill the hashmap with records
	public void populateTable(){
		for(int j=0; j<records.length; j++){
			String[] record = parseRecord(records[j]);
			String name = record[0];
			int time = Integer.parseInt(record[1]);
			//Creates a small array to hold count and average
			int[] value = new int[2];
			if(table.containsKey(name)){
				//updates value count and average
				value[0] = ((int[])table.get(name))[0] + 1;
				value[1] = (((int[])table.get(name))[1] + time)/value[0];
				
			}
			else{
				//creates value count and average
				value[0] = 1;
				value[1] = time;
				
			}
			table.put(name, value);
			
		}
	}
	//parse a single record
	public String[] parseRecord(String str){
		String[] returnStr = str.split(",");
		return returnStr;
	}
	
	//print all records for debugging purposes
	public String getRecords(){
		String reportOutput = "";
		Iterator it = table.entrySet().iterator();
		//goes through each record in HashMap 
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        //getValue can only be of type object, so checks to make sure it is int[]
	        //then it parses that int[] to get count and average
	        Object o = pairs.getValue();
	        String output = "";
	        if(o instanceof int[]){
	        	int[] val = (int[]) o;
	        	output = "/Number of Events Attended: " + val[0] +"/Average time spent: "+ val[1];
	        }
	        reportOutput = reportOutput + "Name: " + pairs.getKey() + output + "\n";
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    return reportOutput;
	}
}
