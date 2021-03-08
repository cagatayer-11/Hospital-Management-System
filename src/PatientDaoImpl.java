import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

class SortById implements Comparator<Patient>{  // I use comparator interface for sorting according to patient id.
	public int compare(Patient p1,Patient p2) {
		return p1.getId() - p2.getId();
		}
	}
class SortByName implements Comparator<Patient>{ // I use comparator interface for sorting according to patient name.
	public int compare(Patient p1, Patient p2) {
		return p1.getNameSurname().compareTo(p2.getNameSurname());
	}
}
class SortByAdmissionId implements Comparator<Patient>{ // I use comparator interface for sorting according to admission id .
		public int compare(Patient p1,Patient p2) {
			return p1.getAdmissionId() - p2.getAdmissionId();
			}
		}


public class PatientDaoImpl implements PatientDao{ 
	
	
	private ArrayList<Patient> patientList;
	private ArrayList<Patient> admissionList;
	
	
	public PatientDaoImpl() throws IOException {
		ReadFile read = new ReadFile();
		patientList = new ArrayList<Patient>();
		admissionList = new ArrayList<Patient>();
		
		
		String[] list1 = read.readFile("patient.txt").split("\\r?\\n");
		String[] list2 = read.readFile("admission.txt").split("\\r?\\n");
		
		for (String line1 : list1) { // This for loop makes creating patients and setting their admission id if created patient has a admission id.
			Patient p = new Patient(Integer.parseInt(line1.split("\t")[0]),line1.split("\t")[1],line1.split("\t")[2],line1.split("\t")[3]);
			patientList.add(p); // Save,created patient according to patient.txt.
			for(String line2 : list2) {
				if(!line2.split("\t")[0].equals("Outpatient") && !line2.split("\t")[0].equals("Inpatient") && Integer.parseInt(line2.split("\t")[1]) == p.getId()) {
			
					p.setAdmissionId(Integer.parseInt(line2.split("\t")[0]));
					admissionList.add(p); // Save , if patient has a admission id .
					
				}
			}
			
		}
		for(int j = -1 ; j<admissionList.size();j++) { // This for loop makes setting examination and operations to the patient who has a admission id.
			for(int i = 0;i<list2.length;i++) {
				if(list2[i].split("\t")[0].equals("Outpatient") || list2[i].split("\t")[0].equals("Inpatient")) {
					
					admissionList.get(j).setExaminationAndOperationType(list2[i]+"\n"+ admissionList.get(j).getExaminationAndeOperationType());
					
				}
				else {
					j++;
				}
		}
			}
			
	
	}
		
		
	@Override // from PatientDao interface
	public void createPatient(Patient p) throws IOException { // This method for creating new patient,adding to patienList and writing applied process to output.txt.
		patientList.add(p);
		String line = "Patient "+p.getId()+" "+p.getNameSurname().split(" ")[0]+" added";
		try{
			FileWriter writer = new FileWriter("output.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			PrintWriter out = new PrintWriter(bufferedWriter);
			out.write(line+"\n");
			out.close();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
		}

	@Override // from PatientDao interface.
	public void removePatient(int id) throws IOException { // This method for removing existing patient,deleting from patienList and writing applied process to output.txt.
		for (Iterator<Patient> iterator = patientList.iterator(); iterator.hasNext(); ) {
		    Patient p = iterator.next();
		    if (p.getId() == id) {
		        iterator.remove();
		        String line = "Patient " + p.getId() + " "+p.getNameSurname().split(" ")[0]+" removed";
		        try{
		        	FileWriter writer = new FileWriter("output.txt", true);
					BufferedWriter bufferedWriter = new BufferedWriter(writer);
					PrintWriter out = new PrintWriter(bufferedWriter);
		        	out.write(line+"\n"); 
		        	out.close();
		        }
		        catch (IOException e) {
		            e.printStackTrace();
		        }
		       
		    }
		    
		   
		}
		
		for (Iterator<Patient> iterator = admissionList.iterator(); iterator.hasNext(); ) {
		    Patient p = iterator.next();
		    if (p.getId() == id) {
		        iterator.remove();
		    }
		}
		
	}

	

	@Override // from PatientDao interface.
	public void createAdmission(int pId,int aID) throws IOException { // This method for creating new admission for patient,adding to admissionList and writing applied process to output.txt.
		
		for (Patient p : patientList ) {
			if(p.getId() == pId ) {
				p.setAdmissionId(aID);
				admissionList.add(p);
				String line = "Admission "+p.getAdmissionId()+" created";
				 try{
					 FileWriter writer = new FileWriter("output.txt", true);
					 BufferedWriter bufferedWriter = new BufferedWriter(writer);
					 PrintWriter out = new PrintWriter(bufferedWriter);
					 out.write(line+"\n");
					 out.close();
					 
				 }
				 catch (IOException e) {
			            e.printStackTrace();
			        }
				
				}
		
		}
	}
	@Override // from PatientDao interface.
	public void addExamination(int aId ,String operations) throws IOException {// This method for adding examination for patient and writing applied process to output.txt.
		
		for(Patient p : admissionList) {
			if(p.getAdmissionId() == aId) {
				p.setExaminationAndOperationType(operations+"\n"+ p.getExaminationAndeOperationType());
				String line = operations.split("\t")[0]+" examination added to admission "+p.getAdmissionId();
				try{
					FileWriter writer = new FileWriter("output.txt", true);
					BufferedWriter bufferedWriter = new BufferedWriter(writer);
					PrintWriter out = new PrintWriter(bufferedWriter);
					out.write(line+"\n");
					out.close();
				}
				catch (IOException e) {
		            e.printStackTrace();
		        }
				}
		}
	
	}

	@Override // from PatientDao interface.
	public void totalCost(int aId) throws IOException { // This method for calculating total cost for patient and writing applied process to output.txt.
		for (Patient p : admissionList) {
			if(p.getAdmissionId() == aId) {
				String line = "TotalCost for admission " + p.getAdmissionId();
				String line2 = makeDecorator(p.getExaminationAndeOperationType(),p);
				try{
					FileWriter writer = new FileWriter("output.txt", true);
					BufferedWriter bufferedWriter = new BufferedWriter(writer);
					PrintWriter out = new PrintWriter(bufferedWriter);
					out.write(line+"\n");
					out.write(line2+"\n");
					out.close();
				}
				catch (IOException e) {
		            e.printStackTrace();
		        }
			   
			}
		}
		
	}
	@Override // from PatientDao interface.
	public void printPatientList() throws IOException { // This method for writing patientList indexes to the output.txt.
		
		Collections.sort(patientList,new SortByName());
		try{
		FileWriter writer = new FileWriter("output.txt", true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		PrintWriter out = new PrintWriter(bufferedWriter);
		out.write("Patient List:"+"\n");
		for(Patient p : patientList) {
			String line = p.getId() +" "+p.getNameSurname()+" "+p.getPhoneNumber()+" "+p.getAddress();
				out.write(line+"\n");
				
				}
		out.close();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	public  String makeDecorator(String operation,Patient p) { // This method use decorator pattern design , it makes decorator given patient.
																// Return string for writing operations to  output.txt.	
		
		ArrayList<String> array = new ArrayList<String>();
	
		String operations = null ;
		for(String s : operation.split("\\r?\\n")){
			if(!s.equals("null")) {
				array.add(s);
				}
		}
		
		Examination examination = null;
		
		for (int z = 0;z<array.size();z++) {
			
			if(array.get(z).split("\t")[0].equals("Inpatient")) {
				
				Examination e = new InPatient();
				examination = e; 
			}
			
			else {
				
				Examination e = new OutPatient();
				examination = e;
			}
			
			if(array.get(z).split("\t")[1].contains("tests")) {
				Examination e2 = new Tests(examination);
				examination = e2;
			}
			if(array.get(z).split("\t")[1].contains("doctorvisit")) {
				Examination e2 = new DoctorVisit(examination);
				examination = e2;
			}
			if(array.get(z).split("\t")[1].contains("measurements")) {
				Examination e2 = new Measurements(examination);
				examination = e2;
			}
			if(array.get(z).split("\t")[1].contains("imaging")) {
				Examination e2 = new Imaging(examination);
				examination = e2;
			}
			p.setTotalCost(examination.cost()+p.getTotalCost());
			operations = "\t"+examination.printOperations()+" "+examination.cost()+"$"+"\n"+operations;
			
			}
		
		return operations.replace("null","\t"+"Total: " +p.getTotalCost()+"$");
	}


	@Override // from PatientDao interface.
	public void updatePatientList() throws IOException {// This method for updating last version of patientList and  writing updating version to patient.txt.
		
		try {
			File file = new File("patient.txt");
			file.delete();
            FileWriter writer = new FileWriter("patient.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            Collections.sort(patientList,new SortById());
            for (Patient p : patientList) {
            	bufferedWriter.write(p.getId()+"\t"+p.getNameSurname()+"\t"+p.getPhoneNumber()+"\t"+p.getAddress()+"\n");
            }
            bufferedWriter.close();
	}
	 catch (IOException e) {
            e.printStackTrace();
        }
	}


	@Override // from PatientDao interface.
	public void updateAdmissionList() throws IOException { // This method for updating last version of admissionList and writing updating version to admission.txt.
		try {
			File file = new File("admission.txt");
			file.delete();
            FileWriter writer = new FileWriter("admission.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            Collections.sort(admissionList,new SortByAdmissionId());
            for (Patient p : admissionList) {
            	bufferedWriter.write(p.getAdmissionId()+"\t"+p.getId()+"\n");
            	bufferedWriter.write(p.getExaminationAndeOperationType());
            }
            
            bufferedWriter.close();
	}
	 catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}


	

}
