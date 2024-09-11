package com.is;



public class Book extends ShortBook {
    
    private int quantity;
    private double depositAmount;
    private double rentalCost;
    //Constructor
    public Book(String title, String author, String genere, int quantity, double depositAmount, double rentalCost) {
        super(title, author, genere);
        this.quantity =validateNumberField(quantity);
        this.depositAmount =validateNumberField(depositAmount);
        this.rentalCost = validateNumberField(rentalCost);
    }
    //Getters and setters
    
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity =validateNumberField(quantity);
    }
    public double getDepositAmount() {
        return depositAmount;
    }
    public void setDepositAmount(double depositAmount) {
        this.depositAmount =validateNumberField(depositAmount);
    }
    public double getRentalCost() {
        return rentalCost;
    }
    public void setRentalCost(double rentalCost) {
        this.rentalCost = validateNumberField(rentalCost);
    }
    //toString method
    @Override
    public String toString() {
        
        return super.toString()+
                ", quantity=" + quantity +
                ", depositAmount=" + depositAmount +
                ", rentalCost=" + rentalCost +
                '}';
    }
    public String shortInfo() {
        return 
        super.toString();
                
    }
   
    //validate method
    public static int validateNumberField(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot be negative");
        }
        return number;
    }
    public static double validateNumberField(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot be negative");
        }
        return number;
    }
   
    
}
