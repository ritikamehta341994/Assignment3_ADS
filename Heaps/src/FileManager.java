import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileManager {
	
	/**
	 * This method reads the data from the crime-incidents.csv file and adds it into the crimeList
	 * @return
	 */
	
	public LinkedList<CrimeIncidents> getCrimeData(){
		LinkedList<CrimeIncidents> crimeList = new LinkedList<>();
		try {
			  //Create object of file
		      File myObj = new File("crime-incidents.csv");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        //Process all records except for the headings and data that contains special characters "
		        if(!data.contains("incident") && !data.contains("\"")) {
		        	String[] crimeData = data.split(",");
		        	//Create CrimeIncidents object from the csv data
			        CrimeIncidents crimeIncidents = new CrimeIncidents(Long.parseLong(crimeData[0]),crimeData[1], crimeData[2],crimeData[3].trim(),crimeData[4],Integer.parseInt(crimeData[5].trim())); 
			        crimeList.add(crimeIncidents);
		        }
		        
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return crimeList;
	}
}
