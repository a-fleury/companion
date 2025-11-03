# Companion's Transactions Service

Microservice handling financial transactions, wallets, and Stripe payment integration for the Companion platform.

## 🚀 Quick Start

```bash
# Start the service with Docker
make up

# Rebuild and start (recommended for code changes)
make rebuild

# View logs
make logs

# Stop the service
make stop
```

## 📋 Prerequisites

- Docker & Docker Compose
- Java 21+ (for local development)
- Stripe account (for payment processing)
- Kafka cluster (for event-driven communication)

## 🏗️ Project Structure

```
transactions/
├── src/main/java/com/companion/transactions/
│   ├── controller/          # REST API endpoints
│   │   ├── WalletController.java
│   │   ├── VTransactionController.java (Virtual Transactions)
│   │   ├── RTransactionController.java (Real Transactions)
│   │   ├── ShopOfferController.java
│   │   ├── WithdrawalRateController.java
│   │   └── StripeWebhookController.java
│   ├── service/             # Business logic
│   ├── repository/          # Database access layer
│   ├── model/               # JPA entities
│   ├── dto/                 # Data transfer objects
│   ├── kafka/               # Event consumers
│   │   ├── BookingEventConsumer.java
│   │   └── UserEventConsumer.java
│   ├── config/              # Spring configuration
│   └── exception/           # Custom exceptions
├── src/main/resources/
│   └── application.yml      # Configuration file
├── Dockerfile               # Multi-stage build
├── docker-compose.yml       # Service orchestration
├── init-db.sh              # Database initialization
└── Makefile                # Build automation
```

## 🌐 Exposed Endpoints

### Wallets
- `GET /wallets/logged-user` - Get current user's wallet (requires `X-User-Id` header)
- `GET /wallets/all` - Get all wallets

### Virtual Transactions (In-app currency)
- `GET /v-transactions/` - Get all virtual transactions
- `GET /v-transactions/:id` - Get transaction by ID
- `GET /v-transactions/buyer/:buyerId` - Get transactions by buyer
- `GET /v-transactions/seller/:sellerId` - Get transactions by seller
- `GET /v-transactions/user/:userId` - Get all user transactions

### Real Transactions (Stripe payments)
- `GET /r-transactions/` - Get all real transactions
- `GET /r-transactions/:id` - Get transaction by ID
- `GET /r-transactions/user/:userId` - Get user transactions
- `POST /r-transactions/deposit` - Create payment intent for deposit
- `POST /r-transactions/withdrawal` - Simulate withdrawal

### Shop Offers
- `GET /shop-offers/` - Get all shop offers
- `GET /shop-offers/active` - Get active offers
- `GET /shop-offers/:id` - Get an offer by ID
- `POST /shop-offers/` - Create new offer
- `PATCH /shop-offers/` - Update offer
- `PATCH /shop-offers/change-status/:id` - Toggle offer status
- `DELETE /shop-offers/:id` - Delete offer

### Withdrawal Rates
- `GET /withdrawal-rates/` - Get all rates
- `GET /withdrawal-rates/active` - Get active rates
- `GET /withdrawal-rates/inactive` - Get inactive rates
- `GET /withdrawal-rates/:id` - Get rate by ID
- `POST /withdrawal-rates/` - Create new rate
- `PATCH /withdrawal-rates/` - Update rate
- `PATCH /withdrawal-rates/change-status/:id` - Toggle rate status
- `DELETE /withdrawal-rates/:id` - Delete rate

### Stripe Webhooks
- `POST /api/stripe/webhook` - Handle Stripe payment events

## 📡 Kafka Listeners

The service consumes events from other microservices:

| Topic              | Handler                | Description                         |
|--------------------|------------------------|-------------------------------------|
| `user.created`     | `UserEventConsumer`    | Creates wallet for new user         |
| `user.deleted`     | `UserEventConsumer`    | Deletes user's wallet               |
| `booking.created`  | `BookingEventConsumer` | Creates pending virtual transaction |
| `booking.success`  | `BookingEventConsumer` | Validates and completes transaction |
| `booking.canceled` | `BookingEventConsumer` | Cancels transaction                 |

**Consumer Group:** `transaction-service-group`

## 🔧 Environment Variables

Create a `.env` file based on `.env.example`:

