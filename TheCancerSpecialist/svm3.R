library(lattice)
library(ggplot2)
library(caret)
library(rpart)
library(e1071)
header <- F
dataURL <- "C:/Users/tam/Desktop/breastData.csv"
d<-read.csv(dataURL,header = header)
c = 2
d[[c]] <- factor(d[[c]],labels = c(0,1))


for( i in 1: nrow(d)){
  for( j in 1:ncol(d)){
    if ( d[i,j] == '?'){
      print(d[i,j])
      d[i,j] <- gsub("?",0,d[i,j], fixed = TRUE)
    }
  }
}



sampleInstances<-sample(1:nrow(d),size = 0.9*nrow(d))
trainingData<-d[sampleInstances,]
testData<-d[-sampleInstances,]




cnames <- colnames(d)
y <- paste(cnames[c], "~", sep="")
count <- 1
if(c != 1) y <- paste(y,cnames[1] , sep="") else {
  y <- paste(y , cnames[2] , sep="") 
  count = 2
}

for(i in 1:length(cnames)){
  
  if(c != i && i != count){
    y <- paste(y, cnames[i], sep="+")
  }
}
y



y <- as.formula(y)


model1<- svm(y, data = trainingData, kernel = 'polynomial', degree = '2',  
             cost = '10')


testData$V2 <- as.numeric(testData$V2)






testData[1,1] <-7877.0
testData[1,2] <- 20.6
testData[1,3] <- 29.33
testData[1,4] <- 140.76
testData[1,5] <- 1265.0
testData[1,6] <- 0.1178
testData[1,7] <- 0.277
testData[1,8] <- 0.3514
testData[1,9] <- 0.152
testData[1,10] <- 0.2397
testData[1,11] <- 0.07016
testData[1,12] <- 0.726
testData[1,13] <- 1.595
testData[1,14] <- 5.772
testData[1,15] <- 86.22
testData[1,16] <- 0.006522
testData[1,17] <- 0.06158
testData[1,18] <- 0.07117
testData[1,19] <- 0.01664
testData[1,20] <- 0.02324
testData[1,21] <- 0.006185
testData[1,22] <- 25.74
testData[1,23] <- 39.42
testData[1,24] <- 184.6
testData[1,25] <- 1821.0
testData[1,26] <- 0.165
testData[1,27] <- 0.8681
testData[1,28] <- 0.9387
testData[1,29] <- 0.265
testData[1,30] <- 0.4087
testData[1,31] <- 0.124
testData[1,32] <- 86.22
testData <- testData[1,]

testData$V2 <- as.factor(testData$V2)

prediction <- predict(model1, testData, type = "class")
if(prediction == "M") result <- "B" else result <- "M"



all <- list(result=result)


