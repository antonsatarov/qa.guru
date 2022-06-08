package reqrestests.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import reqrestests.core.BaseApi;
import reqrestests.pojo.UserData;
import reqrestests.utils.Generator;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("api")
public class GetSingleUserTests {

    private static final int MAX_ID = 12;

    @Test
    @DisplayName("Check user id in response")
    public void userIdIsCorrect() {
        int id = Generator.getRandomIntegerFromRange(1, MAX_ID);
        Response response = BaseApi.getSingleUser(id);
        UserData userData = response.body().jsonPath().getObject("data", UserData.class);

        assertThat(userData.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("404 error if user not found")
    public void userIdNotFoundStatusCode404() {
        int id = Generator.getRandomIntegerFromRange(MAX_ID + 1, 100);
        Response response = BaseApi.getSingleUser(id);

        assertThat(response.statusCode()).isEqualTo(404);
    }
}
