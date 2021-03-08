import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Management {
	public void managementData(String file) throws IOException { // This my main method for management input.txt.  
		
		ReadFile read = new ReadFile();
		String[] input = read.readFile(file).split("\\r?\\n");
		PatientDao patientDao = new PatientDaoImpl();
		for (String line:input) { // This for loop , check conditions with respect to input.txt and using PatientDao interface methods. 
			if (line.split(" ")[0].equals("AddPatient")) { // In this if condition , I split line which is match with condition.
				ArrayList<String> addressTypeList = new ArrayList<String>();
				int id = Integer.parseInt(line.split(" ")[1]);
				String nameSurname = line.split(" ")[2]+" "+line.split(" ")[3];
				String phoneNumber= line.split(" ")[4];
				addressTypeList.add("Address:");
				for (int j = 5;j<line.split(" ").length;j++) {
					 addressTypeList.add(line.split(" ")[j]);
				}
				String address = addressTypeList.stream().map(Object::toString).collect(Collectors.joining(" "));
	            Patient p = new Patient(id,nameSurname,phoneNumber,address); // Create a new patient and send with createPatient method.
				patientDao.createPatient(p);
			}
			else if (line.split(" ")[0].equals("RemovePatient")) {
				patientDao.removePatient(Integer.parseInt(line.split(" ")[1])); //Send existing patient id ,in suitable for condition line, for removing operation.
				
			}
			else if (line.split(" ")[0].equals("CreateAdmission")) {
				patientDao.createAdmission(Integer.parseInt(line.split(" ")[2]),Integer.parseInt(line.split(" ")[1])); // Send patient id and admission id,for creating admission..
			}
			else if(line.split(" ")[0].equals("AddExamination")) {
				ArrayList<String> operationList = new ArrayList<String>();
				operationList.add(line.split(" ")[2]+"\t");
				for (int j = 3;j<line.split(" ").length;j++) {
					operationList.add(line.split(" ")[j]);
				}
				String operation =  operationList.stream().map(Object::toString).collect(Collectors.joining(" ")); 
				patientDao.addExamination(Integer.parseInt(line.split(" ")[1]),operation); // Send patient id  and operations for adding examination.		
			}
			else if (line.split(" ")[0].equals("TotalCost")) {
				patientDao.totalCost(Integer.parseInt(line.split(" ")[1])); // Send patient id , for showing totalCost of patient. 
				
			}
			else if (line.split(" ")[0].equals("ListPatients")) {
				patientDao.printPatientList(); // Show patientList;
			}
			
		}
		
		patientDao.updatePatientList(); // for updating patienList;
		patientDao.updateAdmissionList(); //for updating admissionList;
	}
	
}
