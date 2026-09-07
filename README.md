# Python Currency Converter Web Application

## Requirements
- Python 3.10+
- VS Code
- Git
- Jenkins (for CI/CD)

## Install
```bat
python -m venv venv
venv\Scripts\activate
python -m pip install --upgrade pip
pip install -r requirements.txt
```

Or:
```bat
pip install flask requests
```

## Run
```bat
python app.py
```
Open: http://localhost:9090

## GitHub
```bat
git init
git add .
git commit -m "Initial Python currency converter"
git branch -M main
git remote add origin YOUR_GITHUB_REPOSITORY_URL
git push -u origin main
```

## Jenkins
Create a Pipeline job → Pipeline script from SCM → Git → enter your GitHub repository URL → Branch `*/main` → Script Path `Jenkinsfile`.

The Jenkinsfile installs dependencies, verifies the Python code, and copies the application to `deploy`.

The exchange rate is retrieved from the Frankfurter API, so internet access is required for live conversions.
