package pro.programista;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pro.programista.infrastructure.context.UserBoundContext;
import pro.programista.infrastructure.context.WalletBoundContext;

@Import({
    UserBoundContext.class,
    WalletBoundContext.class
})
@SpringBootApplication
public class GatewayApplication {

  public static void main(String... args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

}
