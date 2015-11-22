import java.io.IOException;
 
public class JavaPDFTest {
 
    public static void main(String[] args){
 
       PDFManager pdfManager = new PDFManager();
       pdfManager.setFilePath("C:/Users/Shonn/Desktop/Reports/Patient1.pdf");
       String file = null;
	try {
		file = pdfManager.ToText();
	} catch (IOException e) {
		System.out.println("unable to read the file");
		e.printStackTrace();
	}
       
       String[] tokens = file.split(" ");
       System.out.println(tokens[12]);
       System.out.println(tokens[16]);
       for(int index = 0; index < tokens.length; index++){
    	   try{
    		   System.out.println(Float.valueOf(tokens[index]));
    	   }catch(NumberFormatException e){
    		   	
    	   }
       }

}    
}