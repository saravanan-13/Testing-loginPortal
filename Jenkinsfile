pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 sh 'mvn -f Registration_Backend/pom.xml clean install'
                 sh 'cd Registration_Frontend && npm install && npm run build'
            }
        }
        stage ('Deploy Stage') {
            steps {
                 sh 'nohup java -jar Registration_Backend/target/Registration_Backend.jar &'
                 sh 'cd Registration_Frontend/build && nohup python -m http.server 8884 &'
            }
        }
        
    }
}