```bash
# Database
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
MAIN_DB=companion_transactions_db
TEST_DB=companion_transactions_db_test
POSTGRES_USER=user
POSTGRES_PASSWORD=pwd

# Application
SERVER_PORT=8085

# Stripe
STRIPE_WEBHOOK_SECRET=whsec_xxxxx
STRIPE_API_KEY=sk_test_xxxxx
```

> **Note:** For production, use environment-specific values and secure secrets management.

## 🐳 Docker Compose Strategy

### Architecture
The service uses a **multi-container setup** with:
- **transactions-service**: Spring Boot application
- **transactions-db**: PostgreSQL database with persistent volume. Includes a development and a test database

### Dockerfile (Multi-stage build)
1. **Builder stage**: Compiles Java code using Gradle wrapper
2. **Runtime stage**: Lightweight JRE image with compiled JAR

### Database Initialization
`init-db.sh` automatically creates both main and test databases on container startup.

### Makefile Commands
| Command              | Description                                     |
|----------------------|-------------------------------------------------|
| `make up`            | Start with cached images                        |
| `make rebuild`       | Rebuild and restart                             |
| `make rebuild-clean` | Clean rebuild (no cache)                        |
| `make logs`          | View live logs                                  |
| `make stop`          | Stop containers                                 |
| `make down`          | Stop and remove containers                      |
| `make clean`         | Remove everything (containers, volumes, images) |
| `make ps`            | Show running containers                         |

## 💳 Stripe Implementation

### Payment Flow
1. Client requests deposit via `POST /r-transactions/deposit`
2. Service creates Stripe PaymentIntent
3. Client completes payment using returned `clientSecret`
4. Stripe sends webhook to `/api/stripe/webhook`
5. Service updates transaction status and credits wallet

### Webhook Events Handled
- `payment_intent.succeeded` - Credits user wallet
- `payment_intent.payment_failed` - Marks transaction as failed
- `payment_intent.canceled` - Cancels transaction

### Security
- Webhook signature verification using `STRIPE_WEBHOOK_SECRET`
- Prevents unauthorized/fraudulent webhook calls

## 📖 OpenAPI Documentation

Access interactive API documentation at:

```
http://localhost:8085/swagger-ui.html
```

> **Tip:** The Swagger UI provides full endpoint documentation with request/response examples.

## 🛠️ Useful Commands

### Local Development
```bash
# Build with Gradle
./gradlew clean build

# Run tests
./gradlew test

# Run locally (requires .env)
./gradlew bootRun
```

### Docker Operations
```bash
# Build image
docker compose build

# Start in foreground (see logs)
docker compose up

# View database logs
docker compose logs -f transactions-db

# Access database shell
docker compose exec transactions-db psql -U user -d companion_transactions_db

# Restart specific service
docker compose restart transactions-service
```

### Database Management
```bash
# Connect to PostgreSQL
docker compose exec transactions-db psql -U user -d companion_transactions_db

# Check tables
\dt

# Query wallets
SELECT * FROM wallet;
```

## 🔍 Troubleshooting

### Service won't start
- Check `.env` file exists and contains valid values
- Ensure ports 8085 and 5432 are not in use
- Verify Docker daemon is running

### Database connection errors
- Wait for `transactions-db` to be fully initialized
- Check `POSTGRES_HOST` points to `transactions-db` in Docker
- For local runs, use `localhost`

### Kafka connection issues
- Ensure Kafka is running and accessible
- Update `spring.kafka.bootstrap-servers` in `application.yml`
- Listeners are disabled by default (`auto-startup: false`)

### Stripe webhooks are not working
- Verify `STRIPE_WEBHOOK_SECRET` matches your Stripe dashboard
- Use Stripe CLI for local testing: `stripe listen --forward-to localhost:8085/api/stripe/webhook`

## 📝 Key Features

- **Dual Transaction System**: Virtual (in-app) and Real (Stripe) transactions
- **Event-Driven Architecture**: Kafka integration for loose coupling
- **Wallet Management**: Automatic wallet creation/deletion
- **Payment Processing**: Full Stripe integration with webhook handling
- **Configurable Rates**: Admin-managed withdrawal rates and shop offers
- **Auto-scaling Ready**: Stateless design with an external database

## 🔗 Dependencies

- Spring Boot 3.5.6
- Spring Data JPA
- PostgreSQL
- Kafka
- Stripe Java SDK
- SpringDoc OpenAPI (Swagger)

---

**Version:** 0.0.1-SNAPSHOT  
**Port:** 8085  
**Team:** Companion

