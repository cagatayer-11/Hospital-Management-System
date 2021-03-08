
public class Patient {
	//These attributes are unique to Patient.
	private int Id;
	private String nameSurname;
	private String phoneNumber;
	private String address;
	private int admissionId;
	private String examinationAndOperationType;
	private int totalCost;
	

	public Patient(int id, String nameSurname, String phoneNumber, String address) {
		this.Id = id;
		this.nameSurname = nameSurname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.examinationAndOperationType = "";
			}

	// Getter - Setter methods.
	public String getExaminationAndeOperationType() {
		return examinationAndOperationType;
	}

	public void setExaminationAndOperationType(String examinationAndOperationType) {
		this.examinationAndOperationType = examinationAndOperationType ;
	}

	public int getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public int getId() {
		return this.Id;
	}

	public String getNameSurname() {
		return this.nameSurname;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getAddress() {
		return this.address;
	}

	
	
}
