package si.um.feri.aiv.client;

import si.um.feri.aiv.remote.ChargingValidationRemote;
import si.um.feri.aiv.remote.ChargingValidationResult;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class ChargingValidationClient {

    private static final String[] LOOKUP_NAMES = {
            "java:global/aiv-naloga9/ChargingStationService!si.um.feri.aiv.remote.ChargingValidationRemote",
            "java:app/aiv-naloga9/ChargingStationService!si.um.feri.aiv.remote.ChargingValidationRemote",
            "java:module/ChargingStationService!si.um.feri.aiv.remote.ChargingValidationRemote"
    };

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Uporaba: ChargingValidationClient <stationId> <userEmail>");
            return;
        }

        String stationId = args[0];
        String userEmail = args[1];

        ChargingValidationRemote bean = lookupBean();
        ChargingValidationResult result = bean.validateCharging(stationId, userEmail);

        System.out.println("Rezultat preverjanja:");
        System.out.println(result);
        System.out.println(result.isAllowed()
                ? "Polnjenje je možno."
                : "Polnjenje ni možno: " + result.getMessage());
    }

    private static ChargingValidationRemote lookupBean() throws NamingException {
        Context context = new InitialContext(createEnvironment());
        NamingException lastError = null;

        for (String lookupName : LOOKUP_NAMES) {
            try {
                return (ChargingValidationRemote) context.lookup(lookupName);
            } catch (NamingException e) {
                lastError = e;
            }
        }

        throw new NamingException("EJB ni bil najden. Preveri JNDI nastavitve in ime zrna. Zadnja napaka: "
                + (lastError != null ? lastError.getMessage() : "neznana"));
    }

    private static Hashtable<String, String> createEnvironment() {
        Hashtable<String, String> env = new Hashtable<>();
        putIfPresent(env, Context.INITIAL_CONTEXT_FACTORY, System.getProperty(Context.INITIAL_CONTEXT_FACTORY));
        putIfPresent(env, Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL));
        putIfPresent(env, Context.SECURITY_PRINCIPAL, System.getProperty(Context.SECURITY_PRINCIPAL));
        putIfPresent(env, Context.SECURITY_CREDENTIALS, System.getProperty(Context.SECURITY_CREDENTIALS));
        return env;
    }

    private static void putIfPresent(Hashtable<String, String> env, String key, String value) {
        if (value != null && !value.isBlank()) {
            env.put(key, value);
        }
    }
}

