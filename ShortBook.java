package com.is;

public class ShortBook {
    private int id;
    private String title;
    private String author;
    private String genere;
    
    //Constructor
    public ShortBook(int id,String title, String author, String genere) {
        this.title = title;
        if(isValidID(id)){
            this.id = id;
        }
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
    public ShortBook(String jsonData) {
        // Парсим строку вручную
        String[] parts = jsonData.replace("{", "").replace("}", "").replace("\"", "").split(",");
        
        for (String part : parts) {
            String[] pair = part.split(":");
            String key = pair[0].trim();
            String value = pair[1].trim();

            switch (key) {
                case "id":
                if(isValidID(Integer.parseInt(value))){
                    this.id = Integer.parseInt(value);
                }
                    break;
                case "title":
                    this.title = value;
                    break;
                case "author":
                    if (validateString(value)) {
                        this.author = value;
                    } else {
                        throw new IllegalArgumentException("Author name is invalid.");
                    }
                    break;
                case "genere":
                    if (validateString(value)) {
                        this.genere = value;
                    } else {
                        throw new IllegalArgumentException("Genre name is invalid.");
                    }
                    break;
            }
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
    public int getId(){
        return id;
    }
    public void setId(int id) {
        if(isValidID(id)){
            this.id = id;
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
    
   
    //validate methods
   
    
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
        return (ch >= 'A' && ch <= 'Z') || 
               (ch >= 'a' && ch <= 'z') || 
               (ch >= 'А' && ch <= 'Я') || 
               (ch >= 'а' && ch <= 'я') || 
               (ch == 'ё' || ch == 'Ё') || 
               (ch == ' ');                
    }
    private boolean isValidID(int id){
        if(id>=0){
            return(true);
        } else {
            throw new IllegalArgumentException("id is invalid.");
        }
    }
}
