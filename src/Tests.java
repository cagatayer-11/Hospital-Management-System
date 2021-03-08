
public class Tests extends ExaminationDecorator {

	
	

	public Tests(Examination patient) {
		super(patient);
	}

	@Override
	public String printOperations() {
		return   examination.printOperations()+"tests ";
		
	}

	@Override
	public int cost() {
		return 7+examination.cost();
		
	}

}
