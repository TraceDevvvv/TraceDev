package com.etour.tags;

import com.etour.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Database implementation of TagRepository.
 * Uses a Database connection to query and delete tags.
 */
public class DatabaseTagRepository implements TagRepository {
    private Database database;

    public DatabaseTagRepository(Database database) {
        this.database = database;
    }

    @Override
    public List<Tag> findAll() {
        List<Tag> tags = new ArrayList<>();
        try {
            ResultSet rs = database.query("SELECT id, name, created_at FROM tags");
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                tags.add(new Tag(id, name, createdAt));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching tags: " + e.getMessage());
        }
        return tags;
    }

    @Override
    public void deleteById(String tagId) {
        String sql = "DELETE FROM tags WHERE id = '" + tagId + "'";
        database.executeUpdate(sql);
    }

    @Override
    public void deleteAllById(List<String> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) {
            return;
        }
        // Build a single DELETE statement for efficiency
        StringBuilder sb = new StringBuilder("DELETE FROM tags WHERE id IN (");
        for (int i = 0; i < tagIds.size(); i++) {
            sb.append("'").append(tagIds.get(i)).append("'");
            if (i < tagIds.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        database.executeUpdate(sb.toString());
    }
}