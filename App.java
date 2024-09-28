package com.is;
import java.io.IOException;
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
        // String jsonString = "{\"title\":\"War and Peace\",\"author\":\"Leo Tolstoy\",\"genere\":\"Classic\",\"quantity\":500,\"depositAmount\":200.0,\"rentalCost\":20.0}";
        // Book book = Book_rep_json.fromJson(jsonString);
        // System.out.println(book); // Output: Book{title='War and Peace', author='Leo Tolstoy', genere='Classic'}
        // try {
        //     // Чтение содержимого JSON-файла
        //     String filePath = "/home/gleb/java/spring7AOP/demo/src/main/java/com/is/book.json"; // Укажи путь к твоему файлу
        //     String jsonContent = Book_rep_json.readJsonFromFile(filePath);

        //     // Парсинг содержимого JSON-файла в объект Book
        //     Book book2 = Book_rep_json.fromJson(jsonContent);
        //     System.out.println(book2);

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        try {
           //String outputFilePath = "/home/gleb/java/spring7AOP/demo/src/main/java/com/is/books.json"; // путь для записи нового JSON-файла
           String outputFilePath = "/home/gleb/java/spring7AOP/demo/src/main/java/com/is/books.yaml";
             Book book = new Book(0,"Cle Code", "Robert Martin", "Programming", 10, 30.0, 4.99);
           // Book book2 = new Book(1,"Cle Code", "Robert Martin", "Programming",1, 30.3, 4.4);
             Book book3 = new Book(3,"War and peace", "Tolstoy", "Drama", 3, 30.4, 4.3);
            
            // Book_rep_json.writeJsonToFile2(book, outputFilePath);
            // Book_rep_json.writeJsonToFile2(book2, outputFilePath);
            // Book_rep_json.writeJsonToFile2(book3, outputFilePath);
            // System.out.println("Данные записаны в файл: " + outputFilePath);
            //System.out.println(Book_rep_json.readJsonFromFile(outputFilePath));
            // System.out.println(Book_rep_json.getBookById(outputFilePath,1));
            //Book_rep_json.deleteBookById(outputFilePath, 1);
            // System.out.println(Book_rep_json.getBookById(outputFilePath,1));
            //Book_rep_json.replacement(book2,0,outputFilePath);
            //Book_rep_json.sortBooksByTitle(outputFilePath);
            //System.out.println( Book_rep_json.getCount(outputFilePath));
            //System.out.println(Book_rep_yaml.readYamlFromFile(outputFilePath));
            //System.out.println(Book_rep_yaml.getCount(outputFilePath));
            Book_rep_yaml.sortBooksByTitle(outputFilePath);
            //Book_rep_yaml.replacement(book3,3,outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
