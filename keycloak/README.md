# Keycloak Setup

## Development vs Production Mode

This setup uses a single Dockerfile that supports both development and production modes via the `KEYCLOAK_MODE` environment variable.

### Development Mode (default)
```bash
# Using docker-compose (default)
docker-compose up

# Or explicitly set the mode
KEYCLOAK_MODE=dev docker-compose up
```

### Production Mode
```bash
KEYCLOAK_MODE=prod docker-compose up
```

The `KEYCLOAK_MODE` variable accepts:
- `dev` (default): Runs `kc.sh start-dev` - optimized for development with auto-reload and relaxed security
- `prod`: Runs `kc.sh start` - optimized for production with build-time optimizations and strict security
### Why This Approach?

**✅ Recommended: Single Dockerfile + ENV variable**
- Single source of truth
- Easy to maintain
- Flexible deployment across environments
- Works with Docker Compose, Kubernetes, and other orchestrators

**❌ Not Recommended:**
- Two separate Dockerfiles: Code duplication and maintenance overhead
- Build-time ARG only: Less flexible at runtime

## Realm Configuration

The setup automatically imports the `companion` realm on startup using the configuration file located at:
```
keycloak-data/import/realm-export.json
```

This file contains pre-configured:
- Realm settings and security policies
- Client applications
- Roles and permissions
- User federation settings

The import is controlled by environment variables:
- `KC_IMPORT`: Path to the realm file
- `KC_IMPORT_STRATEGY`: Import strategy (e.g., `OVERWRITE_EXISTING`, `IGNORE_EXISTING`)

## Important Endpoints

Once Keycloak is running (default: `http://localhost:8080`):

### Admin Console
- **URL**: `http://localhost:8080/admin`
- Access with `KEYCLOAK_ADMIN` credentials

### Realm Endpoints (replace `{realm}` with `companion`)
- **OpenID Configuration**: `http://localhost:8080/realms/{realm}/.well-known/openid-configuration`
- **Token Endpoint**: `http://localhost:8080/realms/{realm}/protocol/openid-connect/token`
- **Authorization Endpoint**: `http://localhost:8080/realms/{realm}/protocol/openid-connect/auth`
- **User Info**: `http://localhost:8080/realms/{realm}/protocol/openid-connect/userinfo`
- **Logout**: `http://localhost:8080/realms/{realm}/protocol/openid-connect/logout`
- **Account Console**: `http://localhost:8080/realms/{realm}/account`

## Quick Start

1. Set up your environment variables (copy from `.env.example` if available)
2. Start the services:
   ```bash
   docker-compose up
   ```
3. Access the admin console at `http://localhost:8080/admin`
4. The `companion` realm is automatically loaded and ready to use

