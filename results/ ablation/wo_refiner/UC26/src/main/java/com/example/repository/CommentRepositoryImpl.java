
package com.example.repository;

import com.example.entities.Comment;
import java.sql.ResultSet;
import java.util.Optional;

/**
 * Implementation of CommentRepository using a Database.
 */
public class CommentRepositoryImpl implements CommentRepository {
    private java.sql.Connection dataSource;

    public CommentRepositoryImpl(java.sql.Connection dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Comment> findById(int commentId) {
        try {
            String sql = "SELECT * FROM comments WHERE id = " + commentId;
            java.sql.Statement stmt = dataSource.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Comment comment = new Comment(
                    rs.getInt("id"),
                    rs.getString("content"),
                    rs.getInt("feedback_id")
                );
                return Optional.of(comment);
            }
        } catch (Exception e) {
            System.err.println("Error finding comment: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Comment comment) {
        try {
            String sql = String.format(
                "UPDATE comments SET content = '%s' WHERE id = %d",
                comment.getContent().replace("'", "''"),
                comment.getId()
            );
            java.sql.Statement stmt = dataSource.createStatement();
            int rows = stmt.executeUpdate(sql);
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Error updating comment: " + e.getMessage());
            return false;
        }
    }
}
