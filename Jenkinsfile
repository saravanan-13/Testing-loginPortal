pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
                 bat 'mvn -f Registration_Backend/pom.xml clean install'
                 bat 'mvn -f Update_Profile_Backend/pom.xml clean install'
                 bat 'mvn -f User_Confirmation_Backend/pom.xml clean install'
                 bat 'mvn -f Login_Service_Backend/pom.xml clean install'
                 bat 'mvn -f Forget_Password_Backend/pom.xml clean install'
                 bat 'cd Registration_Frontend && npm install && npm run build'
            }
        }
        stage ('Testing Stage') {
            steps {
               bat 'mvn -f Registration_Backend/pom.xml test'
               bat 'mvn -f Update_Profile_Backend/pom.xml test'
               bat 'mvn -f User_Confirmation_Backend/pom.xml test'
               bat 'mvn -f Login_Service_Backend/pom.xml test'
               bat 'mvn -f Forget_Password_Backend/pom.xml test'
            }
        }
    }
}
