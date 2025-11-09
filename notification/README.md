# Notification Service

Here is the part that permits the application to send some notification through different chanels for the application

## What type of notification are permitted by the application

- Emails (Work in progress) --> EMAIL
- Push when online and stored if not, sent when back online (in progress) --> PUSH_ONLINE
- Push or Email (Send an email if offline/in progress) --> PUSH_OR_EMAIL
- In application notification ? ( In the mailbox of the application ) --> APPLICATION

## How does it works ? 

The only entrypoint of the application is an event listener, currently using kafka. When you publish a message in the notification topic, if it fits the structure that is expected, the application consume it and process it.  

## Architecture 

- Layer of kafka listener 
- Services to preprocess and dispatch notification
- Layer of listener for handling notification
- Layer of senders to process the notification
