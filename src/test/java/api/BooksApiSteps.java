package api;

import io.qameta.allure.Step;
import models.AddBooksListModel;
import models.DeleteBookModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.ApiSpecs.resourcesRequestSpec;
import static specs.ApiSpecs.getBaseResponseSpec;


public class BooksApiSteps {

    @Step("Удаление всех книг из профиля пользователя")
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(resourcesRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(getBaseResponseSpec(204));
    }
    @Step("Добавление книги в профиль пользователя")
    public void addBook(LoginResponseModel loginResponse, AddBooksListModel booksList) {
        given(resourcesRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(getBaseResponseSpec(201));
    }

    @Step("Удаление добавленной книги из профиля пользователя")
    public void deleteBook(LoginResponseModel loginResponse, String isbn) {
        DeleteBookModel deleteBook = new DeleteBookModel(loginResponse.getUserId(), isbn);

        given(resourcesRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(deleteBook)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(getBaseResponseSpec(204));
    }
}

