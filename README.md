# Java Currency Converter Web Application

## Requirements
- Java 17+
- Maven
- Git
- Jenkins
- VS Code / IntelliJ IDEA

## Run locally
```bat
mvn clean package
java -jar target\currency-converter-1.0.0.jar
```

Open the app in browser:
- http://localhost:8080

## Features
- Convert between multiple currencies
- Simple web UI
- Validation for invalid amount and unsupported currencies
- Unit tests for conversion logic

## GitHub
```bat
git status
git add .
git commit -m "Initial Java currency converter project"
git branch -M main
git remote add origin YOUR_GITHUB_REPOSITORY_URL
git push -u origin main
```

## Jenkins setup
1. Install Jenkins.
2. Install plugins: Git, Maven Integration, Pipeline.
3. Configure JDK and Maven under Manage Jenkins → Global Tool Configuration.
4. Create a new Pipeline job.
5. Choose "Pipeline script from SCM".
6. Set Repository URL to your GitHub repository.
7. Set Branch: `*/main`
8. Set Script Path: `Jenkinsfile`

The Jenkins pipeline will:
- checkout the code
- run `mvn clean test`
- package the Spring Boot app
- create a deploy artifact in the `deploy` folder

## Project structure
- `src/main/java` – Java application code
- `src/main/resources/templates` – Thymeleaf HTML views
- `src/main/resources/static/css` – stylesheet
- `src/test/java` – JUnit tests
- `Jenkinsfile` – CI/CD pipeline
