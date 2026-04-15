# Naloga 9 – JPA + MySQL/JDBC

Projekt uporablja JPA (`naloga9PU`) in JTA datasource prek JNDI.

## Persistence unit

Datoteka: `src/main/resources/META-INF/persistence.xml`

- `transaction-type="JTA"`
- `jta-data-source`: `java:comp/env/jdbc/naloga9`
- registrirane entitete: `User`, `Provider`, `ChargingStation`
- MySQL/Hibernate nastavitve:
  - `hibernate.dialect=org.hibernate.dialect.MySQLDialect`
  - `hibernate.hbm2ddl.auto=create-drop`
  - `jakarta.persistence.schema-generation.database.action=drop-and-create`

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

Na aplikacijskem strežniku mora obstajati JTA datasource z referenco:

```text
jdbc/naloga9
```

in mora biti dostopen kot:

```text
java:comp/env/jdbc/naloga9
```

Primer JDBC parametrov za MySQL:

- JDBC URL: `jdbc:mysql://localhost:3306/naloga9?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
- Driver class: `com.mysql.cj.jdbc.Driver`
- uporabnik/geslo: po tvoji lokalni nastavitvi baze

## Build

```bash
mvn clean package
```
