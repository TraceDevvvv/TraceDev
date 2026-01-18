package com.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Interactor implementing the use case.
 * Traceability: System organizes the information by date.
 */
public class RegisterDetailsInteractor implements IRegisterDetailsUseCase {
    private IClassRegisterRepository registerRepository;
    private RegisterDetailsPresenter presenter;

    public RegisterDetailsInteractor(IClassRegisterRepository registerRepository, RegisterDetailsPresenter presenter) {
        this.registerRepository = registerRepository;
        this.presenter = presenter;
    }

    @Override
    public RegisterDetailsDTO getRegisterDetails(String registerId) throws ConnectionException {
        ClassRegister register = registerRepository.findRegisterById(registerId);
        ClassRegisterDTO registerDTO = register.getRegisterData();
        List<RecordByDate> records = register.getRecords();
        // Organize records by date.
        List<RecordByDate> sortedRecords = sortRecordsByDate(records);
        // Convert to DTOs (simplified).
        List<RecordByDateDTO> organizedRecords = Collections.emptyList(); // In reality, map each RecordByDate to DTO.
        return presenter.presentDetails(registerDTO, organizedRecords);
    }

    public List<RecordByDate> sortRecordsByDate(List<RecordByDate> records) {
        // Sort records by date in ascending order.
        records.sort(Comparator.comparing(RecordByDate::getRecordDate));
        return records;
    }
}