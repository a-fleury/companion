# Self-Hosted GitHub Actions Runner

This module provides a containerized GitHub Actions runner to deploy on machines within Polytech's network, enabling CI/CD workflows to access the Dokku server which is only accessible from within the university network.

## 📋 Prerequisites

- Docker and Docker Compose installed
- A GitHub Personal Access Token (PAT) with `repo` scope
- Network access to the Dokku server (Polytech network)

## 🚀 Quick Start

### 1. Set up your GitHub token

Create a `.env` file in this directory with your GitHub runner registration token:

```bash
RUNNER_TOKEN=your_github_runner_token_here
```

> **Note:** You can generate a runner token from your repository's Settings → Actions → Runners → New self-hosted runner

### 2. Build and start the runner

```bash
make rebuild
```

### 3. Verify the runner is registered

Check the logs to confirm successful registration:

```bash
make logs
```

You should also see the runner appear in your GitHub repository under Settings → Actions → Runners.

## 📚 Available Commands

The Makefile provides several convenient commands:

| Command | Description |
|---------|-------------|
| `make up` | Start the runner (uses cached build) |
| `make rebuild` | Rebuild and start the runner |
| `make rebuild-clean` | Rebuild from scratch without cache |
| `make logs` | View runner logs (follow mode) |
| `make stop` | Stop the runner (keeps volumes & images) |
| `make down` | Stop and remove containers |
| `make clean` | Complete cleanup (containers, volumes, images) |
| `make ps` | Show running containers |

## 🏗️ Architecture

### Components

- **Dockerfile**: Multi-architecture image supporting both x64 and arm64 platforms
- **docker-compose.yml**: Container orchestration with environment configuration
- **start.sh**: Entrypoint script handling runner registration and startup
- **Makefile**: Simplified command interface

### How it works

1. The Dockerfile downloads the appropriate GitHub Actions Runner binary for your architecture
2. The start.sh script registers the runner with your GitHub repository (if not already registered)
3. The runner connects to GitHub Actions and waits for workflow jobs
4. Docker socket is mounted to allow the runner to execute Docker commands (for building/deploying to Dokku)

## ⚙️ Configuration

### Environment Variables

Configure these in `docker-compose.yml` or via `.env` file:

| Variable | Description | Default |
|----------|-------------|---------|
| `REPO_URL` | GitHub repository URL | `https://github.com/a-fleury/companion` |
| `RUNNER_NAME` | Name displayed in GitHub | `dokku-runner` |
| `RUNNER_TOKEN` | Registration token (from GitHub) | *Required* |
| `RUNNER_WORKDIR` | Working directory for jobs | `/runner/_work` |
| `RUNNER_VERSION` | GitHub Actions runner version | `2.329.0` |

### Updating the Runner Version

To use a different runner version, modify the `RUNNER_VERSION` build argument in `docker-compose.yml` or set it in your `.env` file.

## 🔒 Security Considerations

- The runner has access to the Docker socket (`/var/run/docker.sock`), granting it significant system privileges
- Ensure the `RUNNER_TOKEN` is kept secure and never committed to version control
- The runner is configured with `--replace` flag to automatically replace existing runners with the same name
- Use the restart policy `unless-stopped` to ensure the runner automatically restarts after system reboots

## 🐛 Troubleshooting

### Runner not appearing in GitHub

1. Check the logs: `make logs`
2. Verify your `RUNNER_TOKEN` is valid and not expired
3. Ensure the `REPO_URL` matches your repository

### Runner registration fails

Registration tokens expire after 1 hour. Generate a new token from GitHub and update your `.env` file.

### Container fails to start

```bash
# Check container status
make ps

# View detailed logs
make logs

# Try a clean rebuild
make rebuild-clean
```

## 📝 Notes

- The runner will persist its configuration across container restarts (checks for `/runner/.runner` file)
- When the container is removed completely, you'll need to generate a new `RUNNER_TOKEN`
- The runner automatically removes and replaces any existing runner with the same name

