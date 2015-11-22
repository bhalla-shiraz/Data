import rcaller.RCaller;
import rcaller.RCode;

public class RProcessor {
	
	public static void processData()
	{
		int minIteration 			= 0;
		int maxIteration 			= 5;
		int chosenClusterIndex 		= 0;
		String[] classifiers = {"Dtree.R","Bagging.R","Boosting.R","KNN.R","logistic.R","NaiveBayes.R","neuralNet.R","RandomForest.R","SVMperceptron.R","svm.R"};
		// array 1d length = classifiers
		//each element is average of 5 runs
		float[]  avgAccuracy = new float[classifiers.length];
		for(int classifierIndex = minIteration; classifierIndex < classifiers.length; classifierIndex++){
			for(int iteration = minIteration; iteration < maxIteration; iteration++){
		
				avgAccuracy[classifierIndex] += testAllClassifiers(classifiers[classifierIndex]);
			}
			avgAccuracy[classifierIndex] = avgAccuracy[classifierIndex] / maxIteration;
		}
		
		//get the classifier with max average
		chosenClusterIndex = maxClassAvg(avgAccuracy);
		
		//process the data again on this classifier
		// TODO create a test array from the report
		finalTestClassifier(classifiers[chosenClusterIndex]);
		
	}
	
	public static void finalTestClassifier(String classifier){
		try {
    		System.out.println(classifier);
	  		  //Create an object to link java to R
	  	      RCaller caller = new RCaller();
	  	      caller.setRscriptExecutable("C:/Program Files/R/R-3.2.2/bin/Rscript");
	  	      
	  	      //creating a code object to compile in R
	  	      RCode code = new RCode();
	  	      //Clear previous code assignments to the code object
	  	      //not necessary here but should be used later on
	  	      code.clear();

	  	      //Get the code from a R code file created
	  	      code.R_source(classifier);
	  	      
	  	      //set code to the Rcaller object
	  	      caller.setRCode(code);
	  	      //get the result from return list with key = acc
	  	      caller.runAndReturnResult("acc");
	  	      
	  	      //extract the result array
	  	      double[] results = caller.getParser().getAsDoubleArray("acc");
	  	      System.out.println("Accuracy: "+(float)(results[0]*100));
	  	     
	  	      
	  	    } catch (Exception e) {
	  	     e.printStackTrace();
	  	    }
		
	
		
	}
	
	public static int maxClassAvg(float[] avgAccuracy){
		
		int clusterIndex 		= 0;
		float maxAccAvg 		= -2;
		
		for(int cluster = 0; cluster < avgAccuracy.length; cluster++)
		{
			if(avgAccuracy[cluster] > maxAccAvg){
				maxAccAvg = avgAccuracy[cluster];
				clusterIndex = cluster;
			}
		}
		
		System.out.println(maxAccAvg);
		return clusterIndex;
		
	}
	
	
	public static float testAllClassifiers(String file)
	{
		try {
    		System.out.println(file);
	  		  //Create an object to link java to R
	  	      RCaller caller = new RCaller();
	  	      caller.setRscriptExecutable("C:/Program Files/R/R-3.2.2/bin/Rscript");
	  	      
	  	      //creating a code object to compile in R
	  	      RCode code = new RCode();
	  	      //Clear previous code assignments to the code object
	  	      //not necessary here but should be used later on
	  	      code.clear();

	  	      //Get the code from a R code file created
	  	      code.R_source(file);
	  	      
	  	      //set code to the Rcaller object
	  	      caller.setRCode(code);
	  	      //get the result from return list with key = acc
	  	      caller.runAndReturnResult("acc");
	  	      
	  	      //extract the result array
	  	      double[] results = caller.getParser().getAsDoubleArray("acc");
	  	      System.out.println("Accuracy: "+(float)(results[0]*100));
	  	      return (float) (results[0]*100);
	  	      
	  	    } catch (Exception e) {
	  	     e.printStackTrace();
	  	    }
		return 0;
	}
	
}