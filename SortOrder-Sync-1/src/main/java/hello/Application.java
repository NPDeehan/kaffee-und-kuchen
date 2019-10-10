package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import java.security.Provider;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
//        try {
//
//            System.out.println("What do you want to ask Mike?");
//
//            Scanner scanner = new Scanner(System.in);
//
//            new ServiceController().index(scanner.next());
//
//            System.out.println("We've successfully sent the message to Mike");
//            System.out.println("What do you wan to way next?");
//
//        }
//        catch (Exception ex) {
//            // silently ignore
//        }
    }

}
