package com.example.controller;

import com.example.dto.LogDataDTO;
import com.example.gateway.ServerGateway;
import com.example.view.DelayEntryView;
import java.util.Date;

/**
 * Controller for date selection.
 * Trace link: Date selection from SeveralTetTingloregister use case.
 */
public class DateSelectionController {
    public Date selectedDate;
    private DelayEntryView view;
    private ServerGateway gateway;
    
    public DateSelectionController(DelayEntryView view, ServerGateway gateway) {
        this.view = view;
        this.gateway = gateway;
    }
    
    /**
     * Loads data for the given date and updates the view.
     * @param date the selected date
     */
    public void loadDataForDate(Date date) {
        this.selectedDate = date;
        LogDataDTO data = gateway.fetchLogData(date);
        view.displayData(data);
    }
}