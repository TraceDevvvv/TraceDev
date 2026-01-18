package com.system;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing ArchiveEntry entities.
 */
public class ArchiveRepository implements IRepository<ArchiveEntry> {
    // In-memory storage for demonstration.
    private List<ArchiveEntry> entries = new ArrayList<>();

    public ArchiveRepository() {
        // Populate with sample data.
        entries.add(new ArchiveEntry("A001", "S001", new Date(), true));
        entries.add(new ArchiveEntry("A002", "S002", new Date(), false));
        entries.add(new ArchiveEntry("A003", "S003", new Date(), true));
    }

    /**
     * Finds a late entry for a specific student and date.
     * @param studentId the student ID.
     * @param date the date.
     * @return the archive entry if found and late, else null.
     */
    public ArchiveEntry findLateEntry(String studentId, Date date) {
        System.out.println("ArchiveRepository: Finding late entry for student " + studentId + " on " + date);
        for (ArchiveEntry e : entries) {
            if (e.getStudentId().equals(studentId) && e.getIsLate()) {
                // Simplified date comparison: assume same day.
                return e;
            }
        }
        return null;
    }

    /**
     * Deletes a late entry from the archive.
     * @param entry the entry to delete.
     */
    public void deleteLateEntry(ArchiveEntry entry) {
        entries.remove(entry);
        System.out.println("ArchiveRepository: Deleted late entry " + entry.getEntryId());
    }

    @Override
    public void save(ArchiveEntry entity) {
        // Update existing or add new.
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getEntryId().equals(entity.getEntryId())) {
                entries.set(i, entity);
                System.out.println("ArchiveRepository: Updated entry " + entity.getEntryId());
                return;
            }
        }
        entries.add(entity);
        System.out.println("ArchiveRepository: Added new entry " + entity.getEntryId());
    }

    @Override
    public void delete(ArchiveEntry entity) {
        entries.remove(entity);
        System.out.println("ArchiveRepository: Deleted entry " + entity.getEntryId());
    }

    @Override
    public ArchiveEntry findById(String id) {
        return entries.stream()
                .filter(e -> e.getEntryId().equals(id))
                .findFirst()
                .orElse(null);
    }
}