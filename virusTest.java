public class virusTest{
	
	public String startTest(File file){

		BufferedReader textFile = new BufferedReader(new FileReader(file));
		int computers;
		int triples;
		String line = null;

		int foo = Integer.parseInt("1234");

		if (( line = textfile.readLine()) != null){
			computers = Integer.parseInt(line);
		}

		if (( line = textfile.readLine()) != null){
			triples = Integer.parseInt(line);
		}


	}


}