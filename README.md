# Alexa Skills Kit SDK Sample using JAVA - Weather check
A simple [AWS Lambda](http://aws.amazon.com/lambda) function that demonstrates how to use weather check skill in java which basically needs an autorization of the user name and the passkey for the Amazon Echo using the Alexa SDK. It basically provides you the info of the weather on the basis of the features you asking for or full details of city weather with this it also provide you the chances of rain or does there is need to take umbrella with you.

## Concepts
This simple sample has no external dependencies or session management, and shows the example of how to create a Lambda function for handling Alexa Skill requests in java to featch weather details with autorization.

## Prerequisites
1. If you want to test my skills in alexa store than you can ask me for Beta test access through mail on hemantlodha1000@gmail.com.
2. Check in mail you got an link to enable my skill in your alexa store app.
3. Finally, use the pharse Alexa, open weather check or help if any problem you faced.

## Usage
1. Invocation:
  * Say Alexa,open weather check
2. Login(Authentication):
  * You need to tell skill your first name ("Hemant").
  * Then, you need to provide the passkey("1234").
  * The credentials provide in bracket can be used publicly.
3. Logged in:
  * Once you are verified than you can ask all weather related query about rain, weather condition, particular features or full features of weather.
4. Additional:
  * To make it more interactive i have added the basic greets like thank you, welcome.
5. Session End:
  * To logged out or to end session you can say bye, cancel, stop.

## Example Queries
* weather of Delhi
## Setup
To run this example skill you need to do two things. The first is to deploy the example code in lambda, and the second is to configure the Alexa skill to use Lambda. 

### AWS Lambda Setup
Refer to [Hosting a Custom Skill as an AWS Lambda Function](https://developer.amazon.com/docs/custom-skills/host-a-custom-skill-as-an-aws-lambda-function.html) reference for a walkthrough on creating a AWS Lambda function with the correct role for your skill. When creating the function, select the “Author from scratch” option, and select the Java 8 runtime.

To build the sample, open a terminal and go to the directory containing pom.xml, and run 'mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package'. This will generate a zip file named "helloworld-1.0-jar-with-dependencies.jar" in the target directory.
 
Once you've created your AWS Lambda function and configured “Alexa Skills Kit” as a trigger, upload the JAR file produced in the previous step and set the handler to the fully qualified class name of your handler function. Finally, copy the ARN for your AWS Lambda function because you’ll need it when configuring your skill in the Amazon Developer console.

### Alexa Skill Setup
Please refer to [Developing Your First Skill](https://developer.amazon.com/docs/alexa-skills-kit-sdk-for-java/develop-your-first-skill.html) for detailed instructions.
