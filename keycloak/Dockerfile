FROM quay.io/keycloak/keycloak:26.4.0

USER root

# Optional: copy themes, providers and imports if they exist
COPY ./keycloak-data /opt/keycloak/data

# Adjust permissions to let keycloak read/write its files
RUN chown -R keycloak:keycloak /opt/keycloak

USER keycloak

# Environment variable to control the mode (dev or prod)
# dev -> runs 'start-dev'
# prod -> runs 'start'
ENV KEYCLOAK_MODE=dev

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "-c", "if [ \"$KEYCLOAK_MODE\" = \"prod\" ]; then /opt/keycloak/bin/kc.sh start; else /opt/keycloak/bin/kc.sh start-dev; fi"]
