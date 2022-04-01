/**
 * Specifies the fields corresponding to the data present in the crime-incidents.csv file
 * @author ritzm
 *
 */
public class CrimeIncidents {
	
	private Long incidentId; // to store the incident Id of crime
	private String caseNumber; // to store the case number of crime
	private String incidentType; // to store the incident type of crime
	private String address; // to store the address of where the crime was committed
	private String dayOfWeek; // to store day of the week when crime was committed
	private int zipCode; // to store zip code of where the crime was committed
	
	/*
	 * constructor for crimeIncidents
	 */
	public CrimeIncidents(Long incidentId, String caseNumber, String incidentType, String address, String dayOfWeek,
			int zipCode) {
		
		this.incidentId = incidentId;
		this.caseNumber = caseNumber;
		this.incidentType = incidentType;
		this.address = address;
		this.dayOfWeek = dayOfWeek;
		this.zipCode = zipCode;
	}
	
	/**
	 * returns the incidentId
	 * @return incident Id
	 */
	public Long getIncidentId() {
		return incidentId;
	}
	/**
	 * sets the incidentId
	 * @param incidentId
	 */
	public void setIncidentId(Long incidentId) {
		this.incidentId = incidentId;
	}
	/*
	 *  returns the caseNumber
	 *  
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	
	/*
	 * sets the caseNumber
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	/*
	 *  returns incidentType
	 */
	public String getIncidentType() {
		return incidentType;
	}
	/*
	 *  sets incidentType
	 */
	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}
	/*
	 *  returns address
	 */
	public String getAddress() {
		return address;
	}
	/*
	 *  sets address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/*
	 *  return dayOfWeek
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	/*
	 *  sets dayOfWeek
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	/*
	 *  returns zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}
	/*
	 *  sets zipCodeS
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
