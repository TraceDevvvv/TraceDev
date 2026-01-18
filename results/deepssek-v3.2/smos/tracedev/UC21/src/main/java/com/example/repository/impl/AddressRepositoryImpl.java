package com.example.repository.impl;

import com.example.model.Address;
import com.example.repository.AddressRepository;
import com.example.database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of AddressRepository.
 * Based on the UML class diagram.
 */
public class AddressRepositoryImpl implements AddressRepository {
    private Database dataSource;

    public AddressRepositoryImpl(Database dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Address findById(String addressId) {
        String sql = "SELECT * FROM address WHERE addressId = '" + addressId + "'";
        try {
            ResultSet rs = dataSource.executeQuery(sql);
            if (rs.next()) {
                return new Address(
                    rs.getString("addressId"),
                    rs.getString("street"),
                    rs.getString("city"),
                    rs.getString("postalCode")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Address address) {
        String sql = "INSERT INTO address (addressId, street, city, postalCode) VALUES ('" +
                address.getAddressId() + "', '" +
                address.getStreet() + "', '" +
                address.getCity() + "', '" +
                address.getPostalCode() + "') ON DUPLICATE KEY UPDATE street = VALUES(street), city = VALUES(city), postalCode = VALUES(postalCode)";
        int rows = dataSource.executeUpdate(sql);
        return rows > 0;
    }
}