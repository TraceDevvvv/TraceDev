// File: IAddressRepositoryImpl.java
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Dummy implementation for IAddressRepository.
 * Used to simulate database interactions for AddressEntity.
 * Throws SMOSServerException if "fail_address" keyword is used.
 */
class IAddressRepositoryImpl implements IAddressRepository {
    private List<AddressEntity> addresses;

    public IAddressRepositoryImpl() {
        addresses = new ArrayList<>();
        addresses.add(new AddressEntity("A001", "Main Campus", "123 University St", "Campusville"));
        addresses.add(new AddressEntity("A002", "Engineering Building", "456 Tech Ave", "Innovation City"));
        addresses.add(new AddressEntity("A003", "Library", "789 Book Rd", "Campusville"));
    }

    @Override
    public List<AddressEntity> findByKeywords(String keywords) throws SMOSServerException {
        // Simulate a server connection error if specific keyword is used
        if (keywords != null && keywords.toLowerCase().contains("fail_address")) {
            throw new SMOSServerException("Simulated connection error to Address Repository.");
        }

        if (keywords == null || keywords.trim().isEmpty()) {
            return new ArrayList<>(addresses); // Return all if no keywords
        }

        String lowerKeywords = keywords.toLowerCase();
        return addresses.stream()
                .filter(a -> a.name.toLowerCase().contains(lowerKeywords) ||
                             a.street.toLowerCase().contains(lowerKeywords) ||
                             a.city.toLowerCase().contains(lowerKeywords) ||
                             a.id.toLowerCase().contains(lowerKeywords))
                .collect(Collectors.toList());
    }
}