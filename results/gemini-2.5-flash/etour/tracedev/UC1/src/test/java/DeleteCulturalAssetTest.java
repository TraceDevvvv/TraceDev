
import com.example.agency.presenter.DeleteCulturalObjectPresenter;
import com.example.agency.view.AgencyOperatorView;
import com.example.repository.CulturalObjectRepository;
import com.example.usecase.DeleteCulturalObjectUseCase;
import com.example.domain.CulturalObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class DeleteCulturalAssetTest {

    @Mock
    private AgencyOperatorView mockView;

    @Mock
    private CulturalObjectRepository mockRepository;

    private DeleteCulturalObjectPresenter presenter;
    private DeleteCulturalObjectUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new DeleteCulturalObjectUseCase(mockRepository);
        presenter = new DeleteCulturalObjectPresenter(mockView, useCase);
    }

    @Test
    void testDeleteBeneCulturaleWhenConfirmed() {
        // Given: A cultural object with ID "CO001" and name "Ancient Vase"
        CulturalObject culturalObject = new CulturalObject("CO001", "Ancient Vase");
        String objectId = "CO001";

        // Given: The repository returns the object when findById is called
        when(mockRepository.findById(objectId)).thenReturn(culturalObject);

        // Given: User confirms deletion in the dialog (simulated)
        when(mockView.showConfirmationDialog(anyString())).thenReturn(true);

        // Given: The repository successfully deletes the object
        when(mockRepository.delete(objectId)).thenReturn(true);

        // Given: The list of all cultural objects (before deletion)
        List<CulturalObject> initialObjects = Arrays.asList(culturalObject);
        doNothing().when(mockView).displayCulturalObjects(eq(initialObjects));

        // When: The Agency Operator selects the cultural object (simulates UI action)
        // This triggers the presenter's requestDelete method
        presenter.requestDelete(objectId);

        // Then: Verify that confirmation dialog was shown
        verify(mockView).showConfirmationDialog(anyString());

        // Then: Verify that repository delete was called with correct ID
        verify(mockRepository).delete(objectId);

        // Then: Verify that success message was shown to the user
        verify(mockView).showSuccessMessage(anyString());
    }
}
