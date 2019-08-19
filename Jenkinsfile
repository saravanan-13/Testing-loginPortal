pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 sh 'mvn -f Registration_Backend/pom.xml clean install'
               
            }
        }
        
        stage ('Deploy Stage') {
            steps {
                 sh 'java -jar Registration_Backend/target/Registration_Backend.jar'
               
            }
        }
        
    }
}
