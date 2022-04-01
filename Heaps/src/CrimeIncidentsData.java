import java.util.LinkedList;
public class CrimeIncidentsData {

	private int count;
	private LinkedList<CrimeIncidents> crimeIncidents;
	
	public CrimeIncidentsData(int count, LinkedList<CrimeIncidents> crimeIncidents) {
		super();
		this.count = count;
		this.crimeIncidents = crimeIncidents;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public LinkedList<CrimeIncidents> getCrimeIncidents() {
		return crimeIncidents;
	}

	public void setCrimeIncidents(LinkedList<CrimeIncidents> crimeIncidents) {
		this.crimeIncidents = crimeIncidents;
	}
	
}
