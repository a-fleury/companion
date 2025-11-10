FROM quay.io/keycloak/keycloak:26.4.0

USER root

# Optional: copy themes and providers if they exist
COPY ./keycloak-data/themes /opt/keycloak/themes
COPY ./keycloak-data/providers /opt/keycloak/providers
COPY ./keycloak-data/import /opt/keycloak/data/import

# Adjust permissions to let keycloak read/write its files
RUN chown -R keycloak:keycloak /opt/keycloak

USER keycloak

# Environment variable to control the mode (dev or prod)
# dev -> runs 'start-dev'
# prod -> runs 'start'
ENV KEYCLOAK_MODE=dev

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "-c", "if [ \"$KEYCLOAK_MODE\" = \"prod\" ]; then /opt/keycloak/bin/kc.sh start; else /opt/keycloak/bin/kc.sh start-dev; fi"]
