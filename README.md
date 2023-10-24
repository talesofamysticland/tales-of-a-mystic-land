# Tales of a Mystic Land

Projeto Java, com JavaFX e integração com JDBC, usando Gradle.

## Como executar

Nos laboratórios de informática do IFSP é necessário configurar o proxy para o funcionamento do maven:

```bash
export JAVA_TOOL_OPTIONS="-Djava.net.useSystemProxies=true"
chmod +x gradlew
```

No mesmo terminal que foi configurado o proxy executar o comando:
```bash
./gradlew run
```