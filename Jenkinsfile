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
                
               sh 'mvn -f Register_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               sh 'cd Register_Frontend && npm install && npm run build'
            
               sh 'mvn -f UserConfirmation_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               sh 'cd UserConfirmation_Frontend && npm install && npm run build'
                 
               sh 'mvn -f Forgotpassword_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               sh 'cd forgotpassword_frontend && npm install && npm run build'
            
               sh 'mvn -f DataRetrievalService_BackEnd/pom.xml clean install pmd:pmd sonar:sonar'
               sh 'mvn -f LoginService_BackEnd/pom.xml clean install pmd:pmd sonar:sonar'
               sh 'cd LoginService_Frontend && npm install && npm run build'
                
               sh 'mvn -f ChangePasswordService_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               sh 'cd ChangePasswordService_Frontend && npm install && npm run build'
            
               sh 'mvn -f Admin_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               sh 'cd Admin_Frontend && npm install && npm run build'
                
               sh 'mvn -f Edit_Profile_Backend/pom.xml clean install pmd:pmd sonar:sonar'
               sh 'cd Edit_Profile_Frontend && npm install && npm run build'
            
                 
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
                sh './deploy.sh'
            }
         }
         
    }
}
