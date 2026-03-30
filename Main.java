import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    // Collections to store data
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Nurse> nurses = new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static HashMap<String, Doctor> doctorMap = new HashMap<>();
    private static HashMap<String, Nurse> nurseMap = new HashMap<>();
    private static HashMap<String, Patient> patientMap = new HashMap<>();
    
    private static final String DATA_FILE = "hospital_data.txt";
    
    public static void main(String[] args) {
        System.out.println("Welcome to Hospital Management System!");
        System.out.println("=====================================\n");
        
        loadDataFromFile();
        
        try {
            runHospitalMenu();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            saveDataToFile();
            scanner.close();
            System.out.println("\nData saved to " + DATA_FILE);
            System.out.println("Goodbye!");
        }
    }
    
    private static void runHospitalMenu() {
        int choice;
        do {
            printMenu();
            choice = getChoice(0, 8);
            
            if (choice == 1) addDoctor();
            else if (choice == 2) addNurse();
            else if (choice == 3) addPatient();
            else if (choice == 4) showBills();
            else if (choice == 5) showAllDoctors();
            else if (choice == 6) showAllNurses();
            else if (choice == 7) showAllPatients();
            else if (choice == 8) showAllStaff();
            
        } while (choice != 0);
    }
    
    private static void printMenu() {
        System.out.println("\nMAIN MENU:");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Nurse"); 
        System.out.println("3. Add Patient");
        System.out.println("4. Show Sample Bills");
        System.out.println("5. Show All Doctors (" + doctors.size() + ")");
        System.out.println("6. Show All Nurses (" + nurses.size() + ")");
        System.out.println("7. Show All Patients (" + patients.size() + ")");
        System.out.println("8. Show All Staff");
        System.out.println("0. Quit & Save");
        System.out.print("Enter choice: ");
    }
    
    private static String getText(String message) {
        while (true) {
            System.out.print(message);
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) return text;
            System.out.println("Input cannot be empty. Try again.");
        }
    }
    
    private static int getChoice(int min, int max) {
        while (true) {
            try {
                System.out.print("Enter " + min + "-" + max + ": ");
                int num = scanner.nextInt();
                scanner.nextLine();
                if (num >= min && num <= max) return num;
                System.out.println("Invalid option. Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Numbers only please.");
                scanner.nextLine();
            }
        }
    }
    
    private static int getAge() {
        while (true) {
            try {
                System.out.print("Age (18-100): ");
                int age = scanner.nextInt();
                scanner.nextLine();
                if (age >= 18 && age <= 100) return age;
                System.out.println("Age must be between 18 and 100.");
            } catch (InputMismatchException e) {
                System.out.println("Numbers only.");
                scanner.nextLine();
            }
        }
    }
    
    private static double getSalary(double min) {
        while (true) {
            try {
                System.out.print("Salary (minimum $" + min + "): ");
                double salary = scanner.nextDouble();
                scanner.nextLine();
                if (salary >= min) return salary;
                System.out.println("Salary too low.");
            } catch (InputMismatchException e) {
                System.out.println("Numbers please.");
                scanner.nextLine();
            }
        }
    }
    
    private static void addDoctor() {
        try {
            System.out.println("\nAdding a new doctor:");
            String id = getText("Doctor ID (e.g., D001): ");
            
            if (doctorMap.containsKey(id)) {
                System.out.println("Error: Doctor ID " + id + " already exists.");
                return;
            }
            
            String name = getText("Doctor name: ");
            int age = getAge();
            String dept = getText("Department: ");
            double salary = getSalary(50000);
            String specialty = getText("Specialty: ");
            
            Doctor doc = new Doctor(id, name, age, dept, salary, specialty);
            doctors.add(doc);
            doctorMap.put(id, doc);
            
            System.out.println("Doctor added successfully! Total doctors: " + doctors.size());
            doc.displayInfo();
            
        } catch (Exception e) {
            System.out.println("Failed to add doctor: " + e.getMessage());
        }
    }
    
    private static void addNurse() {
        try {
            System.out.println("\nAdding a new nurse:");
            String id = getText("Nurse ID (e.g., N001): ");
            
            if (nurseMap.containsKey(id)) {
                System.out.println("Error: Nurse ID " + id + " already exists.");
                return;
            }
            
            String name = getText("Nurse name: ");
            int age = getAge();
            String dept = getText("Department: ");
            double salary = getSalary(30000);
            
            Nurse nurse = new Nurse(id, name, age, dept, salary);
            nurses.add(nurse);
            nurseMap.put(id, nurse);
            
            System.out.println("Nurse added successfully! Total nurses: " + nurses.size());
            nurse.displayInfo();
            
        } catch (Exception e) {
            System.out.println("Failed to add nurse: " + e.getMessage());
        }
    }
    
    private static void addPatient() {
        try {
            System.out.println("\nNew patient check-in:");
            String id = getText("Patient ID (e.g., P001): ");
            
            if (patientMap.containsKey(id)) {
                System.out.println("Error: Patient ID " + id + " already exists.");
                return;
            }
            
            String name = getText("Patient name: ");
            int age = getAge();
            String diagnosis = getText("Diagnosis: ");
            String recordId = getText("Record ID (e.g., R001): ");
            
            Patient patient = new Patient(id, name, age, diagnosis, recordId);
            patients.add(patient);
            patientMap.put(id, patient);
            
            System.out.println("Patient checked in successfully! Total patients: " + patients.size());
            patient.displayInfo();
            
        } catch (Exception e) {
            System.out.println("Failed to add patient: " + e.getMessage());
        }
    }
    
    private static void showAllDoctors() {
        System.out.println("\nALL DOCTORS (" + doctors.size() + "):");
        if (doctors.isEmpty()) {
            System.out.println("   No doctors yet. Use option 1 to add.");
            return;
        }
        for (Doctor d : doctors) {
            System.out.print("   ");
            d.showInfo();
        }
    }
    
    private static void showAllNurses() {
        System.out.println("\nALL NURSES (" + nurses.size() + "):");
        if (nurses.isEmpty()) {
            System.out.println("   No nurses yet. Use option 2 to add.");
            return;
        }
        for (Nurse n : nurses) {
            System.out.print("   ");
            n.showInfo();
        }
    }
    
    private static void showAllPatients() {
        System.out.println("\nALL PATIENTS (" + patients.size() + "):");
        if (patients.isEmpty()) {
            System.out.println("   No patients yet. Use option 3 to add.");
            return;
        }
        for (Patient p : patients) {
            System.out.print("   ");
            p.showInfo();
        }
    }
    
    private static void showAllStaff() {
        System.out.println("\nHOSPITAL DIRECTORY");
        System.out.println("=================================");
        showAllDoctors();
        showAllNurses();
        showAllPatients();
        System.out.println("=================================");
        System.out.println("Stats: " + doctors.size() + " Doctors, " + 
                          nurses.size() + " Nurses, " + patients.size() + " Patients");
    }
    
    private static void showBills() {
        System.out.println("\nSAMPLE BILLS:");
        if (!doctors.isEmpty()) {
            doctors.get(0).generateBill();
        } else {
            Doctor tempDoc = new Doctor("D001", "Dr. Smith", 45, "Cardiology", 120000, "Heart Surgery");
            tempDoc.generateBill();
        }
        
        if (!patients.isEmpty()) {
            patients.get(0).generateBill();
        } else {
            Patient tempPatient = new Patient("P001", "John Doe", 28, "Flu", "R001");
            tempPatient.generateBill();
        }
    }
    
    private static void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Doctor d : doctors) {
                writer.write(d.toFileString());
                writer.newLine();
            }
            for (Nurse n : nurses) {
                writer.write(n.toFileString());
                writer.newLine();
            }
            for (Patient p : patients) {
                writer.write(p.toFileString());
                writer.newLine();
            }
            System.out.println("Data saved: " + 
                               (doctors.size() + nurses.size() + patients.size()) + " records.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    
    private static void loadDataFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No previous data found. Starting fresh.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            int count = 0;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 2) continue;
                
                String type = parts[0];
                
                switch (type) {
                    case "DOCTOR":
                        if (parts.length == 7) {
                            Doctor d = new Doctor(parts[1], parts[2], Integer.parseInt(parts[3]),
                                                  parts[4], Double.parseDouble(parts[5]), parts[6]);
                            doctors.add(d);
                            doctorMap.put(parts[1], d);
                            count++;
                        }
                        break;
                    case "NURSE":
                        if (parts.length == 6) {
                            Nurse n = new Nurse(parts[1], parts[2], Integer.parseInt(parts[3]),
                                                parts[4], Double.parseDouble(parts[5]));
                            nurses.add(n);
                            nurseMap.put(parts[1], n);
                            count++;
                        }
                        break;
                    case "PATIENT":
                        if (parts.length == 6) {
                            Patient p = new Patient(parts[1], parts[2], Integer.parseInt(parts[3]),
                                                    parts[4], parts[5]);
                            patients.add(p);
                            patientMap.put(parts[1], p);
                            count++;
                        }
                        break;
                }
            }
            System.out.println("Loaded " + count + " records from previous session.");
            System.out.println("   " + doctors.size() + " doctors, " + 
                               nurses.size() + " nurses, " + patients.size() + " patients");
        } catch (IOException e) {
            System.out.println("Could not load data file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Data file corrupted. Starting fresh.");
        }
    }
}
