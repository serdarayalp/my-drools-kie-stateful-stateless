import de.mydomain.model.Counter;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Main {

    public static void main(String[] args) {
        try {

            // load up the knowledge base
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();

            // get stateful session
            KieSession kSession = kieContainer.newKieSession("ksession-rule");

            Counter counter10 = new Counter(10, "cnt10");
            Counter counter20 = new Counter(20, "cnt20");
            Counter counter30 = new Counter(30, "cnt30");

            System.out.println("fire rules after inserting counter10");
            kSession.insert(counter10);
            // fire rules with counter10
            kSession.fireAllRules();

            System.out.println("fire rules after inserting counter20");
            kSession.insert(counter20);
            // fire rules with already existing counter1 and newly inserted counter20
            kSession.fireAllRules();

            System.out.println("fire rules after inserting counter30");
            kSession.insert(counter30);
            // fire rules with already existing counter1 and newly inserted counter30
            kSession.fireAllRules();

            //Dispose the session at the end.
            kSession.dispose();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

}