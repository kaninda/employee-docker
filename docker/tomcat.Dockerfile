FROM openjdk:17-slim as builder

#Mettre a jour les paquets et installer maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY ../ /app

RUN mvn clean package

FROM openjdk:17-slim

ENV TOMCAT_VERSION 10.1.19
ENV CATALINA_HOME /opt/tomcat
ENV APP_NAME app
ENV CONTEXT_NAME employee
ENV APP_PATH /root/www

# Installer curl et nettoyer le cache du gestionnaire de paquets dans la même couche pour minimiser la taille de l'image
RUN apt-get update && \
    apt-get install -y curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Étape 3: Installer Tomcat
RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME
RUN curl -O https://dlcdn.apache.org/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz \
    && tar -xvf apache-tomcat-${TOMCAT_VERSION}.tar.gz --strip-components=1 \
    && rm bin/*.bat \
    && rm apache-tomcat-${TOMCAT_VERSION}.tar.gz*

# Étape 4: Créer le dossier pour l'application et copier le fichier WAR
RUN mkdir -p "$APP_PATH"
COPY --from=builder /app/target/*.war "$APP_PATH/${APP_NAME}.war"

# Étape 4.5: Créer le fichier de contexte pour pointer vers le dossier de l'application
RUN mkdir -p "$CATALINA_HOME/conf/Catalina/localhost" \
    && echo "<Context docBase=\"${APP_PATH}/${APP_NAME}.war\" />" > "$CATALINA_HOME/conf/Catalina/localhost/$CONTEXT_NAME.xml"

# Donner les permissions d'exécution aux scripts
run chmod +x $CATALINA_HOME/bin/*.sh

# Étape 5: Exposer le port sur lequel Tomcat écoute par défaut
EXPOSE 8080

# Étape 6: Lancer Tomcat
CMD ["sh", "/opt/tomcat/bin/catalina.sh", "run"]