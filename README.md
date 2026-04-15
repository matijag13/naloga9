# Naloga 9 – JPA + MySQL/JDBC

Projekt uporablja JPA (`naloga9PU`) in JTA datasource prek JNDI.

## Persistence unit

Datoteka: `src/main/resources/META-INF/persistence.xml`

- `transaction-type="JTA"`
- `jta-data-source`: `jdbc/naloga9`
- registrirane entitete: `User`, `Provider`, `ChargingStation`
- MySQL/Hibernate nastavitve:
  - `hibernate.dialect=org.hibernate.dialect.MySQLDialect`
  - `hibernate.hbm2ddl.auto=update`
  - `hibernate.jdbc.time_zone=UTC`

## MySQL Connector/J

V `pom.xml` je dodana runtime odvisnost:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.4.0</version>
    <scope>runtime</scope>
</dependency>
```

## Datasource (strežnik)

Na aplikacijskem strežniku mora obstajati JTA datasource:

```text
jdbc/naloga9
```

Tipični JNDI lookup v aplikaciji:

```text
jdbc/naloga9
```

Kratka nastavitev za MySQL datasource:

1. Namesti MySQL Connector/J na aplikacijski strežnik (če ga strežnik ne pobere iz WAR artefakta).
2. Ustvari bazo `naloga9` v MySQL.
3. Ustvari JDBC povezavo z gonilnikom `com.mysql.cj.jdbc.Driver`.
4. Ustvari JTA datasource z JNDI imenom `jdbc/naloga9`, ki kaže na to JDBC povezavo.
5. Zaženi/deploy aplikacijo.

Primer JDBC parametrov:

- JDBC URL: `jdbc:mysql://localhost:3306/naloga9?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
- Driver class: `com.mysql.cj.jdbc.Driver`
- uporabnik/geslo: po tvoji lokalni nastavitvi baze

## Build

```bash
mvn clean package
```
