
package com.midas.challenge.challengemidas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Eze
 */
@SpringBootApplication
public class MainApp implements CommandLineRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        LOGGER.info("Init application");
        SpringApplication.run(MainApp.class, args);
        LOGGER.info("application finished");
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
