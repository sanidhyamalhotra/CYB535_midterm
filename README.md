# CYB535 Midterm
code for midterm will be here

# Objective of the midterm
you will set up a CI/CD pipeline for a Java application using:
1. Jenkins for automation
2. SonarQube for static code analysis
3. Docker for containerization
4. Kubernetes for orchestration and deployment

# What are we building?

You will create a Jenkins pipeline that does the following:
1. Checkout Code → Pull the latest Java code from GitHub.
2. Build the Java Application → Compile using Java 17.
3. Run Unit Tests → Test using Java 11.
4. Analyze Code Quality → Use SonarQube and Java 8.
5. Build Docker Image → Package the Java application inside a Docker container.
6. Push to Docker Registry → Store the image in Docker Hub.
7. Deploy to Kubernetes → Deploy the containerized application using Kubernetes manifests.
