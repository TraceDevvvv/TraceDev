package com.system.repository;

import com.system.entities.Tag;
import com.system.exceptions.DatabaseException;
import com.system.data.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Concrete implementation of ITagRepository using a DataSource.
 */
public class TagRepositoryImpl implements ITagRepository {
    private DataSource dataSource;

    public TagRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Tag> findAll() {
        List<Tag> tags = new ArrayList<>();
        try (ResultSet rs = dataSource.executeQuery("SELECT * FROM tags")) {
            while (rs.next()) {
                Tag tag = mapResultSetToTag(rs);
                tags.add(tag);
            }
        } catch (SQLException e) {
            // Log error; return empty list for simplicity.
            System.err.println("Error fetching tags: " + e.getMessage());
        }
        return tags;
    }

    @Override
    public Optional<Tag> findById(String id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("SELECT * FROM tags WHERE id = ?")) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToTag(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error finding tag by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Tag save(Tag tag) {
        // Simplified save - in real scenario, we would check if it's an insert or update.
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(
                "INSERT INTO tags (id, name, description, created_at, updated_at) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, tag.getId());
            stmt.setString(2, tag.getName());
            stmt.setString(3, tag.getDescription());
            stmt.setTimestamp(4, Timestamp.valueOf(tag.getCreatedAt()));
            stmt.setTimestamp(5, Timestamp.valueOf(tag.getUpdatedAt()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving tag: " + e.getMessage());
        }
        return tag;
    }

    @Override
    public void deleteById(String id) throws DatabaseException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM tags WHERE id = ?")) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting tag with id " + id, "DB-002");
        }
    }

    @Override
    public void deleteAllByIds(List<String> ids) throws DatabaseException {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        StringBuilder sql = new StringBuilder("DELETE FROM tags WHERE id IN (");
        for (int i = 0; i < ids.size(); i++) {
            sql.append("?");
            if (i < ids.size() - 1) sql.append(",");
        }
        sql.append(")");
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < ids.size(); i++) {
                stmt.setString(i + 1, ids.get(i));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Simulate connection interrupted scenario.
            if (e.getMessage().contains("Connection") || e.getMessage().contains("interrupted")) {
                throw new DatabaseException("Connection to server interrupted", "DB-001");
            }
            throw new DatabaseException("Error deleting tags", "DB-003");
        }
    }

    private Tag mapResultSetToTag(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        return new Tag(id, name, description, createdAt, updatedAt);
    }
}