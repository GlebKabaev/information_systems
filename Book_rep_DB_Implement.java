package com.is;

import java.io.IOException;

public class Book_rep_DB_Implement implements Book_rep{
    Book_rep_DB db =Book_rep_DB.getInstance();
    @Override
    public Book getBookById(String filePath, int targetId) throws IOException {
        
        return db.getBookById(targetId);
    }
    public Book getBookById( int targetId) throws IOException {
        
        return db.getBookById(targetId);
    }
    
    @Override
    public void deleteBookById(String filePath, int targetId) throws IOException {
        db.deleteBookById(targetId);
        
    }
    public void deleteBookById( int targetId) throws IOException {
        db.deleteBookById(targetId);
        
    }
  
    @Override
    public int getCount(String filePath) throws IOException {
       
        return db.getCount();
    }
    public int getCount() throws IOException {
       
        return db.getCount();
    }
    @Override
    public void sortBooksByTitle(String filePath) throws IOException {
        throw new UnsupportedOperationException("Not implemented");
        
    }
    @Override
    public void updateBookById(Book newbook, int id, String filePath) throws IOException {
        
        db.updateBookById(id, newbook);
    }
    
    public void updateBookById(Book newbook, int id) throws IOException {
        
        db.updateBookById(id, newbook);
    }

    
    public void addBook(Book newBook) throws IOException {
        db.addBook(newBook);
    }
}
