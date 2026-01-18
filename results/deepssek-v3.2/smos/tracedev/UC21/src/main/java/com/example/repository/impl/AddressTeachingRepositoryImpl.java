package com.example.repository.impl;

import com.example.model.AddressTeaching;
import com.example.repository.AddressTeachingRepository;
import com.example.database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of AddressTeachingRepository.
 * Based on the UML class diagram.
 */
public class AddressTeachingRepositoryImpl implements AddressTeachingRepository {
    private Database dataSource;

    public AddressTeachingRepositoryImpl(Database dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AddressTeaching> findByAddressId(String addressId) {
        List<AddressTeaching> associations = new ArrayList<>();
        String sql = "SELECT * FROM addressteaching WHERE addressId = '" + addressId + "'";
        try {
            ResultSet rs = dataSource.executeQuery(sql);
            while (rs.next()) {
                AddressTeaching assoc = new AddressTeaching(
                    rs.getString("addressId"),
                    rs.getString("teachingId"),
                    rs.getDate("associationDate")
                );
                associations.add(assoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return associations;
    }

    @Override
    public boolean save(AddressTeaching association) {
        String sql = "INSERT INTO addressteaching (addressId, teachingId, associationDate) VALUES ('" +
                association.getAddressId() + "', '" +
                association.getTeachingId() + "', '" +
                new java.sql.Date(association.getAssociationDate().getTime()) + "') ON DUPLICATE KEY UPDATE associationDate = VALUES(associationDate)";
        int rows = dataSource.executeUpdate(sql);
        return rows > 0;
    }

    @Override
    public boolean delete(AddressTeaching association) {
        String sql = "DELETE FROM addressteaching WHERE addressId = '" + association.getAddressId() + "' AND teachingId = '" + association.getTeachingId() + "'";
        int rows = dataSource.executeUpdate(sql);
        return rows > 0;
    }
}