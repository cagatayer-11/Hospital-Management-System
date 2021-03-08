
public class InPatient implements Examination{

	
	@Override // from Examination interface.
	public String printOperations() {
		return"Inpatient ";
		
	}

	@Override // from Examination interface.
	public int cost() {
		return 10;
		
	}

}
