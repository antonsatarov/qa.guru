package owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import owner.config.AndroidConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class AndroidTest {

    @Test
    public void testPropertiesFile() {
        AndroidConfig config = ConfigFactory.create(AndroidConfig.class, System.getProperties());

        assertThat(config.platformName()).isEqualTo("Android");
        assertThat(config.platformVersion()).isEqualTo("27.0");
        assertThat(config.deviceName()).isEqualTo("Google Pixel XL");
    }

}