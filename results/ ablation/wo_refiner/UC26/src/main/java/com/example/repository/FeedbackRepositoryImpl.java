package com.example.repository;

import com.example.entities.Comment;
import com.example.entities.Feedback;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Implementation of FeedbackRepository using a Database.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private Connection dataSource;

    public FeedbackRepositoryImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Feedback> findBySiteId(int siteId) {
        List<Feedback> feedbacks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM feedback WHERE site_id = " + siteId;
            Statement stmt = dataSource.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Feedback feedback = new Feedback(
                    rs.getInt("id"),
                    rs.getInt("site_id"),
                    rs.getString("title")
                );
                // Load comments for this feedback
                loadComments(feedback);
                feedbacks.add(feedback);
            }
        } catch (Exception e) {
            System.err.println("Error finding feedbacks by site: " + e.getMessage());
        }
        return feedbacks;
    }

    @Override
    public Optional<Feedback> findById(int feedbackId) {
        try {
            String sql = "SELECT * FROM feedback WHERE id = " + feedbackId;
            Statement stmt = dataSource.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Feedback feedback = new Feedback(
                    rs.getInt("id"),
                    rs.getInt("site_id"),
                    rs.getString("title")
                );
                loadComments(feedback);
                return Optional.of(feedback);
            }
        } catch (Exception e) {
            System.err.println("Error finding feedback: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Comment> findCommentById(int commentId) {
        try {
            String sql = "SELECT * FROM comments WHERE id = " + commentId;
            Statement stmt = dataSource.createStatement();
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

    private void loadComments(Feedback feedback) {
        try {
            String sql = "SELECT * FROM comments WHERE feedback_id = " + feedback.getId();
            Statement stmt = dataSource.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Comment comment = new Comment(
                    rs.getInt("id"),
                    rs.getString("content"),
                    rs.getInt("feedback_id")
                );
                feedback.addComment(comment);
            }
        } catch (Exception e) {
            System.err.println("Error loading comments: " + e.getMessage());
        }
    }
}