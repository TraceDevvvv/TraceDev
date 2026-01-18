package com.example.controller;

/**
 * Database participant class to represent the E-Tour DB.
 * This class provides the database operations referenced in the sequence diagram.
 */
public class DB {
    
    /**
     * Simulates SELECT * FROM news database query (Sequence message m11)
     */
    public void SELECT_ALL_FROM_news() {
        System.out.println("Database: Executing SELECT * FROM news");
    }
    
    /**
     * Simulates returning list of news data (Sequence message m12)
     */
    public void List_of_news_data() {
        System.out.println("Database: Returning list of news data");
    }
    
    /**
     * Simulates SELECT * FROM news WHERE id = newsId database query (Sequence message m26)
     */
    public void SELECT_ALL_FROM_news_WHERE_id(int newsId) {
        System.out.println("Database: Executing SELECT * FROM news WHERE id = " + newsId);
    }
    
    /**
     * Simulates returning news data (Sequence message m27)
     */
    public void News_data() {
        System.out.println("Database: Returning news data");
    }
    
    /**
     * Simulates DELETE FROM news WHERE id = newsId database query (Sequence messages m34, m52)
     */
    public void DELETE_FROM_news_WHERE_id(int newsId) {
        System.out.println("Database: Executing DELETE FROM news WHERE id = " + newsId);
    }
    
    /**
     * Simulates returning deletion success (Sequence message m35)
     */
    public void Deletion_success() {
        System.out.println("Database: Deletion success");
    }
    
    /**
     * Simulates connection lost (Sequence message m53)
     */
    public void Connection_Lost() {
        System.out.println("Database: Connection Lost");
    }
}