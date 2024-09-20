package com.is;
import java.io.IOException;
import com.is.Book_rep_json;
public class App {
    public static void main(String[] args) {
        // Book book1 = new Book("Aurora","Michel de Port","Dramma",2,2.33,0.5);
        // System.out.println(book1);
        // Book book2 = new Book("The Lord of the Rings","Tolkien","Fantasy",3,3.55,1.2);
        // System.out.println(book2);
        // System.out.println("is Books are equals? "+ book1.equals(book2));
        // Book book3 = book1;
        // System.out.println("is Books are equals? "+ book1.equals(book3));
        // System.out.println("Short info book1: "+ book1.shortInfo());
        String jsonString = "{\"title\":\"War and Peace\",\"author\":\"Leo Tolstoy\",\"genere\":\"Classic\",\"quantity\":500,\"depositAmount\":200.0,\"rentalCost\":20.0}";
        Book book = Book_rep_json.fromJson(jsonString);
        System.out.println(book); // Output: Book{title='War and Peace', author='Leo Tolstoy', genere='Classic'}
        try {
            // Чтение содержимого JSON-файла
            String filePath = "/home/gleb/java/spring7AOP/demo/src/main/java/com/is/book.json"; // Укажи путь к твоему файлу
            String jsonContent = Book_rep_json.readJsonFromFile(filePath);

            // Парсинг содержимого JSON-файла в объект Book
            Book book2 = Book_rep_json.fromJson(jsonContent);
            System.out.println(book2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
