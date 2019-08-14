pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 bat 'mvn -f Login_Service_Backend/pom.xml clean install'
            }
        }
        stage ('Testing Stage') {
            steps {
               bat 'mvn -f Login_Service_Backend/pom.xml test'
            }
        }
    }
}
