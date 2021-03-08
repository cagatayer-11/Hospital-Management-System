import java.io.IOException;

public interface PatientDao { 
	void createPatient(Patient p) throws IOException;
	void removePatient(int id) throws IOException;
	void printPatientList() throws IOException;
	void createAdmission(int pId,int aID) throws IOException;
	void addExamination(int aId,String operations) throws IOException;
	void totalCost(int aId) throws IOException;
	void updatePatientList() throws IOException;
	void updateAdmissionList() throws IOException;
	
	
}
