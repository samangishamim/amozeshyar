//package utill;
//
//import exception.NotFoundException;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class StudentMenu {
//    private Scanner scanner;
//    private StudentMenu studentDao;
//    private Lesson lessonDao;
//    private ChooseUnitsDao chooseUnitsDao;
//    private GradeDao gradeDao;
//
//    public StudentMenu(Scanner scanner, StudentMenu studentDao, Lesson lessonDao, ChooseUnitsDao chooseUnitsDao, GradeDao gradeDao) {
//        this.scanner = scanner;
//        this.studentDao = studentDao;
//        this.lessonDao = lessonDao;
//        this.chooseUnitsDao = chooseUnitsDao;
//        this.gradeDao = gradeDao;
//    }
//
//    public void display() {
//        System.out.println("1. View your profile");
//        System.out.println("2. View list of lessons");
//        System.out.println("3. Choose units");
//        System.out.println("4. View grades");
//        System.out.println("5. Logout");
//
//        int choice = scanner.nextInt();
//
//        switch (choice) {
//            case 1:
//                viewProfile();
//                break;
//            case 2:
//                viewLessons();
//                break;
//            case 3:
//                chooseUnits();
//                break;
//            case 4:
//                viewGrades();
//                break;
//            case 5:
//                System.out.println("Goodbye!");
//                return;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                display();
//        }
//    }
//
//    private void viewProfile() {
////        Student student = studentDao.findById(getLoggedInUserId());
////        System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
////        System.out.println("Username: " + student.getUsername());
////        System.out.println("GPA: " + student.getGpa());
//    }
//
//    private void viewLessons() {
//        List<Lesson> lessons = lessonDao.findAll();
//        for (Lesson lesson : lessons) {
//            System.out.println(lesson.getName());
//        }
//    }
//
//    private void chooseUnits() {
//        System.out.println("Enter the ID of the lesson:");
//        int lessonId = scanner.nextInt();
//        try {
//            Lesson lesson = lessonDao.findById(lessonId);
//            System.out.println("Enter the number of units:");
//            int units = scanner.nextInt();
//            studentDao.chooseUnits(lessonId, units);
//            System.out.println("Units chosen successfully!");
//        } catch (NotFoundException e) {
//            System.out.println("Lesson not found. Please try again.");
//            chooseUnits();
//        }
//    }
//
//    private void viewGrades() {
//        List<Grade> grades = gradeDao.findByStudentId(getLoggedInUserId());
//        for (Grade grade : grades) {
//            System.out.println("Lesson: " + lessonDao.findById(grade.getLessonId()).getName());
//            System.out.println("Grade: " + grade.getGrade());
//        }
//    }
//
//    private int getLoggedInUserId() {
//        // Code to get the ID of the logged-in user
//        return 1; // Replace with actual code
//    }
//}
