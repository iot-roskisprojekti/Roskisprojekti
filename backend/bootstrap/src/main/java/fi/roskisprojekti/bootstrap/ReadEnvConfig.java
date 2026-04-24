package fi.roskisprojekti.bootstrap;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadEnvConfig {

    static {
        Dotenv.configure()
                .ignoreIfMissing()
                .systemProperties()
                .load();

        System.out.println("Environment variables loaded into System Properties.");
    }
}