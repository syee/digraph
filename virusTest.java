public class virusTest{
	
	public String startTest<E>(File file){

		BufferedReader textFile = new BufferedReader(new FileReader(file));
		int computers;
		int triples;
		String line = null;
		Digraph<E> nodeGraph = new Digraph<E>();

		line = textFile.readLine(;);
		computers = Integer.parseInt(line);

		line = textFile.readLine();
		triples = Integer.parseInt(line);

		protected ArrayList<E> computerList = new ArrayList<E>(computers);
		protected ArrayList<E> nodeGraph = new ArrayList<E>(2*triples);

		for (int i = 0; i < triples; i++){

			line = textFile.readLine();
			String[] splitted = line.split("\\s+");
			int comp1 = splitted[0];
			int comp2 = splitted[1];
			int time = splitted[2];

			nodeGraph.processTriple(comp1, comp2, time);

		}

		int startComputer, endComputer, startTime, endTime;

		line = textFile.readLine();
		String[] virusS = line.split("\\s+");
		startComputer = virusS[0];
		startTime = virusS[1];

		line = textFile.readLine();
		String[] virusE = line.split("\\s+");
		endComputer = virusE[0];
		endTime = virusE[1];

		return nodeGraph.checkVirus(startComputer, endComputer, startTime, endTime);  




	}


}