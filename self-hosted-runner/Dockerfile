# ===========================
# Custom GitHub Actions Runner
# ===========================

FROM ubuntu:22.04

# Install dependencies for GitHub runner + .NET runtime
RUN apt-get update && \
    apt-get install -y \
      curl tar jq git sudo \
      libicu-dev libkrb5-3 zlib1g libssl3 libcurl4 libgcc-s1 libstdc++6 && \
    rm -rf /var/lib/apt/lists/*


# Define the version (same as GitHub’s latest)
ARG RUNNER_VERSION=2.329.0
ENV RUNNER_VERSION=${RUNNER_VERSION}

# Create the runner directory and download the correct package (x64 or arm64)
WORKDIR /runner
RUN echo "Downloading GitHub Actions Runner v${RUNNER_VERSION}..." && \
    ARCH=$(uname -m) && \
    if [ "$ARCH" = "aarch64" ]; then \
      ARCH_NAME="arm64"; \
    else \
      ARCH_NAME="x64"; \
    fi && \
    echo "Detected architecture: $ARCH_NAME" && \
    curl -fL -o actions-runner-linux-${ARCH_NAME}-${RUNNER_VERSION}.tar.gz \
      "https://github.com/actions/runner/releases/download/v${RUNNER_VERSION}/actions-runner-linux-${ARCH_NAME}-${RUNNER_VERSION}.tar.gz" && \
    echo "✅ Download complete" && \
    tar xzf ./actions-runner-linux-${ARCH_NAME}-${RUNNER_VERSION}.tar.gz && \
    rm ./actions-runner-linux-${ARCH_NAME}-${RUNNER_VERSION}.tar.gz

# Copy startup script
WORKDIR /
COPY start.sh /start.sh
RUN chmod +x /start.sh

ENTRYPOINT ["/start.sh"]
