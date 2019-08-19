pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 sh 'sudo mvn -f Registration_Backend/pom.xml clean install'
                 sh 'sudo cd Registration_Frontend && sudo npm install && sudo npm run build'
            }
        }
        stage ('Deploy Stage') {
            steps {
                 sh 'sudo nohup java -jar Registration_Backend/target/Registration_Backend.jar &'
                 sh 'sudo cd Registration_Frontend/build && sudo nohup python -m http.server 8884 &'
            }
        }
        
    }
}
