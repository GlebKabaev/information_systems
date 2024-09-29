package com.is;

import java.io.*;


public interface Book_rep{
    public abstract Book getBookById(String filePath, int targetId) throws IOException;
    public abstract void deleteBookById(String filePath, int targetId) throws IOException ;
    public abstract int getCount(String filePath) throws IOException;
    public abstract void sortBooksByTitle(String filePath) throws IOException;
    public abstract void updateBookById(Book newbook,int id,String filepath)throws IOException;
}
