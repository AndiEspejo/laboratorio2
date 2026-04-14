# Laboratorio 2 - Spring Boot CI/CD

[![CI/CD Pipeline](https://github.com/<usuario>/<repo>/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/<usuario>/<repo>/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=<sonar_project_key>&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=<sonar_project_key>)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=<sonar_project_key>&metric=coverage)](https://sonarcloud.io/summary/new_code?id=<sonar_project_key>)
[![Known Vulnerabilities](https://snyk.io/test/github/<usuario>/<repo>/badge.svg)](https://snyk.io/test/github/<usuario>/<repo>)

Implementation of a Simple App with the next operations:

- Get random nations
- Get random currencies
- Get random aircraft information
- Get application version
- Health check

Including integration with GitHub Actions, SonarCloud, Docker Hub, Snyk and an optional Render deployment hook.

## Endpoints

- `GET /` → health check
- `GET /version` → application version
- `GET /nations` → 10 random nations
- `GET /currencies` → 20 random currencies
- `GET /aviation` → 20 random aviation records

## Folder structure

- `src/main` → application source code
- `src/test` → unit tests
- `.github/workflows` → CI/CD pipeline

## Local execution

This lab is configured for Java 11. If your machine has multiple JDKs installed, activate the local Java 11 helper first:

```bash
source ./use-java11.sh
```

Run the application in development mode:

```bash
./mvnw spring-boot:run
```

Generate the executable JAR using Java 11:

```bash
./package-java11.sh
```

Run the packaged JAR with Java 11:

```bash
./run-java11-jar.sh
```

Run tests:

```bash
./mvnw clean test
```

Generate package without tests:

```bash
./mvnw -B package -DskipTests --file pom.xml
```

## Required GitHub secrets

For the full laboratory flow, configure these repository secrets:

- `SONAR_TOKEN`
- `SNYK_TOKEN`
- `DOCKER_USERNAME`
- `DOCKER_PASSWORD`
- `DOCKER_IMAGE` → example: `tuusuario/faker`
- `RENDER_DEPLOY_HOOK_URL` (optional, only if you choose Render as cloud provider)

## Pipeline stages

1. **tests** → runs unit tests with Maven
2. **sonar** → publishes analysis to SonarCloud using organization `andiespejo` and project key `AndiEspejo_laboratorio2`
3. **build** → generates the JAR artifact
4. **security** → runs Snyk when token exists
5. **docker** → publishes the Docker image when credentials exist
6. **deploy-render** → triggers cloud deployment on Render when hook exists

## Docker

Build locally:

```bash
docker build -t faker:latest .
```

Run locally:

```bash
docker run -p 8080:8080 faker:latest
```

## Notes about the lab

- The workflow listens to `main` and `mi-rama` to support the branch-based CI/CD exercise from the PDF.
- Coveralls appears in the laboratory as an extra task, so it is intentionally left as an optional next step.
- The project is prepared to match the laboratory requirements without hardcoding personal credentials or cloud-specific secrets.
