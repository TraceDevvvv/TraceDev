package com.school.application;

import java.util.Date;

/**
 * Command interface for creating a disciplinary note.
 */
public interface CreateDisciplinaryNoteCommand {
    String getStudentId();
    Date getDate();
    String getTeacherId();
    String getDescription();
}