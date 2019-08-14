pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 bat 'mvn -f Registration_Backend/pom.xml clean install'
            }
        }
        stage ('Testing Stage') {
            steps {
               bat 'mvn -f Registration_Backend/pom.xml test'
            }
        }
    }
}
