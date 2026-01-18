package infrastructure;

import domain.Tag;
import domain.TagRepository;
import java.sql.*;
import java.util.Optional;

/**
 * Concrete implementation of TagRepository using a database.
 * Note: Simplified for example; real implementation would handle exceptions and connection pooling.
 */
public class TagRepositoryImpl implements TagRepository {
    private ConnectionManager connectionManager;

    public TagRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Optional<Tag> findByName(String name) {
        String sql = "SELECT id, name, description, created_at FROM tags WHERE name = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Tag tag = new Tag(rs.getString("id"),
                                 rs.getString("name"),
                                 rs.getString("description"),
                                 rs.getTimestamp("created_at"));
                return Optional.of(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Tag save(Tag tag) {
        if (tag.getId() == null) {
            // Insert new tag
            String sql = "INSERT INTO tags (id, name, description, created_at) VALUES (?, ?, ?, ?)";
            try (Connection conn = connectionManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                tag.setId(java.util.UUID.randomUUID().toString());
                stmt.setString(1, tag.getId());
                stmt.setString(2, tag.getName());
                stmt.setString(3, tag.getDescription());
                stmt.setTimestamp(4, new Timestamp(tag.getCreatedAt().getTime()));
                stmt.executeUpdate();
                return tag;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to save tag", e);
            }
        } else {
            // Update existing tag - not required by sequence diagram, simplified.
            throw new UnsupportedOperationException("Update not implemented");
        }
    }

    @Override
    public boolean existsByName(String name) {
        String sql = "SELECT COUNT(*) AS count FROM tags WHERE name = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}