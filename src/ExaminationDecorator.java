
public abstract class ExaminationDecorator implements Examination{
protected Examination examination;


public ExaminationDecorator(Examination examination) {
	
	this.examination = examination;
}
@Override // from Examination interface.
public String printOperations() {
	return examination.printOperations();
	
	}

@Override // from Examination interface.
public int cost() {
	return examination.cost() ;
}

}
