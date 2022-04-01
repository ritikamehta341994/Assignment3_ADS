/**
 * Specifies the fields corresponding to the data present in the crime-incidents.csv file
 * @author ritzm
 *
 */
public class CrimeIncidents {
	
	private Long incidentId;
	private String caseNumber;
	private String incidentType;
	private String address;
	private String dayOfWeek;
	private int zipCode;
	public CrimeIncidents(Long incidentId, String caseNumber, String incidentType, String address, String dayOfWeek,
			int zipCode) {
		
		this.incidentId = incidentId;
		this.caseNumber = caseNumber;
		this.incidentType = incidentType;
		this.address = address;
		this.dayOfWeek = dayOfWeek;
		this.zipCode = zipCode;
	}
	public Long getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(Long incidentId) {
		this.incidentId = incidentId;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getIncidentType() {
		return incidentType;
	}
	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
