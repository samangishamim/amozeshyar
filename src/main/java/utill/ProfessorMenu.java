//package utill;
//
//public class ProfessorMenu {
//    private Scanner scanner;
//    private ProfessorDao professorDao;
//    private StudentDao studentDao;
//
//    public ProfessorMenu(Scanner scanner, ProfessorDao professorDao, StudentDao studentDao) {
//        this.scanner = scanner;
//        this.professorDao = professorDao;
//        this.studentDao = studentDao;
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
//        Professor professor = professorDao.findById(getLoggedInUserId());
//        System.out.println("Name: " + professor.getFirstName() + " " + professor.getLastName());
//        System.out.println("Username: " + professor.getUsername());
//        System.out.println("Salary: " + professor.getSalary());
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
//        System.out.println("Enter the number of units:");
//        int units = scanner.nextInt();
//        Professor professor = professorDao.findById(getLoggedInUserId());
//        studentDao.chooseUnits(lessonId, units, professor.getId());
//        System.out.println("Units chosen successfully!");
//    }
//
//    private void viewGrades() {
//        List<Grade> grades = gradeDao.findByProfessorId(getLoggedInUserId());
//        for (Grade grade : grades) {
//            System.out.println("Student: " + studentDao.findById(grade.getStudentId()).getFirstName() + " " + studentDao.findById(grade.getStudentId()).getLastName());
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
