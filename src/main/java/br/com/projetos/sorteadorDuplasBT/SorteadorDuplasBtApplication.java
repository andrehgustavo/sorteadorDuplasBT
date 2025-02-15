package br.com.projetos.sorteadorDuplasBT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SorteadorDuplasBtApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String dburl = dotenv.get("DB_URL");
        if (dburl != null) {
            System.setProperty("DB_URL", dburl);
        } else {
            System.out.println("⚠️ AVISO: DB_URL não foi definida!");
        }

        String dbUsernName = dotenv.get("DB_USERNAME");
        if (dbUsernName != null) {
            System.setProperty("DB_USERNAME", dbUsernName);
        } else {
            System.out.println("⚠️ AVISO: DB_USERNAME não foi definida!");
        }

        String dbPassword = dotenv.get("DB_PASSWORD");
        if (dbPassword != null) {
            System.setProperty("DB_PASSWORD", dbPassword);
        } else {
            System.out.println("⚠️ AVISO: DB_PASSWORD não foi definida!");
        }

        /* Mesmo para outras variáveis */
        String jwtSecret = dotenv.get("JWT_SECRET");
        if (jwtSecret != null) {
            System.setProperty("JWT_SECRET", jwtSecret);
        } else {
            System.out.println("⚠️ AVISO: JWT_SECRET não foi definida!");
        }
        
		SpringApplication.run(SorteadorDuplasBtApplication.class, args);
        System.out.println("Aplicação no ar 🚀! Vá para http://localhost:8084/");
	}

}
