import rcaller.RCaller;
import rcaller.RCode;

public class RProcessor {
	
	public static void processData()
	{
		try {
  	      RCaller caller = new RCaller();
  	      caller.setRscriptExecutable("C:/Program Files/R/R-3.2.2/bin/Rscript");

  	      RCode code = new RCode();
  	      code.clear();

  	      code.R_source("logistic.R");
  	      
  	      caller.setRCode(code);
  	      caller.runAndReturnResult("acc");
  	      double[] results = caller.getParser().getAsDoubleArray("acc");
  	      System.out.println("acc is " + results[0]);
  	      
  	      caller.getCranRepos();
  	      //code.showPlot(file);
  	    } catch (Exception e) {
  	     e.printStackTrace();
  	    }
	 
	    }
	
}
