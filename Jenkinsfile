pipeline {

    agent any
    
    stages {
        stage ('Compile Stage') {
            steps {
               
                 bat 'cd Registration_Frontend && npm install && npm run build'
            }
        }
    }
}
