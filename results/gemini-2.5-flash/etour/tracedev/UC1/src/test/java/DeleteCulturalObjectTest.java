// File: DeleteCulturalObjectTest.java
package com.example.test;

import com.example.agency.presenter.DeleteCulturalObjectPresenter;
import com.example.agency.view.AgencyOperatorView;
import com.example.repository.CulturalObjectRepository;
import com.example.usecase.DeleteCulturalObjectUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteCulturalObjectTest {

    @Mock
    private CulturalObjectRepository repository;

    @Mock
    private AgencyOperatorView view;

    private DeleteCulturalObjectPresenter presenter;

    @BeforeEach
    void setUp() {
        //    UseCase   Presenter
        DeleteCulturalObjectUseCase useCase = new DeleteCulturalObjectUseCase(repository);
        presenter = new DeleteCulturalObjectPresenter(view, useCase);
    }

    @Test
    @DisplayName("    ：             ")
    void testDeleteFlow_Successful() {
        // 1.     
        String objectId = "CO001";

        // 2.      (Mock Behavior)
        //   View       ，      “ ”(   true)
        when(view.showConfirmationDialog(anyString())).thenReturn(true);
        //   Repository      ，          (   true)
        when(repository.delete(objectId)).thenReturn(true);

        // 3.      (Act)
        presenter.requestDelete(objectId);

        // 4.         (Assert)
        InOrder inOrder = inOrder(view, repository);

        //      1:       
        inOrder.verify(view).showConfirmationDialog(contains(objectId));
        
        //      2:         ，    UI   
        inOrder.verify(view).blockInputControls();
        
        //      3:              (Remote Service)
        inOrder.verify(repository).delete(objectId);
        
        //      4:    “  ”  ，       
        inOrder.verify(view).showSuccessMessage(contains("successfully"));
        
        //      5:     UI   
        inOrder.verify(view).unblockInputControls();
    }
}