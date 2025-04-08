package tests;

import models.BookModel;
import models.CredentialsModel;

public class TestData {
    public static String login = "JuliaTest1",
                         password = "JuliaTest1/!",
                         isbn = "9781449365035",
                         title = "Speaking JavaScript";;

    public static CredentialsModel credentials = new CredentialsModel(login, password);
    public static BookModel book = new BookModel(isbn, title);
}