package com.example.app.infrastructure;

import com.example.app.domain.ClassRegister;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class RegisterRepositoryImpl implements IRegisterRepository {
    private final Map<String, ClassRegister> registers = new HashMap<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public RegisterRepositoryImpl() {
        // Populate with some mock data
        try {
            ClassRegister register1 = new ClassRegister("REG001", "Math Grade 10A", "Advanced Mathematics for 10th graders", dateFormat.parse("2023-01-15"));
            ClassRegister register2 = new ClassRegister("REG002", "Physics Grade 11B", "Introduction to Physics for 11th graders", dateFormat.parse("2023-02-01"));
            registers.put(register1.getId(), register1);
            registers.put(register2.getId(), register2);
        } catch (ParseException e) {
            System.err.println("Error parsing mock date in RegisterRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public ClassRegister findById(String registerId) {
        // Simulates fetching from a database
        System.out.println("DB: SELECT * FROM Registers WHERE id = " + registerId);
        return registers.get(registerId);
    }
}