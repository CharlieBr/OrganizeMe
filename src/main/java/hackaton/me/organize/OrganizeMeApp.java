package hackaton.me.organize;

import hackaton.me.organize.config.OrganizeMeConfig;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class OrganizeMeApp extends Application<OrganizeMeConfig> {

    public static void main(final String[] args) throws Exception {
        new OrganizeMeApp().run(args);
    }

    @Override
    public void run(final OrganizeMeConfig organizeMeConfig, final Environment environment) throws Exception {

    }
}