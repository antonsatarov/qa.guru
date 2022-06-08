package reqrestests.core;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reqrestests.pojo.Employee;

import static io.restassured.RestAssured.given;

public class BaseApi {

    public static final String BASE_URL = "https://reqres.in/";

    public static Response getSingleUser(int id) {
        return given(Specifications.requestSpec(BASE_URL))
                .when()
                    .pathParam("user_id", id)
                    .get("api/users/{user_id}")
                .then().log().ifError()
                    .extract().response();
    }

    public static Response createUser(Employee employee) {
        return given(Specifications.requestSpec(BASE_URL))
                .when()
                    .contentType(ContentType.JSON)
                    .body(employee)
                    .post("api/users")
                .then().log().ifError()
                    .extract().response();
    }

    public static Response updateUser(int id, Employee employee) {
        return given(Specifications.requestSpec(BASE_URL))
                .when()
                    .contentType(ContentType.JSON)
                    .pathParam("user_id", id)
                    .body(employee)
                    .put("api/users/{user_id}")
                .then().log().ifError()
                    .extract().response();
    }

    public static Response deleteUser(int id) {
        return given(Specifications.requestSpec(BASE_URL))
                .when()
                .pathParam("user_id", id)
                .delete("api/users/{user_id}")
                .then().log().ifError()
                .extract().response();
    }

}
