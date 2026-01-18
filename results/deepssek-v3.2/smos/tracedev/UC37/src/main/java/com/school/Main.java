package com.school;

import com.school.application.CreateDisciplinaryNoteUseCase;
import com.school.auth.Administrator;
import com.school.auth.AuthenticationService;
import com.school.auth.Session;
import com.school.domain.*;
import com.school.infrastructure.*;
import com.school.smos.SMOSConnection;
import com.school.smos.SMOSServer;
import com.school.ui.DisciplinaryNoteController;
import com.school.ui.RegistryScreen;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class for demonstration and integration.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== School Disciplinary Note System ===");

        // Setup infrastructure
        SMOSServer smosServer = new SMOSServer("smos.example.com", 8080);
        SMOSConnection smosConnection = smosServer.connect();

        SmtpClient smtpClient = new SmtpClient("smtp.example.com", 587, smosConnection);
        EmailNotificationService notificationService = new EmailNotificationService(smtpClient);
        AuthenticationService authService = new AuthenticationService();

        // Create repositories
        Map<String, Student> studentMap = new HashMap<>();
        studentMap.put("STU001", new Student("STU001", "John Doe", "john.doe@example.com"));
        StudentRepository studentRepo = new InMemoryStudentRepository(studentMap);

        Map<String, Teacher> teacherMap = new HashMap<>();
        teacherMap.put("TCH001", new Teacher("TCH001", "Jane Smith"));
        TeacherRepository teacherRepo = new InMemoryTeacherRepository(teacherMap);

        Map<String, DisciplinaryNote> noteMap = new HashMap<>();
        DisciplinaryNoteRepository noteRepo = new InMemoryDisciplinaryNoteRepository(noteMap);

        // Build use case
        CreateDisciplinaryNoteUseCase useCase = new CreateDisciplinaryNoteUseCase(
                noteRepo, studentRepo, teacherRepo, notificationService
        );

        // Build controller
        DisciplinaryNoteController controller = new DisciplinaryNoteController(useCase, authService);
        RegistryScreen screen = new RegistryScreen(controller);

        // Simulate login
        Session session = authService.login("admin", "password");
        System.out.println("Logged in as: " + session.getUser().getUsername());

        // Simulate UI flow
        screen.showRegistryScreen();
        screen.onNewNoteButtonClick();

        // Simulate SMOS interruption
        Administrator admin = new Administrator("admin");
        System.out.println("Administrator " + admin.getUsername() + " interrupts connection.");
        smosConnection.interrupt();

        // End
        System.out.println("=== End ===");
    }

    static class InMemoryStudentRepository implements StudentRepository {
        private Map<String, Student> map;

        InMemoryStudentRepository(Map<String, Student> map) {
            this.map = map;
        }

        @Override
        public Student findById(String id) {
            return map.get(id);
        }
    }

    static class InMemoryTeacherRepository implements TeacherRepository {
        private Map<String, Teacher> map;

        InMemoryTeacherRepository(Map<String, Teacher> map) {
            this.map = map;
        }

        @Override
        public Teacher findById(String id) {
            return map.get(id);
        }
    }

    static class InMemoryDisciplinaryNoteRepository implements DisciplinaryNoteRepository {
        private Map<String, DisciplinaryNote> map;

        InMemoryDisciplinaryNoteRepository(Map<String, DisciplinaryNote> map) {
            this.map = map;
        }

        @Override
        public void save(DisciplinaryNote note) {
            map.put(note.getNoteId(), note);
            System.out.println("Saved note: " + note.getNoteId());
        }

        @Override
        public DisciplinaryNote findById(String id) {
            return map.get(id);
        }
    }
}