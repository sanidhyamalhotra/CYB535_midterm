pipeline {
    agent any
    
    environment {
        GIT_REPO = 'https://github.com/sanidhyamalhotra/CYB535_midterm.git'
        BRANCH = 'todoListCode'
        SONARQUBE_URL = 'http://localhost:9191/'
        SONAR_TOKEN = credentials('sonar_token')
        DOCKERHUB_REPO = "sanidhyamalhotra/todolist"
    }
    
    stages {
        stage('Clone Repository') {
            steps {
                script {
                    cleanWs()
                    git branch: "${BRANCH}", url: "${GIT_REPO}"
                }
            }
        }

        stage('Build Code with Java 17') {
            agent {
                docker {
                    reuseNode true
                    image 'maven:3.8.5-openjdk-17'
                    args '-v $HOME/.m2:/root/.m2 -v $WORKSPACE:/app'
                }
            }
            steps {
                script {
                    sh "mvn clean package"
                }
            }
        }

        stage('Run Tests with Java 11') {
            agent {
                docker {
                    reuseNode true
                    image 'maven:3.8.5-openjdk-11'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                script {
                    sh """
                        mvn clean package
                        mvn test
                    """
                }
            }
        }
        
        stage('Code Quality') {
            agent {
                docker {
                    reuseNode true
                    image 'maven:3.8.6-jdk-8'
                    args '-v $HOME/.m2:/root/.m2 --network host'
                }
            }
            steps {
                sh """
                    mvn sonar:sonar \
                        -Dsonar.host.url=${SONARQUBE_URL} \
                        -Dsonar.login=${SONAR_TOKEN} \
                        -Dsonar.projectKey=todolist-app \
                        -Dsonar.java.binaries=target/classes \
                        -Dsonar.java.source=8
                """
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    def jarFile = findFiles(glob: 'target/*.jar').find { it.name.endsWith('-jar-with-dependencies.jar') }

                    // Create Dockerfile
                    writeFile file: 'Dockerfile', text: """
                        FROM openjdk:17-jdk-slim
                        WORKDIR /app
                        COPY ${jarFile} app.jar
                        EXPOSE 8080
                        ENTRYPOINT ["java", "-jar", "app.jar"]
                    """
                    
                     // Tag the image with the build number
                    def dockerImageTag = "${env.DOCKERHUB_REPO}:${env.BUILD_ID}"
                    def dockerImageTagLatest = "${env.DOCKERHUB_REPO}:latest"
                    
                    // Build the Docker image with the unique build number
                    sh "docker build -t ${dockerImageTag} -t ${dockerImageTagLatest} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',  // Must match what you use below
                        passwordVariable: 'DOCKER_PWD'    // Must match what you use below
                    )]) {
                        sh """
                            echo ${DOCKER_PWD} | docker login -u ${DOCKER_USER} --password-stdin
                            docker push ${env.DOCKERHUB_REPO}:latest
                            docker logout
                        """
                        
                        // Clean up old Docker images
                        sh '''
                            # Keep last 2 versions (latest + current BUILD_ID)
                            docker images ${DOCKERHUB_REPO} \
                            --format "{{.Tag}} {{.CreatedAt}}" \
                            | sort -rk 2 \
                            | awk 'NR>2 {print $1}' \
                            | xargs -r -I {} docker rmi ${DOCKERHUB_REPO}:{} --force
                        '''
                    }
                }
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh 'kubectl apply -f deployment.yaml'
                }
            }
        }
    }
    
    post {
        success {
            echo "build completed successfully."
        }
        failure {
            echo "failed."
        }
    }
}
