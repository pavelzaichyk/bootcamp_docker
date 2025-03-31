#!/bin/bash

# Update system packages
sudo dnf update -y

# Install Git
sudo dnf install -y git

# Install Docker
sudo dnf install -y docker

# Install Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Start and enable Docker service
sudo systemctl start docker
sudo systemctl enable docker

# Fix Docker socket permissions
sudo chmod 666 /var/run/docker.sock

# Add current user to the Docker group (avoids using sudo for Docker commands)
sudo usermod -aG docker $USER

# Display installed versions
git --version
docker --version
docker compose version

echo "Docker, Docker Compose and Git installation completed successfully! Please restart your terminal or run 'newgrp docker' to apply the changes."
