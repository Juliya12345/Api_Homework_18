package tests;

import models.BookModel;
import models.CredentialsModel;

public class TestData {
    public static String LOGIN = System.getProperty("login"),
            PASSWORD  = System.getProperty("password"),
                         isbn = "9781449365035",
                         title = "Speaking JavaScript";;

    public static CredentialsModel credentials = new CredentialsModel(LOGIN, PASSWORD);
    public static BookModel book = new BookModel(isbn, title);
}