import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException  {
		String file = new String(args[0]);
		Management management = new Management();
		management.managementData(file);
		
	}
}
