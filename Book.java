package com.is;



public class Book {
    private String title;
    private String author;
    private String genere;
    private int quantity;
    private float depositAmount;
    private float rentalCost;
    //Constructor
    public Book(String title, String author, String genere, int quantity, float depositAmount, float rentalCost) {
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
        this.quantity =validateNumberField(quantity);
        this.depositAmount =validateNumberField(depositAmount);
        this.rentalCost = validateNumberField(rentalCost);
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity =validateNumberField(quantity);
    }
    public float getDepositAmount() {
        return depositAmount;
    }
    public void setDepositAmount(float depositAmount) {
        this.depositAmount =validateNumberField(depositAmount);
    }
    public float getRentalCost() {
        return rentalCost;
    }
    public void setRentalCost(float rentalCost) {
        this.rentalCost = validateNumberField(rentalCost);
    }
    //toString method
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genere='" + genere + '\'' +
                ", quantity=" + quantity +
                ", depositAmount=" + depositAmount +
                ", rentalCost=" + rentalCost +
                '}';
    }
   
    //validate method
    public static int validateNumberField(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot be negative");
        }
        return number;
    }
    public static float validateNumberField(float number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot be negative");
        }
        return number;
    }
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
