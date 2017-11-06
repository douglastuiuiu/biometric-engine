FROM java:8

# Port configuration
EXPOSE 8099:8099

# setting timezone
RUN apt-get install -y wget
RUN echo "America/Sao_Paulo" > '/etc/timezone'
RUN dpkg-reconfigure --frontend noninteractive tzdata

# Prepare workspace
RUN mkdir -p /usr/share/biometric-engine

# Copy package release
COPY ./biometric-engine.jar /usr/share/biometric-engine

# Start service
RUN sh -c 'touch /usr/share/biometric-engine/biometric-engine.jar'
CMD ["java","-jar","/usr/share/biometric-engine/biometric-engine.jar"]