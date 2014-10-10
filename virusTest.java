import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.*;

public class VirusTest{
	
	static public boolean startTest(File file){

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

		return nodeGraph.checkVirus(startComputer, endComputer, startTime, endTime);  

		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}

		return false;


	}

	public static void main (String... aArguments) throws IOException {

		File testing = new File(System.getProperty("user.dir") + "/test");
		System.out.println("The virus is " + startTest(testing));
	}

}