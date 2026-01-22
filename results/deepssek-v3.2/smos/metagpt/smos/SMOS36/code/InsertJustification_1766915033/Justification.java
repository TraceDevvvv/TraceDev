import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Justification -          
 *           ，             。
 *      JavaBean  ，  getter setter  。
 */
public class Justification {
    
    //         ，           
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    //        
    private String justificationId;          //     ID（     ）
    private String absenceRecordId;          //     ID（           ）
    private LocalDate justificationDate;     //     
    private String reason;                   //       
    private String adminId;                  //           ID
    private LocalDate createdAt;             //     
    private LocalDate updatedAt;             //     
    private String status;                   //   （ ：   、   、    ）
    
    /**
     *       
     *       
     */
    public Justification() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.status = "   ";
        //        ID（             ID    ）
        this.justificationId = "JUST_" + System.currentTimeMillis();
    }
    
    /**
     *         
     * @param absenceRecordId     ID
     * @param justificationDate     
     * @param reason     
     * @param adminId    ID
     */
    public Justification(String absenceRecordId, LocalDate justificationDate, 
                         String reason, String adminId) {
        this();
        this.absenceRecordId = absenceRecordId;
        this.justificationDate = justificationDate;
        this.reason = reason;
        this.adminId = adminId;
    }
    
    /**
     *           
     * @param justificationId     ID
     * @param absenceRecordId     ID
     * @param justificationDate     
     * @param reason     
     * @param adminId    ID
     * @param createdAt     
     * @param updatedAt     
     * @param status   
     */
    public Justification(String justificationId, String absenceRecordId, 
                         LocalDate justificationDate, String reason, 
                         String adminId, LocalDate createdAt, 
                         LocalDate updatedAt, String status) {
        this.justificationId = justificationId;
        this.absenceRecordId = absenceRecordId;
        this.justificationDate = justificationDate;
        this.reason = reason;
        this.adminId = adminId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
    
    // Getter   Setter   
    
    /**
     *       ID
     * @return     ID
     */
    public String getJustificationId() {
        return justificationId;
    }
    
    /**
     *       ID
     * @param justificationId     ID
     */
    public void setJustificationId(String justificationId) {
        this.justificationId = justificationId;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     *       ID
     * @return     ID
     */
    public String getAbsenceRecordId() {
        return absenceRecordId;
    }
    
    /**
     *       ID
     * @param absenceRecordId     ID
     */
    public void setAbsenceRecordId(String absenceRecordId) {
        this.absenceRecordId = absenceRecordId;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     *       
     * @return     
     */
    public LocalDate getJustificationDate() {
        return justificationDate;
    }
    
    /**
     *       
     * @param justificationDate     
     */
    public void setJustificationDate(LocalDate justificationDate) {
        this.justificationDate = justificationDate;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     *         
     * @return       
     */
    public String getReason() {
        return reason;
    }
    
    /**
     *         
     * @param reason       
     */
    public void setReason(String reason) {
        if (reason != null && reason.length() > 500) {
            //       ，   500  
            this.reason = reason.substring(0, 500);
        } else {
            this.reason = reason;
        }
        this.updatedAt = LocalDate.now();
    }
    
    /**
     *      ID
     * @return    ID
     */
    public String getAdminId() {
        return adminId;
    }
    
    /**
     *      ID
     * @param adminId    ID
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     *       
     * @return     
     */
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    
    /**
     *       
     * @param createdAt     
     */
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     *       
     * @return     
     */
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
    
    /**
     *       
     * @param updatedAt     
     */
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    /**
     *         
     * @return   
     */
    public String getStatus() {
        return status;
    }
    
    /**
     *         
     * @param status   
     */
    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     *             LocalDate  
     * @param dateString      （  ：yyyy-MM-dd）
     * @return     LocalDate  
     * @throws IllegalArgumentException         
     */
    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new IllegalArgumentException("         ");
        }
        return LocalDate.parse(dateString.trim(), DATE_FORMATTER);
    }
    
    /**
     *  LocalDate         
     * @param date        
     * @return           （  ：yyyy-MM-dd）
     */
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(DATE_FORMATTER);
    }
    
    /**
     *             
     * @return true      
     */
    public boolean isValid() {
        return absenceRecordId != null && !absenceRecordId.trim().isEmpty() &&
               justificationDate != null &&
               reason != null && !reason.trim().isEmpty() &&
               adminId != null && !adminId.trim().isEmpty();
    }
    
    /**
     *              
     * @return        
     */
    @Override
    public String toString() {
        return "      ：" +
               "\n  ID: " + justificationId +
               "\n      ID: " + absenceRecordId +
               "\n      : " + formatDate(justificationDate) +
               "\n      : " + (reason != null && reason.length() > 50 ? 
                                  reason.substring(0, 50) + "..." : reason) +
               "\n     ID: " + adminId +
               "\n      : " + formatDate(createdAt) +
               "\n      : " + formatDate(updatedAt) +
               "\n    : " + status;
    }
    
    /**
     *          
     * @return             
     */
    public String toDetailedString() {
        return "        ：" +
               "\n  ID: " + justificationId +
               "\n      ID: " + absenceRecordId +
               "\n      : " + formatDate(justificationDate) +
               "\n      : " + reason +
               "\n     ID: " + adminId +
               "\n      : " + formatDate(createdAt) +
               "\n      : " + formatDate(updatedAt) +
               "\n    : " + status;
    }
}