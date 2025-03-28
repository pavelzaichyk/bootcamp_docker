# Bootcamp - Docker




# Step 2: Running a Docker Container

This guide covers the basic Docker commands for finding, downloading, and running a container.

## 1. Search and Pull a Docker Image

Search for the `hello-world` image:
```bash
docker search hello-world
```

Download the `hello-world` image:
```bash
docker pull hello-world
```

List downloaded images:
```bash
docker images
```

## 2. Run the Docker Container

Run the `hello-world` container:
```bash
docker run hello-world
```

List all containers (including stopped ones):
```bash
docker ps -a
```

## 3. Run a Web Server Container (EXTRA 2.1)

Start an Nginx container with an HTML page:
```bash
docker run -d --name web-container -p 8080:80 -e USERNAME="${USERNAME}" nginx
```

Check if the web server is running:
```bash
curl http://localhost:8080
```

Access the container's shell:
```bash
docker exec -it web-container /bin/sh
```

Modify the default HTML file:
```sh
cd /usr/share/nginx/html
echo "<h1>${USERNAME:-DefaultUser} 2024</h1>" > index.html
```

Exit the container:
```sh
exit
```

## 4. Stop and Remove the Container

Stop the running container:
```bash
docker stop web-container
```

Remove the container:
```bash
docker rm web-container
```

Remove the `hello-world` image:
```bash
docker rmi hello-world


# Step 3



Build Docker image
```bash
docker build -t spring-boot-app .
```

Run container
```bash
docker run -p 8080:8080 -e DEVOPS=myusername spring-boot-app
```


Check endpoints
```bash
curl localhost:8080
curl localhost:8080/env
```

# Step 4: Pushing Docker Image to Docker Hub

This section provides commands for tagging, pushing, and automating Docker image deployment.

## 1. Log in to Docker Hub

Before pushing an image, log in to Docker Hub:
```bash
docker login
```
Enter your Docker Hub credentials when prompted.

## 2. Tag the Docker Image

Docker images must be tagged with the format `<dockerhub-username>/<image-name>:<tag>` before pushing:
```bash
docker tag spring-boot-app pavelzaichyk/spring-boot-app:latest
```

## 3. Push the Docker Image to Docker Hub

Push the tagged image to Docker Hub:
```bash
docker push pavelzaichyk/spring-boot-app:latest
```
Once completed, your image will be available at:
```
https://hub.docker.com/r/pavelzaichyk/spring-boot-app
```

## 4. Automate Deployment with GitHub Actions

### 4.1. Set Up GitHub Secrets
1. Go to your GitHub repository.
2. Navigate to `Settings` → `Secrets and variables` → `Actions`.
3. Click `New repository secret` and add:
   - **DOCKER_USERNAME** = your Docker Hub username
   - **DOCKER_PASSWORD** = your Docker Hub password

### 4.2. Create a GitHub Actions Workflow

Add a new workflow file `.github/workflows/docker-publish.yml` with the following content:
```yaml
name: Build and Push Docker Image

on:
  push:
    branches:
      - main  # Runs when code is pushed to main

jobs:
  build-and-push:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Log in to Docker Hub
        run: echo "${{ secrets.DOCKER_PASSWORD }}" | docker login -u "${{ secrets.DOCKER_USERNAME }}" --password-stdin

      - name: Build Docker image
        run: docker build -t ${{ secrets.DOCKER_USERNAME }}/spring-boot-app:latest .

      - name: Push Docker image
        run: docker push ${{ secrets.DOCKER_USERNAME }}/spring-boot-app:latest
```
