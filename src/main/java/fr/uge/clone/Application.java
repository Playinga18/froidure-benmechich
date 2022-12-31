package fr.uge.clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application, utilisée pour démarrer le contexte Spring.
 */
@SpringBootApplication
public class Application {

    /**
     * Point d'entrée de l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
