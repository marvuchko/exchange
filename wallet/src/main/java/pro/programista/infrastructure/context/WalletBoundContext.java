package pro.programista.infrastructure.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource(
    ignoreResourceNotFound = false,
    value = "classpath:wallet-module.properties"
)
public class WalletBoundContext {
}
