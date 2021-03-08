
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
public String readFile(String scan) throws IOException {
		
	    String content = null;
	    File file = new File(scan);
	    FileReader reader = null;
	    try {
	        reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        content = new String(chars);
	        reader.close();
	    	} 
	    catch (IOException e) {
	        e.printStackTrace();
	    	} 
	    finally {
	        if(reader != null){
	            reader.close();
	        	}
	    	}
	    return content;
	}
	
}

