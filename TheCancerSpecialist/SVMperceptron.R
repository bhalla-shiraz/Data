#import libraries
library(rpart)
library(e1071)
library(lattice)
library(ggplot2)
library(caret)
library(class)
header <- F
dataURL <- "C:/Users/tam/Desktop/breastData.csv"
d<-read.csv(dataURL,header = header)


c = 2
d[[c]] <- factor(d[[c]],labels = c(0,1))


for(i in 1:ncol(d)){
  a = sapply(d[[i]],is.factor)
  if(a[1] == TRUE && i != c) d[[i]] <- as.numeric(d[[i]])
}



sampleInstances<-sample(1:nrow(d),size = 0.9*nrow(d))
trainingData<-d[sampleInstances,]
testData<-d[-sampleInstances,]
# which one is the class attribute



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
y <- as.formula(y)


svmModel<- svm(y, data = trainingData, kernel = 'linear')
prediction <- predict(svmModel, testData, type = "class")
accuracy <- table(prediction, testData[[c]])

if(nrow(accuracy) == 1)acc = accuracy[1]/nrow(testData)else{
  #Accuracy given by confusionMatrix command
  confuse_mat <- confusionMatrix(accuracy)
  overal <- confuse_mat$overall
  accuracy <- overal['Accuracy']
  acc <-  as.numeric(format(round(accuracy, 2), nsmall = 2))
}

all <- list(acc=acc)
