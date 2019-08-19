pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 sh 'cd Registration_Backend && sudo mvn clean install'
               
            }
        }
        
    }
}
