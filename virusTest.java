/**
 * File: Digraph.java
 * 
 * This is my implmentation of a class to test if Digraph and CompNode are coded correctly for the given assignment. This program will determine whether a computer could have been infected by a certain time given a virus originating at a specific point in trace data. 
 *@author Steven Yee
 *@version 1.0 10/10/14
 */

import java.io.*;

public class VirusTest{
	
	/*
	 * This method takes in trace data and indicates whether or not a computer would be infected by a certain time.
	 *@param file where file contains the trace data.
	 */
	static public void startTest(File file){

		try{
		BufferedReader textFile = new BufferedReader(new FileReader(file));
		try{

		int computers;
		int triples;
		String line = null;

		line = textFile.readLine();
		computers = Integer.parseInt(line);

		line = textFile.readLine();
		triples = Integer.parseInt(line);

		Digraph nodeGraph = new Digraph(computers, triples);

		for (int i = 0; i < triples; i++){
			line = textFile.readLine();
			String[] splitted = line.split("\\s+");
			int comp1 = Integer.parseInt(splitted[0]);
			int comp2 = Integer.parseInt(splitted[1]);
			int time = Integer.parseInt(splitted[2]);

			nodeGraph.processTriple(comp1, comp2, time);
		}

		int startComputer, endComputer, startTime, endTime;

		line = textFile.readLine();
		String[] virusS = line.split("\\s+");
		startComputer = Integer.parseInt(virusS[0]);
		startTime = Integer.parseInt(virusS[1]);

		line = textFile.readLine();
		String[] virusE = line.split("\\s+");
		endComputer = Integer.parseInt(virusE[0]);
		endTime = Integer.parseInt(virusE[1]);

		nodeGraph.checkVirus(startComputer, endComputer, startTime, endTime);  

		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	/*
	 * This is my main for the assignment.
	 */
	public static void main (String... aArguments) throws IOException {

		File testing = new File(System.getProperty("user.dir") + "/test");
		startTest(testing);
	}

}