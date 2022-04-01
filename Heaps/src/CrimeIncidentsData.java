import java.util.LinkedList;
/*
 * This class stores the count of crime incidents and the crime incidents for a particular zip code
 */

public class CrimeIncidentsData {

	private int count; //count of crime incidents for the zip code
	private LinkedList<CrimeIncidents> crimeIncidents; // list of crime incidents for the zip code
	
	/*
	 * Constructor for the class
	 */
	public CrimeIncidentsData(int count, LinkedList<CrimeIncidents> crimeIncidents) {
		super();
		this.count = count;
		this.crimeIncidents = crimeIncidents;
	}

	/*
	 * returns the count
	 */
	public int getCount() {
		return count;
	}

	/*
	 * sets the count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/*
	 * returns the list of crime incidents
	 */
	public LinkedList<CrimeIncidents> getCrimeIncidents() {
		return crimeIncidents;
	}

	/*
	 * sets the list of crime incidents
	 */
	public void setCrimeIncidents(LinkedList<CrimeIncidents> crimeIncidents) {
		this.crimeIncidents = crimeIncidents;
	}
	
}
