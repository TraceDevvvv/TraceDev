package repository;

import domain.Teacher;

/**
 * Interface for Data Access operations related to Teacher entities.
 */
public interface ITeacherRepository {
    /**
     * Retrieves a Teacher by its unique identifier.
     * @param teacherId The ID of the teacher to retrieve.
     * @return The Teacher object if found, null otherwise.
     */
    Teacher getTeacherById(String teacherId);
}