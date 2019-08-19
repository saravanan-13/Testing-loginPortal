pipeline {

    agent any
    
    stages {
        stage ('Deploy Stage') {
            steps {
                 bat 'start java -jar Registration_Backend/target/Registration_Backend.jar'
                 bat 'cd Registration_Frontend && cd build && python -m http.server 8884'
            }
        }
        
    }
}
