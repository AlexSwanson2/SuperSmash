import java.io.*;


public class FileRW {
	String filename = "TopScore.dat"; 
	File file = new File(filename);

	
	
	public void fileWrite(int topScore) throws IOException{
		if(!file.exists()){
			System.out.println("Could not find file!\nCreating new file, this is normal\nif this is first time running\nor if you reset score.");
			file.createNewFile();
		}	
		PrintStream PS = new PrintStream(file);
		PS.print("" + topScore);
		PS.close();
	}
	
	
	public int fileRead() throws IOException{
		FileReader FR = new FileReader(file);
		BufferedReader BR = new BufferedReader(FR);
		int score = Integer.parseInt(BR.readLine());
		BR.close();
		FR.close();
		return score;
	}
	
	
}
