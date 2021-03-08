
public class OutPatient implements Examination {

	
	@Override // from Examination interface.
	public String printOperations() {
		return "Outpatient ";
		
	}

	@Override // from Examination interface.
	public int cost() {
		return 15;
		
		
	}

}
