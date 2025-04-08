package api;

import models.CredentialsModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.ApiSpecs.getBaseResponseSpec;
import static specs.ApiSpecs.resourcesRequestSpec;

public class AuthorizationApi {
    public LoginResponseModel login(CredentialsModel credentials){
        return given(resourcesRequestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(getBaseResponseSpec(200))
                .extract().as(LoginResponseModel.class);
    }
}
