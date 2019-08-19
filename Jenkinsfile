pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 bat 'mvn -f Registration_Backend/pom.xml clean install'
                 bat 'cd Registration_Backend && npm install && npm run build'
            }
        }
    }
}
© 2019 GitHub,
