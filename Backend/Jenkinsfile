node {
 
  stage('Checkout') {
   git url: 'https://github.com/saravanan2910/spring-boot-build-demo'
  }
  stage('Build') {
      bat 'mvn clean install'
      bat 'docker build -f Dockerfile -t testing-springboot-docker .'
      bat 'docker run -t --name testing-springboot-docker --link docker-mysql:mysql -p 8888:8888 testing-springboot-docker'
  }
}