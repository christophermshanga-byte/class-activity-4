public class Patient extends Person {
    private String id;
    private String diagnosis;
    private String recordId;
    
    public Patient(String id, String name, int age, String diagnosis, String recordId) {
        super(name, age);
        this.id = id;
        this.diagnosis = diagnosis;
        this.recordId = recordId;
    }
    
    public String getId() { return id; }
    public String getDiagnosis() { return diagnosis; }
    public String getRecordId() { return recordId; }
    
    public void showInfo() {
        System.out.println("Patient: " + getName() + " (ID: " + id + ", Age: " + getAge() + 
                          ", Diagnosis: " + diagnosis + ", Record: " + recordId + ")");
    }
    
    public void displayInfo() {
        showInfo();
    }
    
    public void generateBill() {
        System.out.println("Patient bill: $500 (Consultation + Treatment)");
    }
    
    public String toFileString() {
        return "PATIENT|" + id + "|" + getName() + "|" + getAge() + "|" + diagnosis + "|" + recordId;
    }
}
