package com.is;

public class App {
    public static void main(String[] args) {
        Book book1 = new Book("Aurora","Michel de Port","Dramma",2,2.33,0.5);
        System.out.println(book1);
        Book book2 = new Book("The Lord of the Rings","Tolkien","Fantasy",3,3.55,1.2);
        System.out.println(book2);
        System.out.println("is Books are equals? "+ book1.equals(book2));
        Book book3 = book1;
        System.out.println("is Books are equals? "+ book1.equals(book3));
        System.out.println("Short info book1: "+ book1.shortInfo());
    }
}
