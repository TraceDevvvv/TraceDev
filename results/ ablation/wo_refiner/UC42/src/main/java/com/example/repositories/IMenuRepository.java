package com.example.repositories;

import com.example.entities.Menu;
import com.example.enums.DayOfWeek;
import com.example.database.IUnitOfWork;
import com.example.exceptions.ConnectionException;

/**
 * Repository interface for Menu persistence operations.
 */
public interface IMenuRepository {
    Menu findByDay(DayOfWeek day);
    void delete(Menu menu) throws ConnectionException;
    IUnitOfWork getUnitOfWork();
}