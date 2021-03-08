
public class Measurements extends ExaminationDecorator  {





	public Measurements(Examination patient) {
		super(patient);
	}

	@Override 
	public String printOperations() {
		return  examination.printOperations()+"measurements ";
		
	}

	@Override 
	public int cost() {
		return 5+examination.cost();
		
	}

}
