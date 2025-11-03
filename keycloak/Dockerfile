FROM quay.io/keycloak/keycloak:26.4.0

USER root

# Optional: copy themes and providers if they exist
COPY ./keycloak-data/themes /opt/keycloak/themes
COPY ./keycloak-data/providers /opt/keycloak/providers

# Adjust permissions to let keycloak read/write its files
RUN chown -R keycloak:keycloak /opt/keycloak

USER keycloak

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]
CMD ["start-dev"]
