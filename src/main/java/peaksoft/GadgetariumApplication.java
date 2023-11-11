package peaksoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.Objects;
@Controller
@SpringBootApplication
public class GadgetariumApplication {
    public static void main(String[] args) throws IOException {


        SpringApplication.run(GadgetariumApplication.class, args);
        System.out.println("\n  " +
                "\n   Geh WEITER...");
    }

}
