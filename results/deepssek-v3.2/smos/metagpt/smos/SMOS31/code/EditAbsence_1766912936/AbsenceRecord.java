import java.time.LocalDate;

/**
 * AbsenceRecord  -          
 *               
 */
public class AbsenceRecord {
    private String recordId;
    private String studentId;
    private String studentName;
    private LocalDate date;
    private boolean isAbsent;
    private String reason;
    private LocalDate lastModified;
    
    /**
     *      -         
     * @param recordId   ID
     * @param studentId   ID
     * @param studentName     
     * @param date     
     * @param isAbsent     
     * @param reason     
     */
    public AbsenceRecord(String recordId, String studentId, String studentName, 
                        LocalDate date, boolean isAbsent, String reason) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.isAbsent = isAbsent;
        this.reason = reason;
        this.lastModified = LocalDate.now();
    }
    
    /**
     *           -        
     *       ，      （     ）
     * @param newAbsenceStatus       （true=  ，false=  ）
     * @param reason     （       ）
     * @return       
     */
    public boolean editAbsence(boolean newAbsenceStatus, String reason) {
        //             
        if (this.isAbsent == newAbsenceStatus) {
            System.out.println("  ：       （  " + (newAbsenceStatus ? "  " : "  ") + "）。");
            return false;
        }
        
        //       
        boolean oldStatus = this.isAbsent;
        this.isAbsent = newAbsenceStatus;
        
        //         ，    ；      
        if (newAbsenceStatus) {
            this.reason = (reason != null && !reason.trim().isEmpty()) ? reason : "     ";
        } else {
            this.reason = null;
        }
        
        //         
        this.lastModified = LocalDate.now();
        
        String action = newAbsenceStatus ? "  " : "  ";
        System.out.println("       " + action + "：");
        System.out.println("    ：" + studentName);
        System.out.println("    ：" + date);
        System.out.println("    ：" + (oldStatus ? "  " : "  ") + " → " + (newAbsenceStatus ? "  " : "  "));
        
        return true;
    }
    
    /**
     *            
     * @param targetDate     
     * @return          
     */
    public boolean isForDate(LocalDate targetDate) {
        return this.date.equals(targetDate);
    }
    
    /**
     *           
     * @return       
     */
    public boolean validate() {
        if (recordId == null || recordId.trim().isEmpty()) {
            System.out.println("  ：  ID    。");
            return false;
        }
        
        if (studentId == null || studentId.trim().isEmpty()) {
            System.out.println("  ：  ID    。");
            return false;
        }
        
        if (date == null) {
            System.out.println("  ：        。");
            return false;
        }
        
        if (date.isAfter(LocalDate.now())) {
            System.out.println("  ：           。");
            return false;
        }
        
        //        ，     ，          
        if (isAbsent && (reason == null || reason.trim().isEmpty())) {
            System.out.println("  ：        。");
        }
        
        return true;
    }
    
    // Getter Setter  
    
    public String getRecordId() {
        return recordId;
    }
    
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public boolean isAbsent() {
        return isAbsent;
    }
    
    public void setAbsent(boolean absent) {
        this.isAbsent = absent;
        if (!absent) {
            this.reason = null; //       ，    
        }
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public LocalDate getLastModified() {
        return lastModified;
    }
    
    /**
     *         
     * @return          
     */
    public String getSummary() {
        return String.format("  ID: %s |   : %s (%s) |   : %s |   : %s |     : %s",
                           recordId, studentName, studentId, date,
                           isAbsent ? "  " : "  ", lastModified);
    }
    
    @Override
    public String toString() {
        return String.format("AbsenceRecord{recordId='%s', studentId='%s', studentName='%s', date=%s, isAbsent=%s, reason='%s', lastModified=%s}",
                           recordId, studentId, studentName, date, isAbsent, reason, lastModified);
    }
}