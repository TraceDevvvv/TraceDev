package com.example.smos;

/**
 * Application Layer Service for managing teaching-related business logic.
 * Orchestrates data access and DTO mapping.
 */
public class TeachingService {
    // Dependency on ITeachingRepository
    private ITeachingRepository teachingRepository;

    /**
     * Constructs a TeachingService with a specific repository instance.
     * @param teachingRepository The data access object for Teaching entities.
     */
    public TeachingService(ITeachingRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }

    /**
     * Retrieves the details of a teaching, converting the domain entity to a DTO.
     * This method implements the application logic path from the sequence diagram.
     *
     * @param teachingId The ID of the teaching to retrieve.
     * @return A TeachingDto containing displayable details of the teaching.
     * @throws ConnectionException Propagates the exception from the repository if connection fails.
     */
    public TeachingDto getTeachingDetails(String teachingId) {
        System.out.println("TeachingService: Getting details for teaching ID: " + teachingId);
        // Call to repository as per sequence diagram
        Teaching teachingEntity = teachingRepository.findById(teachingId);

        if (teachingEntity == null) {
            System.out.println("TeachingService: Teaching with ID " + teachingId + " not found.");
            return null; // Or throw a TeachingNotFoundException
        }

        // Internal mapping as per sequence diagram
        return mapToDto(teachingEntity);
    }

    /**
     * Maps a Teaching domain entity to a TeachingDto.
     * This is an internal helper method as per the sequence diagram.
     *
     * @param teaching The Teaching domain entity.
     * @return A TeachingDto representation.
     */
    private TeachingDto mapToDto(Teaching teaching) {
        System.out.println("TeachingService: Mapping Teaching entity to DTO.");
        // In a real application, teacherId might need to be looked up in a UserService
        // to get the actual teacherName. For this example, we'll assume a direct mapping
        // or a simple placeholder. Let's assume teacherId can be used as teacherName for demo.
        // Or, better, if SMOSDatabase provided a 'teacherName', we should use that.
        // Given the Teaching entity has `teacherId` and the DTO has `teacherName`,
        // let's make an assumption: `teacherId` in `Teaching` maps to `teacherName` in `TeachingDto`
        // by simply using the ID as the name, or having a simple hardcoded mapping.
        // Let's assume a simplified mapping for now.
        String teacherName = "Teacher " + teaching.getTeacherId(); // Placeholder logic
        // If the TeachingRepository passed a teacherName, we'd use it from a more complex Teaching entity.
        // Given `Teaching` has `teacherId` and `TeachingDto` has `teacherName`, the service
        // should resolve `teacherId` to `teacherName`. For this example, we'll use a simple conversion
        // or assume `SMOSDatabase` could provide it (which it currently does in its dummy data).
        // Let's adjust `TeachingRepository` to store the actual teacher name if available in the map.
        // Re-thinking: `Teaching` has `teacherId`, `TeachingDto` has `teacherName`.
        // `mapToDto` is responsible for this conversion.
        // For now, let's just use a dummy 'Teacher [ID]' for the name.
        // The `SMOSDatabase` dummy data actually provides `teacherName`. `TeachingRepository` could extract it
        // and put it into a richer `Teaching` object or pass it directly.
        // Let's ensure `Teaching` gets the teacher's name if available from DB.
        // The current `Teaching` class has `teacherId`. If we want `teacherName` in `TeachingDto`,
        // `TeachingService` would typically fetch this or `TeachingRepository` would enrich `Teaching`.
        // Let's assume for this diagram's simplicity that `teacherId` in `Teaching` is good enough
        // to derive `teacherName` for `TeachingDto` (e.g., "EMP001" -> "Dr. Alice Smith" is a service lookup).
        // For now, let's keep it simple: `teacherName` in DTO is a placeholder derived from `teacherId`.
        // Or, let's modify Teaching to also carry a teacherName if available, making DTO mapping easier.
        // The diagram only shows `teacherId` in `Teaching`.
        // For the sake of realism, `mapToDto` would typically perform a lookup for `teacherName`.
        // Let's assume a simple lookup logic here.
        String actualTeacherName;
        switch (teaching.getTeacherId()) {
            case "EMP001": actualTeacherName = "Dr. Alice Smith"; break;
            case "EMP002": actualTeacherName = "Prof. Bob Johnson"; break;
            default: actualTeacherName = "Unknown Teacher";
        }

        return new TeachingDto(
                teaching.getId(),
                teaching.getName(),
                teaching.getCourseCode(),
                actualTeacherName, // Derived teacher name
                teaching.getSemester()
        );
    }
}