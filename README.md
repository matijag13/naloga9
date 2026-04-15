# Naloga 9 – MySQL setup

This project now uses JPA with a container-managed datasource.
The application code is datasource-agnostic; the MySQL-specific setup is done in the application server.

## What to configure on the server

1. Install the MySQL JDBC driver on the application server.
2. Create a JTA datasource with the JNDI name:

```text
jdbc/naloga9
```

3. Point that datasource to your MySQL database.
4. Make sure the datasource is accessible as `java:comp/env/jdbc/naloga9`.

## Persistence unit

The application uses the persistence unit:

```text
naloga9PU
```

It is defined in `src/main/resources/META-INF/persistence.xml` and uses the server-provided JTA datasource.

## Notes

- The project keeps `drop-and-create` schema generation enabled for development/testing.
- For production, change schema generation and manage the schema manually.
- The DAOs already use `EntityManager`, so no further code changes are needed for the MySQL switch.

## Build

```bash
mvn clean package
```

