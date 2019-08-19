pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 sh 'sudo mvn -f Registration_Backend/pom.xml clean install'
                 sh 'sudo cd Registration_Frontend && sudo npm install && sudo npm run build'
            }
        }
        
    }
}
