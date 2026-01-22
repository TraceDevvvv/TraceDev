import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * ValidationUtils -      
 *              ，        、      、        。
 *          ，             。
 */
public class ValidationUtils {
    
    //       ，          
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     *             
     *   MockUI.java          
     * @param justification       
     * @return true      
     */
    public static boolean validateJustification(Justification justification) {
        if (justification == null) {
            System.out.println("  ：          。");
            return false;
        }
        
        //       
        if (justification.getAbsenceRecordId() == null || 
            justification.getAbsenceRecordId().trim().isEmpty()) {
            System.out.println("  ：    ID    。");
            return false;
        }
        
        if (justification.getJustificationDate() == null) {
            System.out.println("  ：        。");
            return false;
        }
        
        if (justification.getReason() == null || 
            justification.getReason().trim().isEmpty()) {
            System.out.println("  ：        。");
            return false;
        }
        
        if (justification.getAdminId() == null || 
            justification.getAdminId().trim().isEmpty()) {
            System.out.println("  ：   ID    。");
            return false;
        }
        
        //        
        LocalDate today = LocalDate.now();
        LocalDate justificationDate = justification.getJustificationDate();
        
        if (justificationDate.isAfter(today)) {
            System.out.println("  ：           。");
            //       false      ，       ，        
        }
        
        //         
        String reason = justification.getReason();
        if (reason.trim().length() < 5) {
            System.out.println("  ：      ，    5   。");
            return false;
        }
        
        if (reason.trim().length() > 500) {
            System.out.println("  ：      ，  500   。");
            return false;
        }
        
        //            
        if (containsInvalidContent(reason)) {
            System.out.println("  ：            。");
        }
        
        return true;
    }
    
