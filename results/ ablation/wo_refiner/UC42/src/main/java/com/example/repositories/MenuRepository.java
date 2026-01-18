package com.example.repositories;

import com.example.entities.Menu;
import com.example.enums.DayOfWeek;
import com.example.database.IDataSource;
import com.example.database.IUnitOfWork;
import com.example.exceptions.ConnectionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Concrete implementation of IMenuRepository.
 * Assumes IDataSource is injected and IUnitOfWork is implemented elsewhere.
 */
public class MenuRepository implements IMenuRepository {
    private static final Logger LOGGER = Logger.getLogger(MenuRepository.class.getName());
    private IDataSource dataSource;
    private IUnitOfWork unitOfWork;

    public MenuRepository(IDataSource dataSource, IUnitOfWork unitOfWork) {
        this.dataSource = dataSource;
        this.unitOfWork = unitOfWork;
    }

    @Override
    public Menu findByDay(DayOfWeek day) {
        // Simulate querying menu by day
        String query = "SELECT * FROM menu WHERE day = '" + day.name() + "'";
        LOGGER.info("Executing query: " + query);
        try {
            ResultSet rs = dataSource.executeQuery(query);
            // Assuming mapping to Menu object; simplified for example
            return new Menu(1L, day, new ArrayList<>());
        } catch (Exception e) {
            LOGGER.severe("Error finding menu by day: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Menu menu) throws ConnectionException {
        // Simulate delete operation that may throw ConnectionException
        String query = "DELETE FROM menu WHERE id = " + menu.getId();
        LOGGER.info("Executing delete: " + query);
        try {
            int rows = dataSource.executeUpdate(query);
            if (rows == 0) {
                LOGGER.warning("No menu deleted for id: " + menu.getId());
            }
        } catch (Exception e) {
            // Simulate connection loss
            throw new ConnectionException("Database connection lost during delete.");
        }
    }

    @Override
    public IUnitOfWork getUnitOfWork() {
        return unitOfWork;
    }
}