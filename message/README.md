# Message microservice

Lightweight microservice that handles messaging for the Companion application.

## Overview & basic architecture
- REST API built with Spring Boot: entry point is [`com.companion.message.MessageApplication`](message/src/main/java/com/companion/message/MessageApplication.java).
- Controllers:
  - [`com.companion.message.controller.MessageController`](message/src/main/java/com/companion/message/controller/MessageController.java) — send a message.
  - [`com.companion.message.controller.ConversationController`](message/src/main/java/com/companion/message/controller/ConversationController.java) — list conversations & fetch messages.
  - [`com.companion.message.controller.UnreadMessageController`](message/src/main/java/com/companion/message/controller/UnreadMessageController.java) — mark messages as read.
- Service layer contracts and implementations:
  - Interfaces: [`com.companion.message.service.contract.MessageService`](message/src/main/java/com/companion/message/service/contract/MessageService.java), [`com.companion.message.service.contract.ConversationService`](message/src/main/java/com/companion/message/service/contract/ConversationService.java), [`com.companion.message.service.contract.UserService`](message/src/main/java/com/companion/message/service/contract/UserService.java)
  - Implementations: [`com.companion.message.service.impl.concrete.ConcreteMessageService`](message/src/main/java/com/companion/message/service/impl/concrete/ConcreteMessageService.java), [`com.companion.message.service.impl.concrete.ConcreteConversationService`](message/src/main/java/com/companion/message/service/impl/concrete/ConcreteConversationService.java), [`com.companion.message.service.impl.fake.UserServiceFake`](message/src/main/java/com/companion/message/service/impl/fake/UserServiceFake.java)
- Persistence:
  - Entities: [`com.companion.message.entity.MessageEntity`](message/src/main/java/com/companion/message/entity/MessageEntity.java), [`com.companion.message.entity.ConversationEntity`](message/src/main/java/com/companion/message/entity/ConversationEntity.java)
  - Repositories: [`com.companion.message.repository.MessageRepository`](message/src/main/java/com/companion/message/repository/MessageRepository.java), [`com.companion.message.repository.ConversationRepository`](message/src/main/java/com/companion/message/repository/ConversationRepository.java)
- DTO mapping via MapStruct:
  - [`com.companion.message.mapper.MessageMapper`](message/src/main/java/com/companion/message/mapper/MessageMapper.java)
  - [`com.companion.message.mapper.ConversationMapper`](message/src/main/java/com/companion/message/mapper/ConversationMapper.java)

## Endpoints & OpenAPI
- API paths (examples):
  - POST /message -> handled by [`MessageController`](message/src/main/java/com/companion/message/controller/MessageController.java)
  - GET /{conversationId} -> conversation messages in [`ConversationController`](message/src/main/java/com/companion/message/controller/ConversationController.java)
  - PATCH /{conversationId}/unread-messages -> mark read in [`UnreadMessageController`](message/src/main/java/com/companion/message/controller/UnreadMessageController.java)
- OpenAPI / Swagger UI paths configured in application properties:
  - Swagger UI: configured path is `/swagger-mes-couilles` (see [application.yml](message/src/main/resources/application.yml))
  - API docs: `/api-docs` (see [application.yml](message/src/main/resources/application.yml))
- Example API requests are available in `api-doc-bruno/`:
  - [PostNewMessage.bru](message/api-doc-bruno/PostNewMessage.bru)
  - [Get conversation messages.bru](message/api-doc-bruno/Get conversation messages.bru)
  - [GetCoversation.bru](message/api-doc-bruno/GetCoversation.bru)

## How to run
Prerequisites: Java toolchain (configured for Java 21 in the Gradle build), Docker (optional for local DB).

1. Start the database via Docker Compose (recommended):
   - File: [docker-compose.yaml](docker-compose.yaml)
   - Command:
     ```sh
     docker compose up -d
     ```
   - This spins up a PostgreSQL container listening on host port 5533 (DB name: `messagerie`, user/password: `user`/`password`).

2. Run the service with Gradle from the `message` folder:
   - From workspace root:
     ```sh
     cd message
     ./gradlew bootRun
     ```
   - Or on Windows:
     ```bat
     cd message
     gradlew.bat bootRun
     ```

3. Open the Swagger UI:
   - http://localhost:8080/swagger-mes-couilles
   - Raw OpenAPI JSON: http://localhost:8080/api-docs

Alternative: build a jar and run:
```sh
cd message
./gradlew bootJar
java -jar build/libs/message-0.0.1-SNAPSHOT.jar
```

Build configuration: [message/build.gradle](message/build.gradle)

## Database structure
The initial schema is defined in the Flyway migration:
- [V1_Initial_DB.sql](message/src/main/resources/db/migration/V1_Initial_DB.sql)

Main tables (summary):
- conversation
  - id (SERIAL PRIMARY KEY)
  - user1_id VARCHAR(50)
  - user2_id VARCHAR(50)
- message
  - id (SERIAL PRIMARY KEY)
  - conversation_id INT (FK to conversation)
  - message_type VARCHAR(20)
  - sender_id VARCHAR(50)
  - content TEXT
  - readed BOOLEAN DEFAULT FALSE
  - sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  - modified_at TIMESTAMP NULL

Entity mappings are in:
- [`ConversationEntity`](message/src/main/java/com/companion/message/entity/ConversationEntity.java)
- [`MessageEntity`](message/src/main/java/com/companion/message/entity/MessageEntity.java)

## Particularities & notes
- MapStruct is used for DTO mapping — MapStruct processor configured in the Gradle build. See [`MessageMapper`](message/src/main/java/com/companion/message/mapper/MessageMapper.java) and [`ConversationMapper`](message/src/main/java/com/companion/message/mapper/ConversationMapper.java).
- Lombok is used (compile-time) for getters/setters. See `build.gradle` and `annotationProcessor` configuration: [message/build.gradle](message/build.gradle).
- The default UserService is a fake implementation [`UserServiceFake`](message/src/main/java/com/companion/message/service/impl/fake/UserServiceFake.java) returning a constant connected user id; replace with a real user integration when wiring authentication.
- The application expects a PostgreSQL DB (configured in [application.yml](message/src/main/resources/application.yml)).
- Java version configured in Gradle toolchain: 21. Adjust if needed: [message/build.gradle](message/build.gradle).

## Useful files
- Application configuration: [message/src/main/resources/application.yml](message/src/main/resources/application.yml)
- Flyway migration: [message/src/main/resources/db/migration/V1_Initial_DB.sql](message/src/main/resources/db/migration/V1_Initial_DB.sql)
- Docker compose (database): [docker-compose.yaml](docker-compose.yaml)
- Controllers: see files in [message/src/main/java/com/companion/message/controller/](message/src/main/java/com/companion/message/controller/)
- Services, repositories, entities, mappers: see corresponding packages under [message/src/main/java/com/companion/message/](message/src/main/java/com/companion/message/)

If you want, I can:
- add example curl requests to the README,
- add health/readiness endpoints,
- or replace the fake UserService with a token-based implementation.