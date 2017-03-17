import java.io.*;


public class FileRW {
	private final String filename = "TopScore.dat"; 
	private File file = new File(filename);

	
	
	public void fileWrite(int topScore) throws IOException{
		if(!file.exists()){
			System.out.println("Could not find file!\nCreating new file, this is normal\nif this is first time running\nor if you reset score.");
			file.createNewFile();
		}	
		PrintStream pS = new PrintStream(file);
		pS.print("" + topScore);
		pS.close();
	}
	
	
	public int fileRead() throws IOException{
		FileReader fR = new FileReader(file);
		BufferedReader bR = new BufferedReader(fR);
		int score = Integer.parseInt(bR.readLine());
		bR.close();
		fR.close();
		return score;
	}
	
	
}
