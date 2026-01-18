package com.example;

import java.util.List;

/**
 * Presenter interface for register details.
 */
public interface IRegisterDetailsPresenter {
    RegisterDetailsDTO presentDetails(ClassRegisterDTO registerDTO, List<RecordByDateDTO> organizedRecords);
    void presentError(String errorMessage);
}