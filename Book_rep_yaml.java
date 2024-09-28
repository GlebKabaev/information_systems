package com.is;

import java.io.*;
import java.util.*;

public class Book_rep_yaml implements Book_rep{

    // Чтение YAML из файла
    public static List<Map<String, String>> readFromFile(String filePath) throws IOException {
        List<Map<String, String>> yamlContent = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Map<String, String> currentMap = null;
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // Пропускаем пустые строки
                }
                if (line.startsWith("-")) {
                    if (currentMap != null) {
                        yamlContent.add(currentMap);
                    }
                    currentMap = new HashMap<>();
                } else if (currentMap != null && line.contains(":")) {
                    String[] keyValue = line.split(":", 2);
                    if (keyValue.length == 2) {
                        currentMap.put(keyValue[0].trim(), keyValue[1].trim());
                    }
                }
            }
            if (currentMap != null) {
                yamlContent.add(currentMap);
            }
        }
        return yamlContent;
    }
    

    // Преобразование YAML строки в объект Book
    public static Book toBook(Map<String, String> yamlMap) {
        int id = Integer.parseInt(yamlMap.get("id"));
        String title = yamlMap.get("title");
        String author = yamlMap.get("author");
        String genere = yamlMap.get("genere");
        int quantity = Integer.parseInt(yamlMap.get("quantity"));
        double depositAmount = Double.parseDouble(yamlMap.get("depositAmount"));
        double rentalCost = Double.parseDouble(yamlMap.get("rentalCost"));

        return new Book(id, title, author, genere, quantity, depositAmount, rentalCost);
    }

    // // Запись YAML в файл
    // public static void writeYamlToFile(Book book, String filePath) throws IOException {
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
    //         writer.write(bookToYaml(book));
    //     }
    // }

    // Запись YAML массива в файл с добавлением новых объектов
    public static void writeToFile(Book book, String filePath) throws IOException {
        List<Map<String, String>> yamlContent = readFromFile(filePath);
        yamlContent.add(toMap(book));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map<String, String> map : yamlContent) {
                writer.write("-\n");
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    writer.write("  " + entry.getKey() + ": " + entry.getValue() + "\n");
                }
            }
        }
    }

    // Получение объекта Book по ID из YAML файла
    public  Book getBookById(String filePath, int targetId) throws IOException {
        List<Map<String, String>> yamlContent = readFromFile(filePath);

        for (Map<String, String> yamlMap : yamlContent) {
            int id = Integer.parseInt(yamlMap.get("id"));
            if (id == targetId) {
                return toBook(yamlMap);
            }
        }
        return null;  // Возвращаем null, если объект не найден
    }

    // Удаление книги по ID из YAML файла
    public  void deleteBookById(String filePath, int targetId) throws IOException {
        List<Map<String, String>> yamlContent = readFromFile(filePath);
        List<Map<String, String>> updatedContent = new ArrayList<>();

        for (Map<String, String> yamlMap : yamlContent) {
            int id = Integer.parseInt(yamlMap.get("id"));
            if (id != targetId) {
                updatedContent.add(yamlMap);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map<String, String> map : updatedContent) {
                writer.write("-\n");
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    writer.write("  " + entry.getKey() + ": " + entry.getValue() + "\n");
                }
            }
        }
    }

    // Преобразование объекта Book в YAML строку
    private static String bookToYaml(Book book) {
        StringBuilder yamlBuilder = new StringBuilder();
        yamlBuilder.append("-\n");
        yamlBuilder.append("  id: ").append(book.getId()).append("\n");
        yamlBuilder.append("  title: ").append(book.getTitle()).append("\n");
        yamlBuilder.append("  author: ").append(book.getAuthor()).append("\n");
        yamlBuilder.append("  genere: ").append(book.getGenere()).append("\n");
        yamlBuilder.append("  quantity: ").append(book.getQuantity()).append("\n");
        yamlBuilder.append("  depositAmount: ").append(book.getDepositAmount()).append("\n");
        yamlBuilder.append("  rentalCost: ").append(book.getRentalCost()).append("\n");
        return yamlBuilder.toString();
    }

    // Преобразование объекта Book в Map для дальнейшего использования
    private static Map<String, String> toMap(Book book) {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(book.getId()));
        map.put("title", book.getTitle());
        map.put("author", book.getAuthor());
        map.put("genere", book.getGenere());
        map.put("quantity", String.valueOf(book.getQuantity()));
        map.put("depositAmount", String.valueOf(book.getDepositAmount()));
        map.put("rentalCost", String.valueOf(book.getRentalCost()));
        return map;
    }
    // Подсчет количества объектов (книг) в YAML файле
    public  int getCount(String filePath) throws IOException {
    List<Map<String, String>> yamlContent = readFromFile(filePath);
    return yamlContent.size();
    }
// Сортировка книг по полю title
    public  void sortBooksByTitle(String filePath) throws IOException {
        List<Map<String, String>> yamlContent = readFromFile(filePath);
        List<Book> bookList = new ArrayList<>();

        // Преобразуем каждый YAML объект в объект Book
        for (Map<String, String> yamlMap : yamlContent) {
            Book book = toBook(yamlMap);
            bookList.add(book);
        }

        // Сортируем список по полю title
        bookList.sort(Comparator.comparing(Book::getTitle));

        // Записываем отсортированные книги обратно в YAML файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : bookList) {
                writer.write(bookToYaml(book));
            }
        }
    }
    public  void replacement(Book newbook,int id,String filepath)throws IOException{
        deleteBookById(filepath,id);
        writeToFile(newbook,filepath);
    }
}
