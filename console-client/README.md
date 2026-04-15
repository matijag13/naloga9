# Console Client

Standalone console client for remote EJB charging validation.

## Build

```powershell
mvn -DskipTests package
```

## Run

The client expects the application server's JNDI settings as system properties.
Example:

```powershell
mvn -DskipTests exec:java -Djavax.naming.factory.initial=... -Djavax.naming.provider.url=... -- `
  -Dexec.args="STATION_ID USER_EMAIL"
```

The remote bean is looked up using these names:

- `java:global/aiv-naloga9/ChargingStationService!si.um.feri.aiv.remote.ChargingValidationRemote`
- `java:app/aiv-naloga9/ChargingStationService!si.um.feri.aiv.remote.ChargingValidationRemote`
- `java:module/ChargingStationService!si.um.feri.aiv.remote.ChargingValidationRemote`

