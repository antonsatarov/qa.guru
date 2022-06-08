package reqrestests.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import reqrestests.core.BaseApi;
import reqrestests.pojo.Employee;
import reqrestests.utils.Generator;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("api")
public class UserCRUDTests {

    private static final int MAX_ID = 12;

    @Test
    @DisplayName("User is created with exact name and job")
    public void createUserTest() {
        Employee employeeActual = new Employee(
                "Bob",
                "Engineer"
        );
        Response response = BaseApi.createUser(employeeActual);
        Employee employeeExpected = response.body().as(Employee.class);

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(employeeExpected).isEqualTo(employeeActual);
    }

    @Test
    @DisplayName("Updated user has new values for name and job fields")
    public void updateUserTest() {
        Employee employeeActual = new Employee(
                "Bob",
                "Engineer"
        );
        int id = Generator.getRandomIntegerFromRange(1, MAX_ID);
        Response response = BaseApi.updateUser(id, employeeActual);
        Employee employeeExpected = response.body().as(Employee.class);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(employeeExpected).isEqualTo(employeeActual);
    }

    @Test
    @DisplayName("User is deleted. Status code is 204")
    public void deleteUserTest() {
        int id = Generator.getRandomIntegerFromRange(1, MAX_ID);
        Response response = BaseApi.deleteUser(id);

        assertThat(response.statusCode()).isEqualTo(204);
    }

}
