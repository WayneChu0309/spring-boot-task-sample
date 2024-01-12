package wayne.springboot.task.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "wayne.springboot.task.repository")
public class DataBaseConfig extends AbstractMongoClientConfiguration {

    @Value("${mongodb.dbname}")
    String databaseName;

    @Value("${mongodb.url}")
    private String mongoUri;

    @Override
    protected String getDatabaseName() {
        return this.databaseName;
    }


    @Override
    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient = MongoClients.create(mongoUri);
        return MongoClients.create(mongoUri);
    }


}
