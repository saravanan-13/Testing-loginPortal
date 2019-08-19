pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 sh 'cd Registration_Backend && mvn clean install'
               
            }
        }
        
    }
}
