public class Doctor extends Person {
    private String id;
    private String department;
    private double salary;
    private String specialty;
    
    public Doctor(String id, String name, int age, String department, double salary, String specialty) {
        super(name, age);
        this.id = id;
        this.department = department;
        this.salary = salary;
        this.specialty = specialty;
    }
    
    public String getId() { return id; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public String getSpecialty() { return specialty; }
    
    public void showInfo() {
        System.out.println("Doctor: " + getName() + " (ID: " + id + ", Age: " + getAge() + 
                          ", Dept: " + department + ", Specialty: " + specialty + ", Salary: $" + salary + ")");
    }
    
    public void displayInfo() {
        showInfo();
    }
    
    public void generateBill() {
        System.out.println("Doctor consultation fee: $200");
    }
    
    public String toFileString() {
        return "DOCTOR|" + id + "|" + getName() + "|" + getAge() + "|" + department + "|" + salary + "|" + specialty;
    }
}
