package com.is;



public class Book extends ShortBook {
    
    private int quantity;
    private double depositAmount;
    private double rentalCost;
    //Constructor
    public Book(int id,String title, String author, String genere, int quantity, double depositAmount, double rentalCost) {
        super(id,title, author, genere);
        this.quantity =validateNumberField(quantity);
        this.depositAmount =validateNumberField(depositAmount);
        this.rentalCost = validateNumberField(rentalCost);
    }
    public Book(String jsonData) {
        super(jsonData);
        // Убираем фигурные скобки и разделяем строку по запятым
        String[] parts = jsonData.replace("{", "").replace("}", "").replace("\"", "").split(",");
        
        //String title = null, author = null, genere = null;
        
        int quantity = 0;
        double depositAmount = 0.0, rentalCost = 0.0;

        for (String part : parts) {
            // Каждая часть делится на ключ и значение
            String[] pair = part.split(":");
            String key = pair[0].trim();
            String value = pair[1].trim();

            switch (key) {
                // case "title":
                //     title = value;
                //     break;
                // case "author":
                //     author = value;
                //     break;
                // case "genere":
                //     genere = value;
                //     break;
                case "quantity":
                    quantity = Integer.parseInt(value);
                    break;
                case "depositAmount":
                    depositAmount = Double.parseDouble(value);
                    break;
                case "rentalCost":
                    rentalCost = Double.parseDouble(value);
                    break;
            }
    }

    this.quantity = validateNumberField(quantity);
    this.depositAmount = validateNumberField(depositAmount);
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
        
        return super.toString().replaceAll("}", "")+
                ", quantity=" + quantity +
                ", depositAmount=" + depositAmount +
                ", rentalCost=" + rentalCost +
                '}';
    }
    public String toJson() {
        return "{\n" +
                "\"id\": \"" + getId() + "\",\n" +
                "\"title\": \"" + getTitle() + "\",\n" +
                "\"author\": \"" + getAuthor() + "\",\n" +
                "\"genere\": \"" + getGenere() + "\",\n" +
                "\"quantity\": \"" + getQuantity() + "\",\n" +
                "\"depositAmount\": \"" + getDepositAmount() + "\",\n" +
                "\"rentalCost\": \"" + getRentalCost() + "\"\n" +
                "}";
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
