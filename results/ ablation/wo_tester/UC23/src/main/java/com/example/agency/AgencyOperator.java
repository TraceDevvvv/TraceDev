package com.example.agency;

/**
 * Represents an agency operator who can edit news.
 */
public class AgencyOperator {
    private String userId;
    private String name;
    private boolean loggedIn;

    public AgencyOperator(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.loggedIn = false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    // 1. activate editing functionality
    public void activateEditingFunctionality(ListView listView) {
        System.out.println("Activating editing functionality for operator: " + name);
        listView.setCurrentOperator(this);
        listView.activateEditingFunctionality();
    }

    // 2. view all news
    public void viewAllNews(ListView listView) {
        System.out.println("Operator viewing all news.");
        listView.viewAllNews();
    }

    // 3. select news (newsId)
    public void selectNews(int newsId, ListView listView) {
        System.out.println("Operator selecting news with ID: " + newsId);
        listView.selectNews(newsId);
    }

    // 4-6. display form with news data
    public void displayFormWithNewsData(EditView editView) {
        System.out.println("Operator displaying form with news data.");
        editView.displayFormWithNewsData();
    }

    // 7. change data in form
    public void changeDataInForm(EditView editView) {
        System.out.println("Operator changing data in form.");
        editView.changeDataInForm();
    }

    // 8. submit form
    public void submitForm(EditView editView) {
        System.out.println("Operator submitting form.");
        editView.submitForm();
    }

    // 11. confirm operation
    public void confirmOperation(EditView editView) {
        System.out.println("Operator confirming operation.");
        editView.confirmOperation();
    }

    // cancel editing
    public void cancelEditing(EditView editView) {
        System.out.println("Operator cancelling editing.");
        editView.cancelEditing();
    }
}