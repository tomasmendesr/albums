package wolox.albumes;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wolox.albumes.repositories.SharedAlbumRepository;

@Configuration
@Slf4j
public class LoadDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SharedAlbumRepository repository) {
        return args -> {
           /* LOGGER.("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
            LOGGER.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));*/
        };
    }
}
