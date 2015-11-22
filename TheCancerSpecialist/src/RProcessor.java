import rcaller.RCaller;
import rcaller.RCode;

public class RProcessor {
	
	public static void processData(){
		try {
    		
  		  //Create an object to link java to R
  	      RCaller caller = new RCaller();
  	      caller.setRscriptExecutable("C:/Program Files/R/R-3.2.2/bin/Rscript");
  	      
  	      //creating a code object to compile in R
  	      RCode code = new RCode();
  	      //Clear previous code assignments to the code object
  	      //not necessary here but should be used later on
  	      code.clear();

  	      //Get the code from a R code file created
  	      code.R_source("RandomForest.R");
  	      
  	      //set code to the Rcaller object
  	      caller.setRCode(code);
  	      //get the result from return list with key = acc
  	      caller.runAndReturnResult("acc");
  	      
  	      //extract the result array
  	      double[] results = caller.getParser().getAsDoubleArray("acc");
  	      
  	      System.out.println("acc is " + results[0]*100 + "%");
  	      
  	      caller.getCranRepos();
  	     
  	    } catch (Exception e) {
  	     e.printStackTrace();
  	    }
	}
}