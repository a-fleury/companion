# Notification Service

## Overview

This microservice is the centralized notification emitter for the application. It accepts notification events (via Kafka) and dispatches them to one or more channels. It is designed for extensibility, reliability, and clear separation of concerns so new channels, templates or routing rules can be added without changing business producers.

## Supported channels

- EMAIL
- APPLICATION — in-app mailbox

## Responsibilities

- Validate and normalize incoming notification events
- Select template and render content (subject, body, metadata)
- Resolve recipients and routing rules
- Enqueue and dispatch to channel-specific senders/adapters

## Typical flow

1. Event received on Kafka notification topic  
2. Validator checks schema and required fields  
3. Service resolves notification type, template and recipients  
4. Sender layer (adapter per channel) transmits the message  

## 🚀 Quick Start

## Prerequisites

- JDK 17+
- Docker & Docker Compose
- Gradle wrapper (provided by the project)

## Quick commands

- Run locally (Gradle):
    - ./gradlew bootRun

- Build the artifact and run via Docker Compose:
    - ./gradlew bootJar
    - docker compose build
    - docker compose up -d


## 📡 Kafka Listeners

The service consumes events from other microservices:

| Topic        | Handler              | Description                                   |
|--------------|----------------------|-----------------------------------------------|
| notification | NotificationListener | All notifications that are waiting to be sent |

## What type of notification are permitted by the application

- Emails  --> EMAIL
- In application notification --> APPLICATION

## How does it works ? 

The only entrypoint of the application is an event listener, currently using kafka. When you publish a message in the notification topic, if it fits the structure that is expected, the application consume it and process it.  

## Architecture 

- Layer of kafka listener 
- Services to preprocess and dispatch notification
- Layer of listener for handling notification
- Layer of senders to process the notification
