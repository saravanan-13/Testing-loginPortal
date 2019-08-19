pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 bat 'mvn -f Registration_Backend/pom.xml clean install'
                 bat 'cd Registration_Backend && npm install && npm run build'
            }
        }
        stage ('Testing Stage') {
            steps {
               bat 'mvn -f Registration_Backend/pom.xml test'
            }
        }
    }
}
© 2019 GitHub,
