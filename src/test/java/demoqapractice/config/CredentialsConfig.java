package demoqapractice.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentials.prop")
public interface CredentialsConfig extends Config {
    String login();
    String password();
}