    /**
     *              
     * @param dateString      
     * @return true      
     */
    public static boolean isValidDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return false;
        }
        
        try {
            LocalDate.parse(dateString, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     *             
     *        1   30 
     * @param date       
     * @return true      
     */
    public static boolean isValidDateRange(LocalDate date) {
        if (date == null) {
            return false;
        }
        
        LocalDate today = LocalDate.now();
        LocalDate minValidDate = today.minusYears(1); //           
        LocalDate maxValidDate = today.plusDays(30); //     30     
        
        return !date.isBefore(minValidDate) && !date.isAfter(maxValidDate);
    }
    
    /**
     *             
     * @param reason     
     * @return true      
     */
    public static boolean isValidReason(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            return false;
        }
        
        String trimmedReason = reason.trim();
        
        //     
        if (trimmedReason.length() < 5 || trimmedReason.length() > 500) {
            return false;
        }
        
        //          ，      
        if (trimmedReason.matches("^[\\p{P}\\s]+$")) {
            return false;
        }
        
        return true;
    }
    
    /**
     *      ID    
     * @param adminId    ID
     * @return true  ID  
     */
    public static boolean isValidAdminId(String adminId) {
        if (adminId == null || adminId.trim().isEmpty()) {
            return false;
        }
        
        //   ID  
        return adminId.matches("^[a-zA-Z0-9_-]+$");
    }
    
    /**
     *       ID    
     * @param absenceRecordId     ID
     * @return true  ID  
     */
    public static boolean isValidAbsenceRecordId(String absenceRecordId) {
        if (absenceRecordId == null || absenceRecordId.trim().isEmpty()) {
            return false;
        }
        
        //   ID  
        return absenceRecordId.matches("^[a-zA-Z0-9_-]+$");
    }
    
    /**
     *                 
     * @param reason     
     * @return true      
     */
    public static boolean isValidReasonLength(String reason) {
        if (reason == null) {
            return false;
        }
        
        int length = reason.trim().length();
        return length >= 5 && length <= 500;
    }
    
    /**
     *                 
     * @param reason     
     * @return true        
     */
    public static boolean containsInvalidContent(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            return false;
        }
        
        String lowerReason = reason.toLowerCase().trim();
        
        //               
        if (lowerReason.matches("^[\\p{P}\\s]+$")) {
            return true;
        }
        
        //             
        String[] placeholders = {
            "test", "  ", "  ", "example", 
            "placeholder", "xxx", "aaa", "asdf",
            "qwerty", "12345", "  "
        };
        
        for (String placeholder : placeholders) {
            if (lowerReason.contains(placeholder)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     *          
     * @param date       
     * @return true       
     */
    public static boolean isFutureDate(LocalDate date) {
        if (date == null) {
            return false;
        }
        
        return date.isAfter(LocalDate.now());
    }
    
    /**
     *          
     * @param date       
     * @return true       
     */
    public static boolean isPastDate(LocalDate date) {
        if (date == null) {
            return false;
        }
        
        return date.isBefore(LocalDate.now());
    }
    
    /**
     *          
     * @param date       
     * @return true     
     */
    public static boolean isToday(LocalDate date) {
        if (date == null) {
            return false;
        }
        
        return date.isEqual(LocalDate.now());
    }
    
    /**
     *            
     * @param justification       
     * @return       
     */
    public static String getValidationDetails(Justification justification) {
        if (justification == null) {
            return "  ：          。";
        }
        
        StringBuilder details = new StringBuilder();
        details.append("        ：\n");
        
        boolean isValid = true;
        
        //       ID
        if (!isValidAbsenceRecordId(justification.getAbsenceRecordId())) {
            details.append("❌     ID     \n");
            isValid = false;
        } else {
            details.append("✓     ID  \n");
        }
        
        //       
        if (justification.getJustificationDate() == null) {
            details.append("❌       \n");
            isValid = false;
        } else if (!isValidDateRange(justification.getJustificationDate())) {
            details.append("❌           \n");
            isValid = false;
        } else {
            details.append("✓       \n");
        }
        
        //       
        if (!isValidReason(justification.getReason())) {
            details.append("❌       \n");
            isValid = false;
        } else {
            details.append("✓       \n");
        }
        
        //      ID
        if (!isValidAdminId(justification.getAdminId())) {
            details.append("❌    ID  \n");
            isValid = false;
        } else {
            details.append("✓    ID  \n");
        }
        
        if (isValid) {
            details.append("\n✅       ");
        } else {
            details.append("\n❌       ");
        }
        
        return details.toString();
    }
    
    /**
     *      
     *              
     */
    public static class ValidationResult {
        private boolean isValid;
        private StringBuilder errorMessages;
        
        /**
         *     
         */
        public ValidationResult() {
            this.isValid = true;
            this.errorMessages = new StringBuilder();
        }
        
        /**
         *       
         * @param error     
         */
        public void addError(String error) {
            if (this.isValid) {
                this.isValid = false;
            }
            this.errorMessages.append("• ").append(error).append("\n");
        }
        
        /**
         *         
         * @return true      
         */
        public boolean isValid() {
            return this.isValid;
        }
        
        /**
         *         
         * @return        
         */
        public String getErrorMessages() {
            return this.errorMessages.toString();
        }
        
        /**
         *         
         * @return       
         */
        public String getSummary() {
            if (this.isValid) {
                return "✅       。";
            } else {
                return "❌     ：\n" + getErrorMessages();
            }
        }
    }
    
    /**
     *              
     * @param justification       
     * @return          
     */
    public static ValidationResult performCompleteValidation(Justification justification) {
        ValidationResult result = new ValidationResult();
        
        if (justification == null) {
            result.addError("          ");
            return result;
        }
        
        //       ID
        if (!isValidAbsenceRecordId(justification.getAbsenceRecordId())) {
            result.addError("    ID     ");
        }
        
        //       
        if (justification.getJustificationDate() == null) {
            result.addError("      ");
        } else if (!isValidDateRange(justification.getJustificationDate())) {
            result.addError("          （       1   30 ）");
        }
        
        //       
        if (!isValidReason(justification.getReason())) {
            result.addError("      （    5-500           ）");
        }
        
        //      ID
        if (!isValidAdminId(justification.getAdminId())) {
            result.addError("   ID     ");
        }
        
        return result;
    }
}