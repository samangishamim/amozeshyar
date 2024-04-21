//package utill;
//
//import model.Professor;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.Scanner;
//
//public class EmployeeMenu {
//
//    private Scanner scanner;
//
//    private EmployeeMenu employeeDao;
//
//    private ProfessorDao professorDao;
//
//    private StudentDao studentDao;
//
//    private CourseDao courseDao;
//
//
//    public EmployeeMenu(Scanner scanner, EmployeeDao employeeDao, ProfessorDao professorDao, StudentDao studentDao, CourseDao courseDao) {
//
//        this.scanner = scanner;
//
//        this.employeeDao = employeeDao;
//
//        this.professorDao = professorDao;
//
//        this.studentDao = studentDao;
//
//        this.courseDao = courseDao;
//
//    }
//
//
//    public void display() {
//
//        System.out.println("1. Register student");
//
//        System.out.println("2. Delete student");
//
//        System.out.println("3. Edit student");
//
//        System.out.println("4. Register professor");
//
//        System.out.println("5. Delete professor");
//
//        System.out.println("6. Edit professor");
//
//        System.out.println("7. Register employee");
//
//        System.out.println("8. Delete employee");
//
//        System.out.println("9. Edit employee");
//
//        System.out.println("10. Register course");
//
//        System.out.println("11. Delete course");
//
//        System.out.println("12. Edit course");
//
//        System.out.println("13. View payroll");
//
//        System.out.println("14. Logout");
//
//
//        int choice = scanner.nextInt();
//
//
//        switch (choice) {
//
//            case 1:
//
//                registerStudent();
//
//                break;
//
//            case 2:
//
//                deleteStudent();
//
//                break;
//
//            case 3:
//
//                editStudent();
//
//                break;
//
//            case 4:
//
//                registerProfessor();
//
//                break;
//
//            case 5:
//
//                deleteProfessor();
//
//                break;
//
//            case 6:
//
//                editProfessor();
//
//                break;
//
//            case 7:
//
//                registerEmployee();
//
//                break;
//
//            case 8:
//
//                deleteEmployee();
//
//                break;
//
//            case 9:
//
//                editEmployee();
//
//                break;
//
//            case 10:
//
//                registerCourse();
//
//                break;
//
//            case 11:
//
//                deleteCourse();
//
//                break;
//
//            case 12:
//
//                editCourse();
//
//                break;
//
//            case 13:
//
//                viewPayroll();
//
//                break;
//
//            case 14:
//
//                System.out.println("Goodbye!");
//
//                return;
//
//            default:
//
//                System.out.println("Invalid choice. Please try again.");
//
//                display();
//
//        }
//
//    }
//
//
//    private void registerStudent() {
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        Transaction transaction = null;
//
//
//        try {
//
//            transaction = session.beginTransaction();
//
//
//            System.out.print("Enter first name: ");
//
//
//            String firstName = scanner.next();
//
//
//            System.out.print("Enter last name: ");
//
//
//            String lastName = scanner.next();
//
//
//            System.out.print("Enter username: ");
//
//
//            String username = scanner.next();
//
//
//            System.out.print("Enter password: ");
//
//
//            String password = scanner.next();
//
//
//            System.out.print("Enter GPA: ");
//
//
//            double gpa = scanner.nextDouble();
//
//
//
//            Student student = new Student(firstName, lastName, username, password, gpa);
//
//
//            studentDao.save(session, student);
//
//
//
//            System.out.println("Student registered successfully.");
//
//
//            transaction.commit();
//
//
//        } catch (HibernateException e) {
//
//            if (transaction != null) {
//
//                transaction.rollback();
//
//            }
//
//            e.printStackTrace();
//
//        } finally {
//
//            session.close();
//
//        }
//
//
//    }
//
//
//    private void deleteStudent() {
//
//        Session session = HibernateUtil.getSessionFactory().openSession(); Transaction transaction = null;
//
//        try { transaction = session.beginTransaction();
//
//            System.out.print("Enter student ID: "); int id = scanner.nextInt();
//
//            Student student = session.get(Student.class, id);
//
//            if (student != null)
//            { studentDao.delete(session, student); System.out.println("Student deleted successfully."); } else { System.out.println("Student not found."); }
//
//            transaction.commit(); } catch (HibernateException e) { if (transaction != null) { transaction.rollback(); } e.printStackTrace(); } finally { session.close(); } }
//
//}
//
//    private void editStudent() {
//        private void editStudent() { Session session =
//
//                HibernateUtil.getSessionFactory().openSession(); Transaction transaction = null;
//
//            try { transaction = session.beginTransaction();
//
//                System.out.print("Enter student ID: "); int id = scanner.nextInt();
//
//                Student student = session.get(Student.class, id);
//
//                if (student != null) { System.out.print("Enter new first name: "); String firstName = scanner.next(); System.out.print("Enter new last name: "); String lastName = scanner.next(); System.out.print("Enter new username: "); String username = scanner.next(); System.out.print("Enter new password: "); String password = scanner.next(); System.out.print("Enter new GPA: "); double gpa = scanner.nextDouble();
//
//                    student.setFirstName(firstName); student.setLastName(lastName); student.setUsername(username); student.setPassword(password); student.setGpa(gpa);
//
//                    studentDao.update(session, student);
//
//                    System.out.println("Student updated successfully."); } else { System.out.println("Student not found."); }
//
//                transaction.commit(); } catch (HibernateException e) { if (transaction != null) { transaction.rollback(); } e.printStackTrace(); } finally { session.close(); } }
//    }
//    private void registerProfessor() {
//        System.out.print("Enter first name: ");
//        String firstName = scanner.next();
//        System.out.print("Enter last name: ");
//        String lastName = scanner.next();
//        System.out.print("Enter username: ");
//        String username = scanner.next();
//        System.out.print("Enter password: ");
//        String password = scanner.next();
//        System.out.print("Enter salary: ");
//        double salary = scanner.nextDouble();
//
//        Professor professor = new Professor(firstName, lastName, username, password, salary);
//        professorDao.save(professor);
//
//        System.out.println("Professor registered successfully.");
//    }
//
//
//    private void deleteProfessor() {
//        System.out.print("Enter professor ID: ");
//        int id = scanner.nextInt();
//
//        professorDao.deleteById(id);
//
//        System.out.println("Professor deleted successfully.");
//    }
//
//
//    private void editProfessor() {
//        System.out.print("Enter professor ID: ");
//        int id = scanner.nextInt();
//
//        Professor professor = professorDao.findById(id);
//
//        System.out.print("Enter new first name: ");
//        String firstName = scanner.next();
//        System.out.print("Enter new last name: ");
//        String lastName = scanner.next();
//        System.out.print("Enter new username: ");
//        String username = scanner.next();
//        System.out.print("Enter new password: ");
//        String password = scanner.next();
//        System.out.print("Enter new salary: ");
//        double salary = scanner.nextDouble();
//
//        professor.setFirstName(firstName);
//        professor.setLastName(lastName);
//        professor.setUsername(username);
//        professor.setPassword(password);
//        professor.setSalary(salary);
//
//        professorDao.update(professor);
//
//
//        System.out.println("Professor updated successfully.");
//    }
//
//    private void registerEmployee() {
//        System.out.print("Enter first name: ");
//        String firstName = scanner.next();
//        System.out.print("Enter last name: ");
//        String lastName = scanner.next();
//        System.out.print("Enter username: ");
//        String username = scanner.next();
//        System.out.print("Enter password: ");
//        String password = scanner.next();
//        System.out.print("Enter salary: ");
//        double salary = scanner.nextDouble();
//
//        Employee employee = new Employee(firstName, lastName, username, password, salary);
//        employeeDao.save(employee);
//
//        System.out.println("Employee registered successfully.");
//    }
//
//
//    private void deleteEmployee() {
//        System.out.print("Enter employee ID: ");
//        int id = scanner.nextInt();
//
//        employeeDao.deleteById(id);
//
//        System.out.println("Employee deleted successfully.");
//    }
//
//    private void editEmployee() {
//        System.out.print("Enter employee ID: ");
//        int id = scanner.nextInt();
//
//        try {
//            Employee employee = employeeDao.findById(id);
//
//            System.out.print("Enter new first name: ");
//            String firstName = scanner.next();
//            System.out.print("Enter new last name: ");
//            String lastName = scanner.next();
//            System.out.print("Enter new username: ");
//            String username = scanner.next();
//            System.out.print("Enter new password: ");
//            String password = scanner.next();
//
//            employee.setFirstName(firstName);
//            employee.setLastName(lastName);
//            employee.setUsername(username);
//            employee.setPassword(password);
//
//            employeeDao.update(employee);
//
//            System.out.println("Employee updated successfully.");
//
//        } catch (EmployeeNotFoundException e) {
//            System.out.println("Employee not found. Please try again.");
//            editEmployee();
//        }
//    }
//
//    private void registerCourse() {
//        System.out.print("Enter course name: ");
//        String name = scanner.next();
//        System.out.print("Enter number of units: ");
//        int units = scanner.nextInt();
//
//        Course course = new Course(name, units);
//
//        courseDao.save(course);
//
//        System.out.println("Course registered successfully.");
//    }
//
//    private void deleteCourse() {
//        System.out.print("Enter course ID: ");
//        int id = scanner.nextInt();
//
//        try {
//            courseDao.deleteById(id);
//            System.out.println("Course deleted successfully.");
//        } catch (CourseNotFoundException e) {
//            System.out.println("Course not found. Please try again.");
//            deleteCourse();
//        }
//    }
//
//    private void editCourse() {
//        System.out.print("Enter course ID: ");
//        int id = scanner.nextInt();
//
//        Course course = courseDao.findById(id);
//
//        if (course == null) {
//            System.out.println("Course not found. Please try again.");
//            return;
//        }
//
//        System.out.print("Enter new name: ");
//        String newName = scanner.next();
//        System.out.print("Enter new number of units: ");
//        int newUnits = scanner.nextInt();
//
//        course.setName(newName);
//        course.setUnits(newUnits);
//
//        courseDao.update(course);
//
//        System.out.println("Course updated successfully.");
//    }
//
//    private void viewPayroll() {
//        System.out.print("Enter employee ID: ");
//        int id = scanner.nextInt();
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = null;
//
//        try {
//            transaction = session.beginTransaction();
//
//            Employee employee = session.get(Employee.class, id);
//
//            if (employee == null) {
//                System.out.println("Employee not found. Please try again.");
//                return;
//            }
//
//            double salary = employee.getSalary();
//
//            System.out.println("Employee ID: " + employee.getId());
//            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
//            System.out.println("Salary: " + salary);
//
//            if (employee instanceof Professor) {
//                Professor professor = (Professor) employee;
//                int teachingUnits = professor.getTeachingUnits();
//                salary += teachingUnits * 1000; // $1000 per teaching unit
//            }
//
//            System.out.println("Total payroll: " + salary);
//
//            transaction.commit();
//        } catch (HibernateException e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//}