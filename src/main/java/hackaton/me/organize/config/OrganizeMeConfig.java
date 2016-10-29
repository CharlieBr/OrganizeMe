package hackaton.me.organize.config;

import hackaton.me.organize.clients.PostgreSQLClient;
import hackaton.me.organize.clients.PostgreSQLQueries;
import hackaton.me.organize.resources.OrganizeMeResource;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class OrganizeMeConfig extends Configuration {
    private static final String USERNAME = "organizemeuser";
    private static final String PASSWORD = "79NAau5sPxKhwDKg";
    private static final String URL = "jdbc:postgresql://organizeme.crhem4ifyrwg.eu-west-1.rds.amazonaws.com:5432/organizemedb";

    public void addResources(final Environment environment){
        final PostgreSQLClient client = getPostgreSQLClient();
        environment.jersey().register(new OrganizeMeResource(client));
    }

    private PostgreSQLClient getPostgreSQLClient() {

        return new PostgreSQLClient(URL, USERNAME, PASSWORD, new PostgreSQLQueries());
    }

}
