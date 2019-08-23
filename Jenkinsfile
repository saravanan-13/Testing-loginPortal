pipeline {

    agent any
    
    stages {
     /*   
         stage ('Stop Ports Running'){
            steps {
              bat 'stop_port.bat'
            }
       
        }
         */
        
        stage ('Compile Stage') {
             steps {
                
               bat 'mvn -f Register_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               bat 'cd Register_Frontend && npm install && npm run build'
            
               bat 'mvn -f UserConfirmation_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               bat 'cd UserConfirmation_Frontend && npm install && npm run build'
                 
               bat 'mvn -f Forgotpassword_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               bat 'cd forgotpassword_frontend && npm install && npm run build'
            
               bat 'mvn -f DataRetrievalService_BackEnd/pom.xml clean install pmd:pmd sonar:sonar'
               bat 'mvn -f LoginService_BackEnd/pom.xml clean install pmd:pmd sonar:sonar'
               bat 'cd LoginService_Frontend && npm install && npm run build'
                
               bat 'mvn -f ChangePasswordService_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               bat 'cd ChangePasswordService_Frontend && npm install && npm run build'
            
               bat 'mvn -f Admin_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               bat 'cd Admin_Frontend && npm install && npm run build'
                
              bat 'mvn -f Edit_Profile_Backend/pom.xml clean install pmd:pmd sonar:sonar'
              bat 'cd Edit_Profile_Frontend && npm install && npm run build'
            
                 
            }
         }
       
        stage ('Code Review'){
            steps {
               script {
                step([$class: 'PmdPublisher', pattern: '**/target/pmd.xml'])
                }
            }
        
        }
         
         
         stage ('Deploy Stage') {
            steps {
                bat 'deploy.bat'
            }
         }
         
    }
}
