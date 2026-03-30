public class Nurse extends Person {
    private String id;
    private String department;
    private double salary;
    
    public Nurse(String id, String name, int age, String department, double salary) {
        super(name, age);
        this.id = id;
        this.department = department;
        this.salary = salary;
    }
    
    public String getId() { return id; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    
    public void showInfo() {
        System.out.println("Nurse: " + getName() + " (ID: " + id + ", Age: " + getAge() + 
                          ", Dept: " + department + ", Salary: $" + salary + ")");
    }
    
    public void displayInfo() {
        showInfo();
    }
    
    public String toFileString() {
        return "NURSE|" + id + "|" + getName() + "|" + getAge() + "|" + department + "|" + salary;
    }
}
