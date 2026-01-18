package com.example;

import com.example.database.IDataSource;
import com.example.database.IUnitOfWork;
import com.example.enums.DayOfWeek;
import com.example.repositories.IMenuRepository;
import com.example.repositories.MenuRepository;
import com.example.serv.AuthenticationService;
import com.example.serv.MenuService;
import com.example.ui.UIDeleteForm;
import java.sql.ResultSet;

/**
 * Main class to demonstrate the flow.
 * It includes a simple mock for IDataSource and IUnitOfWork to make the code runnable.
 */
public class Main {
    // Mock implementations for database interfaces
    static class MockDataSource implements IDataSource {
        @Override
        public ResultSet executeQuery(String query) { return null; }
        @Override
        public int executeUpdate(String query) { return 1; }
        @Override
        public void beginTransaction() { System.out.println("Transaction begun."); }
        @Override
        public void commit() { System.out.println("Transaction committed."); }
        @Override
        public void rollback() { System.out.println("Transaction rolled back."); }
    }

    static class MockUnitOfWork implements IUnitOfWork {
        @Override
        public void commit() { System.out.println("UoW commit."); }
        @Override
        public void rollback() { System.out.println("UoW rollback."); }
        @Override
        public void beginTransaction() { System.out.println("UoW begin transaction."); }
    }

    public static void main(String[] args) {
        // Setup mocks
        IDataSource dataSource = new MockDataSource();
        IUnitOfWork unitOfWork = new MockUnitOfWork();
        IMenuRepository menuRepository = new MenuRepository(dataSource, unitOfWork);
        MenuService menuService = new MenuService(menuRepository);
        AuthenticationService authService = new AuthenticationService();
        UIDeleteForm uiForm = new UIDeleteForm(menuService, authService);

        // Create operator
        RestaurantOperator operator = new RestaurantOperator("operator123", "John", uiForm, authService);

        // Simulate main success scenario
        System.out.println("Starting simulation...");
        operator.displayDaysForm();
        operator.selectDay(DayOfWeek.MONDAY);
        uiForm.deleteMenu(DayOfWeek.MONDAY);
        operator.displayConfirmation();
        operator.confirmOperation();
        uiForm.notifySuccess();
    }
}