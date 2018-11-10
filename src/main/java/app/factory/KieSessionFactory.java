package app.factory;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class KieSessionFactory {

    private KieSessionFactory() {
    }

    public static KieSession getKieSession(String drlPath) throws FileNotFoundException {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();

        FileInputStream fis = new FileInputStream(drlPath);
        kfs.write( "src/main/resources/invoice.drl",
                kieServices.getResources().newInputStreamResource( fis ) );

        KieBuilder kieBuilder = kieServices.newKieBuilder( kfs ).buildAll();
        Results results = kieBuilder.getResults();

        if( results.hasMessages( Message.Level.ERROR ) ){
            System.out.println( results.getMessages() );
            throw new IllegalStateException( "### errors ###" );
        }

        KieContainer kieContainer =
                kieServices.newKieContainer( kieServices.getRepository().getDefaultReleaseId() );
        KieSession kieSession = kieContainer.newKieSession();

        return kieSession;
    }
}
