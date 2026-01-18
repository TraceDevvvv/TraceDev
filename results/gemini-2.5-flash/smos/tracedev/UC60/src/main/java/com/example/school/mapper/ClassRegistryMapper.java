package com.example.school.mapper;

import com.example.school.model.ClassRegistryDTO;
import com.example.school.model.ClassRegistryEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A utility class for mapping between {@link ClassRegistryEntry} (data layer)
 * and {@link ClassRegistryDTO} (presentation layer).
 * This class uses static methods to perform the mapping, adhering to requirement FE-2.
 */
public class ClassRegistryMapper {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Converts a single {@link ClassRegistryEntry} object to a {@link ClassRegistryDTO} object.
     *
     * @param entry The ClassRegistryEntry to convert.
     * @return A new ClassRegistryDTO with data mapped from the entry.
     */
    public static ClassRegistryDTO toDTO(ClassRegistryEntry entry) {
        if (entry == null) {
            return null;
        }
        System.out.println("ClassRegistryMapper: Mapping ClassRegistryEntry ID " + entry.getId() + " to ClassRegistryDTO.");
        return new ClassRegistryDTO(
                DATE_FORMAT.format(entry.getDate()),
                entry.getAbsences(),
                entry.getDisciplinaryNotes(),
                entry.getDelays(),
                entry.justification // justification is public in ClassRegistryEntry
        );
    }

    /**
     * Converts a list of {@link ClassRegistryEntry} objects to a list of {@link ClassRegistryDTO} objects.
     *
     * @param entries The list of ClassRegistryEntry objects to convert.
     * @return A new list of ClassRegistryDTOs. Returns an empty list if the input list is null or empty.
     */
    public static List<ClassRegistryDTO> toDTOs(List<ClassRegistryEntry> entries) {
        if (entries == null || entries.isEmpty()) {
            System.out.println("ClassRegistryMapper: No ClassRegistryEntries to map to DTOs.");
            return List.of(); // Returns an immutable empty list
        }
        System.out.println("ClassRegistryMapper: Mapping " + entries.size() + " ClassRegistryEntries to DTOs.");
        return entries.stream()
                .map(ClassRegistryMapper::toDTO)
                .collect(Collectors.toList());
    }
}