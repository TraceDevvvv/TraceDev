
package com.example.usecase;

import java.util.List;

/**
 * Use case interface for searching tourists.
 */
public interface ITouristSearchUseCase {
    List<Object> execute(Object criteria);
}
