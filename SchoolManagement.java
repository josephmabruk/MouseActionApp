import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. STUDENT CLASS
// ==========================================
class Student {
    private int id;
    private String name;
    private int grade;
    private int feesPaid;
    private int feesTotal;

    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.feesPaid = 0;
        this.feesTotal = 30000; // Standard annual tuition fee
    }

    public void payFees(int fees) {
        this.feesPaid += fees;
        School.updateTotalMoneyEarned(fees);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getGrade() { return grade; }
    public int getFeesPaid() { return feesPaid; }
    public int getRemainingFees() { return feesTotal - feesPaid; }

    @Override
    public String toString() {
        return "Student: " + name + " (ID: " + id + ") | Paid: $" + feesPaid + " | Remaining: $" + getRemainingFees();
    }
}

// ==========================================
// 2. TEACHER CLASS
// ==========================================
class Teacher {
    private int id;
    private String name;
    private int salary;
    private int salaryEarned;

    public Teacher(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.salaryEarned = 0;
    }

    public void receiveSalary() {
        this.salaryEarned += salary;
        School.updateTotalMoneySpent(salary);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getSalary() { return salary; }

    @Override
    public String toString() {
        return "Teacher: " + name + " (ID: " + id + ") | Salary: $" + salary;
    }
}

// ==========================================
// 3. SCHOOL CLASS
// ==========================================
class School {
    private List<Teacher> teachers;
    private List<Student> students;
    private static int totalMoneyEarned = 0;
    private static int totalMoneySpent = 0;

    public School(List<Teacher> teachers, List<Student> students) {
        this.teachers = teachers;
        this.students = students;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public static void updateTotalMoneyEarned(int moneyEarned) {
        totalMoneyEarned += moneyEarned;
    }

    public static void updateTotalMoneySpent(int moneySpent) {
        totalMoneySpent += moneySpent;
    }

    public static int getNetBalance() {
        return totalMoneyEarned - totalMoneySpent;
    }

    public void displaySummary() {
        System.out.println("\n========== SCHOOL FINANCIAL SUMMARY ==========");
        System.out.println("Total Revenue (Fees Collected): $" + totalMoneyEarned);
        System.out.println("Total Expenses (Salaries Paid): $" + totalMoneySpent);
        System.out.println("Net School Balance:            $" + getNetBalance());
        System.out.println("==============================================\n");
    }
}

// ==========================================
// 4. MAIN RUNNER
// ==========================================
public class SchoolManagement {
    public static void main(String[] args) {
        // Create Teachers
        Teacher sarah = new Teacher(1, "Sarah Connor", 5000);
        Teacher john = new Teacher(2, "John Keating", 6000);
        
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(sarah);
        teacherList.add(john);

        // Create Students
        Student alice = new Student(101, "Alice Smith", 10);
        Student bob = new Student(102, "Bob Jones", 11);

        List<Student> studentList = new ArrayList<>();
        studentList.add(alice);
        studentList.add(bob);

        // Initialize School System
        School lincolnHigh = new School(teacherList, studentList);

        // --- Execute Actions (Fixed line values below) ---
        alice.payFees(15000);
        bob.payFees(20000);

        // Pay teachers
        sarah.receiveSalary();

        // Print status
        System.out.println(alice);
        System.out.println(bob);
        System.out.println(sarah);

        // Display financial overview
        lincolnHigh.displaySummary();
    }
}