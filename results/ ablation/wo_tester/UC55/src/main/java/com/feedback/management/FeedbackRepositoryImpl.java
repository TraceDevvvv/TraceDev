package com.feedback.management;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRepositoryImpl implements FeedbackRepository {
    private DataSource dataSource;
    private List<Feedback> feedbackList = new ArrayList<>();

    public FeedbackRepositoryImpl() {
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Feedback> findBySiteAndUser(String siteId, String userId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            if (feedback.getSiteId().equals(siteId) && feedback.getUserId().equals(userId)) {
                result.add(feedback);
            }
        }
        return result;
    }

    @Override
    public void save(Feedback feedback) {
        feedbackList.add(feedback);
    }
}