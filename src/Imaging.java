
public class Imaging extends ExaminationDecorator {

	

	

	public Imaging(Examination patient) {
		super(patient);
		
	}

	@Override 
	public String printOperations() {
		return  examination.printOperations()+"imaging " ;
		
	}

	@Override 
	public int cost() {
		return 10+examination.cost();
		
	}

	

}
