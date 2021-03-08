
public class DoctorVisit extends ExaminationDecorator  {


	

	public DoctorVisit(Examination patient) {
		super(patient);
		
	}

	@Override 
	public String printOperations() {
		return   examination.printOperations()+"doctorvisit ";
		
		
	}

	@Override 
	public int cost() {
		return 15+examination.cost();
		
	}

}
