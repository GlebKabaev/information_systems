package com.is;

public class ShortBook {

    private String title;
    private String author;
    private String genere;
    
    //Constructor
    public ShortBook(String title, String author, String genere) {
        this.title = title;
        if(validateString(author)){
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author name is invalid.");
        }
        if(validateString(genere)){
            this.genere = genere;
        } else {
            throw new IllegalArgumentException("Genre name is invalid.");
        }
        
    }
    //Getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        if(validateString(author)){
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author name is invalid.");
        }
    }
    public String getGenere() {
        return genere;
    }
    public void setGenere(String genere) {
        if(validateString(genere)){
            this.genere = genere;
        } else {
            throw new IllegalArgumentException("Genre name is invalid.");
        }
    }
   
    //toString method
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genere='" + genere + '\''+"}";
                
    }
    
   
    //validate method
   
    
    public static boolean validateString(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!isValidCharacter(ch)) {
                return false;
            }
        }
        return true;
    }
    private static boolean isValidCharacter(char ch) {
        return (ch >= 'A' && ch <= 'Z') || // Латинские заглавные буквы
               (ch >= 'a' && ch <= 'z') || // Латинские строчные буквы
               (ch >= 'А' && ch <= 'Я') || // Кириллические заглавные буквы
               (ch >= 'а' && ch <= 'я') || // Кириллические строчные буквы
               (ch == 'ё' || ch == 'Ё') || // Дополнительные кириллические буквы
               (ch == ' ');                // Пробел
    }
}
