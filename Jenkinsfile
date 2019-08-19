pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 bat 'mvn -f Registration_Backend/pom.xml clean install'
                 bat 'cd Registration_Frontend && npm install && npm run build'
            }
        }
        stage ('Deploy Stage') {
            steps {
                 bat 'start java -jar Registration_Backend/target/Registration_Backend.jar'
                 bat 'start cd Registration_Frontend/build && python -m http.server 8884'
            }
        }
        
    }
}
