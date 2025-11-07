#!/bin/bash
set -e

# Register the runner only if it’s not already configured
if [ ! -f "/runner/.runner" ]; then
  echo "➡️  Registering new GitHub Actions runner..."
  cd /runner
  ./config.sh \
    --url "${REPO_URL}" \
    --token "${RUNNER_TOKEN}" \
    --name "${RUNNER_NAME}" \
    --work "${RUNNER_WORKDIR:-_work}" \
    --unattended \
    --replace
  echo "✅  Registration complete."
else
  echo "ℹ️  Existing runner detected – skipping registration."
fi

# Start the runner
exec /runner/run.sh
