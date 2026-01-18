package com.example.schoolreports;

import java.util.List;

/**
 * Concrete implementation of IReportRepository that interacts with the SMOSGateway.
 * This class acts as an adapter, translating requests to the SMOSGateway and passing
 * back the raw data.
 */
public class SMOSReportRepository implements IReportRepository {

    private SMOSGateway smosGateway;

    /**
     * Constructs a new SMOSReportRepository with a dependency on SMOSGateway.
     *
     * @param smosGateway The gateway used to communicate with the SMOS system.
     */
    public SMOSReportRepository(SMOSGateway smosGateway) {
        this.smosGateway = smosGateway;
    }

    /**
     * {@inheritDoc}
     * Delegates the request to the SMOSGateway to fetch raw student reports.
     */
    @Override
    public List<SMOSRawReportData> fetchStudentReports(String parentId) throws SMOSConnectionException {
        System.out.println("SMOSReportRepository: Fetching student reports for parent: " + parentId);
        // Delegate call to SMOSGateway
        return smosGateway.requestReportsFromSMOS(parentId);
    }

    /**
     * {@inheritDoc}
     * Delegates the request to the SMOSGateway to fetch raw report card details.
     */
    @Override
    public SMOSRawReportCardData fetchReportCardDetails(String reportCardId) throws SMOSConnectionException {
        System.out.println("SMOSReportRepository: Fetching report card details for ID: " + reportCardId);
        // Delegate call to SMOSGateway
        return smosGateway.requestReportDetailsFromSMOS(reportCardId);
    }
}