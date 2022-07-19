package owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import owner.config.AuthConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest {

    @Test
    public void testClasspath() {
        AuthConfig config = ConfigFactory
                .create(AuthConfig.class, System.getProperties());

        assertThat(config.username()).isEqualTo("user");
        assertThat(config.password()).isEqualTo("pass");
    }

    @Test
    public void testLocalFile() throws IOException {
        // вот это будет происходить в Jenins, вы к этому не имеете доступа
        String content = "username=secret-user\npassword=secret-pass";
        Path propsPath = Paths.get("/tmp/secret.properties");
        Files.writeString(propsPath, content);
        // вот это будет происходить в Jenins, вы к этому не имеете доступа

        AuthConfig config = ConfigFactory
                .create(AuthConfig.class, System.getProperties());
        assertThat(config.username()).isEqualTo("secret-user");
        assertThat(config.password()).isEqualTo("secret-pass");

        // вот это будет происходить в Jenins, вы к этому не имеете доступа
        Files.delete(propsPath);
    }

}