package com.agency.modifycomment;

import com.agency.modifycomment.controller.ModifyCommentController;
import com.agency.modifycomment.repository.CommentRepository;
import com.agency.modifycomment.repository.FeedbackRepository;
import com.agency.modifycomment.repository.SiteRepository;
import com.agency.modifycomment.service.CommentService;
import com.agency.modifycomment.service.FeedbackService;
import com.agency.modifycomment.service.SiteService;

import java.util.Scanner;

/**
 * Main class to run the ModifyComment application.
 * This class initializes the repositories, serv, and controller,
 * and then starts the application flow.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize repositories (using in-memory implementations for this example)
        SiteRepository siteRepository = new SiteRepository();
        FeedbackRepository feedbackRepository = new FeedbackRepository();
        CommentRepository commentRepository = new CommentRepository();

        // Initialize serv with their respective repositories
        SiteService siteService = new SiteService(siteRepository);
        FeedbackService feedbackService = new FeedbackService(feedbackRepository);
        CommentService commentService = new CommentService(commentRepository);

        // Initialize the controller with the serv
        ModifyCommentController controller = new ModifyCommentController(siteService, feedbackService, commentService);

        // Use a Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Start the application flow by calling the controller's main method
        controller.start(scanner);

        // Close the scanner when the application exits
        scanner.close();
    }
}