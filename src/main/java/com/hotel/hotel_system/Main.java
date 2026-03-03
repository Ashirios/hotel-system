package com.hotel.hotel_system;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hotel System Started ===");
        System.out.println("Application is running...");
        
        // Держим приложение запущенным
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
