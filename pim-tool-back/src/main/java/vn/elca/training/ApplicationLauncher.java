package vn.elca.training;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vn.elca.training.util.DataProvider;


/**
 * @author vlp
 */
public class ApplicationLauncher {
    static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationWebConfig.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationWebConfig.class, args);
        DataProvider.init();
        System.out.println("Spring Boot Application is running");
    }
}
