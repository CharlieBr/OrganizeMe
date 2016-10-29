package hackaton.me.organize;

import hackaton.me.organize.config.OrganizeMeConfig;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class OrganizeMeApp extends Application<OrganizeMeConfig> {

    public static void main(final String[] args) throws Exception {
        new OrganizeMeApp().run(args);
    }

    @Override
    public void initialize(final Bootstrap<OrganizeMeConfig> bootstrap) {
        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(final OrganizeMeConfig config, final Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/app/*");
        config.addResources(environment);
    }
}