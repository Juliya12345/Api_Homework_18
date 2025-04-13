package tests;

import api.AuthorizationApiSteps;
import api.BooksApiSteps;
import models.AddBooksListModel;
import models.IsbnModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

import org.openqa.selenium.Cookie;

public class BooksTests extends TestBase {

    @Test
    void addBookToCartAndDeleteBookTest(){

        AuthorizationApiSteps authorizationApi = new AuthorizationApiSteps();
        LoginResponseModel loginResponse = authorizationApi.login(TestData.credentials);

        BooksApiSteps booksApi = new BooksApiSteps();
        booksApi.deleteAllBooks(loginResponse);

        step("Добавление книги в профиль пользователя", () -> {
        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(TestData.book.getIsbn());
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);
        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);
        booksApi.addBook(loginResponse,booksList);    });

        step("Удаление книги из профиля пользователя", () -> {
        booksApi.deleteBook(loginResponse, TestData.book.getIsbn());});

        step("Проверка, что книга исчезла", () -> {

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));


        open("/profile");
        $("[id='see-book-" + TestData.book.getTitle() + "']").shouldNotBe(visible);
        });
    }

}
