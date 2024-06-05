package br.com.fatecmogidascruzes.fatecwebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.fatecmogidascruzes.fatecwebsite.repository")
public class MongoConfig {
}
