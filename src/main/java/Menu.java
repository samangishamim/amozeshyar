import exception.NotFoundException;
import model.*;
import security_context.SecurityContext;
import service.courceServise.CourseService;
import service.employeeService.EmployeeService;
import service.professorService.ProfessorService;
import service.registerCourse.RegisterCourseService;
import service.studentService.StudentService;
import utill.ApplicationContext;
import utill.Validation;


import java.util.*;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    final EmployeeService employeeService = ApplicationContext.getEmployeeService();
    final ProfessorService professorService = ApplicationContext.getProfessorService();
    final StudentService studentService = ApplicationContext.getStudentService();
    final CourseService courseService = ApplicationContext.getCourseService();
    final RegisterCourseService registerCourseService = ApplicationContext.getRegisterCourseService();


    public void mainMenu() {
        int numberInput = -1;
        while (numberInput != 0) {
            System.out.println("*** MAIN MENU ***");
            System.out.println("1-Continue as Employee");
            System.out.println("2-Continue as Student");
            System.out.println("3-Continue as Teacher");
            System.out.println("0-Exit");
            System.out.println();

            try {
                System.out.println("Enter a number:");
                numberInput = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }

            switch (numberInput) {
                case 1 -> {
                    String nationalId = employeeSignIn();
                    employeeMenu(nationalId);
                }
                case 2 -> {
                    String nationalId = studentSignIn();
                    studentMenu(nationalId);
                }
                case 3 -> {
                    String nationalId = professorSignIn();
                    professorMenu(nationalId);
                }
                case 0 -> System.out.println("Bye Bye");
                default -> System.out.println("Wrong input");
            }
        }
    }

    public void studentMenu(String nationalId) {
        int numberInput = -1;
        while (numberInput != 0) {
            System.out.println("*** Student MENU ***");
            System.out.println("1-Show your information");
            System.out.println("2-Show presented lessons");
            System.out.println("3-Choose lessons");
            System.out.println("4-Show list lessons with grade");
            System.out.println("0-Exit");
            System.out.println();

            try {
                System.out.println("Enter a number:");
                numberInput = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }

            switch (numberInput) {
                case 1 -> studentInfo(nationalId);
                case 2 -> listPresentedLessons();
                case 3 -> chooseCourse(nationalId);
                case 4 -> showlessonsWithGrade(nationalId);
                case 0 -> System.out.println("Bye Bye");
                default -> System.out.println("Wrong input");
            }
        }
    }

    private void chooseCourse(String nationalId) {
        int semesterChoose = 0;
        Optional<Student> student = studentService.studentInfo(nationalId);
        if (student.isPresent()) {
            Student student1 = student.get();
            System.out.println("Student : " + student1);
            try {
                System.out.println("Which semester do you want to choose from?");
                semesterChoose = Integer.parseInt(scanner.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid semester number.");
            }
            List<Course> byLessonCode = courseService.findBySemester(semesterChoose);
            if (byLessonCode != null) {
                Course lesson = byLessonCode.get(0);
                System.out.println("name : " + lesson.getName());
                checkForLessonSituation(nationalId, lesson, student1, semesterChoose);
            } else {
                System.out.println("Lesson not found this semester : " + semesterChoose);
                chooseCourse(nationalId);
            }
        } else {
            System.out.println("Student not found with national ID: " + nationalId);
            studentMenu(nationalId);
        }

    }

    private void studentInfo(String nationalId) {
        Optional<Student> student = studentService.studentInfo(nationalId);
        Student student1 = student.get();
        System.out.println("name : " + student1.getFirstName() + " " + student1.getLastName() +
                " national id : " + student1.getNationalId());
    }

    private void checkForLessonSituation(String nationalId, Course course, Student student1, int semesterChoose) {
        Integer capacity = course.getCapacity();
        Optional<RegisterCourse> registerCourse = registerCourseService.checkDoublLessonInOneSemster(student1, course);


        if (course.getSemester() == semesterChoose) {
            boolean capFlag = checkLessonCapacity(nationalId, capacity, course);
            if (capFlag && registerCourse.isPresent() && registerCourse.get().getCourseGrade().equals(CourseGrade.FAILED)) {

                registerCourseService.saveOrUpdate(new RegisterCourse(0D, CourseGrade.NO_VALUE, student1.getId(), course.getId()));

            } else if (registerCourse.isPresent() && registerCourse.get().getCourseGrade().equals(CourseGrade.PASSED)) {

                System.out.println("You passed this lesson before");
                studentMenu(nationalId);

            } else {
                registerCourseService.saveOrUpdate(new RegisterCourse(0D, CourseGrade.NO_VALUE, student1.getId(), course.getId()));
            }
        } else {
            System.out.println("You choose wrong semester");
            studentMenu(nationalId);
        }
    }

    private boolean checkLessonCapacity(String nationalId, Integer capacity, Course course) {
        if (capacity > 0) {
            capacity = capacity - 1;
            course.setCapacity(capacity);
            courseService.saveOrUpdate(course);
            return true;
        } else {
            System.out.println("the course have not capacity !!!");
            return false;
        }
    }


    private void showlessonsWithGrade(String nationalId) {
        Optional<Student> student = studentService.studentInfo(nationalId);

        Student student1 = student.get();


        List<RegisterCourse> registerCourses = registerCourseService.listStudentLessonsWithGrade(student1);


        for (RegisterCourse status : registerCourses) {

            Course course = status.getCourse();

            if (course != null) {

                String courseName = course.getName();

                String grade = String.valueOf(status.getGrade());

                CourseGrade courseGrade = status.getCourseGrade();

                System.out.println("Course Name: " + courseName + ", Grade: " + grade + ", Course Grade: " + courseGrade);

            } else {

                System.out.println("Course information is not available.");

            }

        }
    }

    private void listPresentedLessons() {
        List<Course> courseList = courseService.findAll();
        System.out.println();
        System.out.println("***** list of courses *****");
        for (Course course : courseList) {
            System.out.println("Term: ( " + course.getName() +
                    " - " + course.getSemester() +
                    " ) - " + course.getCapacity() + " - "
                    + "units: " + course.getUnits());
        }
    }


    public void professorMenu(String nationalId) {
        int numberInput = -1;
        while (numberInput != 0) {
            System.out.println("*** Professor MENU ***");
            System.out.println("1-Show your information");
            System.out.println("2-Give student's grades");
            System.out.println("3-Show your semester salary");
            System.out.println("0-Exit");
            System.out.println();

            try {
                System.out.println("Enter a number:");
                numberInput = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }

            switch (numberInput) {
                case 1 -> professorInfo(nationalId);
                case 2 -> scoring(nationalId);
                case 3 -> teacherSalaryPerUnit(nationalId);
                case 0 -> System.out.println("Bye Bye");
                default -> System.out.println("Wrong input");
            }
        }
    }


    public void scoring(String nationalId) {
        System.out.println("enter the semester : ");
        int semester = Integer.parseInt(scanner.nextLine());

        Optional<Professor> professor = professorService.professorInfo(nationalId);
        if (professor.isEmpty()) {
            System.out.println("Professor not found with national ID: " + nationalId);
            return; }
        Professor professor1 = professor.get();

        List<Course> courseList = courseService.findByProfessorIdAndSemester(professor1.getId(), semester);
        for (Course course : courseList) {
            System.out.println("id : " + course.getId() + " name : " + course.getName());
        }
        long id = 0L;
        try {
            System.out.println("Enter course id that you want give  : ");
            id = Long.parseLong(scanner.nextLine());
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        List<RegisterCourse> registerCourseList = registerCourseService.findByCourseId(id);
        for (RegisterCourse registerCourse : registerCourseList) {
            Long studentId = registerCourse.getStudentId();
            Student student = studentService.findById(studentId);
            System.out.println("id: " + studentId + " studentName:  "
                    + student.getFirstName() + " " + student.getLastName());
        }
        System.out.println(" select studentId to get score: ");
        long studentId = Long.parseLong(scanner.nextLine());
        double grade = 0D;
        try {
            System.out.println("Enter grade :");
            while (true) {
                grade = Double.parseDouble(scanner.nextLine());
                if (grade < 0 || grade > 20) {
                    System.out.println("You should enter grade between 0 - 20 !!!");
                } else
                    break;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }


        RegisterCourse registerCourse = registerCourseService.findByStudentIdAndCourseId(studentId, id);

        if (registerCourse == null) {

            System.out.println("RegisterCourse not found for student ID: " + studentId + " and course ID: " + id);

            return;

        }


        CourseGrade courseGrade;

        if (grade < 20 || grade > 0) {

            if (grade >= 10)

                courseGrade = CourseGrade.PASSED;

            else

                courseGrade = CourseGrade.FAILED;


            registerCourse.setGrade(grade);

            registerCourse.setCourseGrade(courseGrade);

            registerCourseService.saveOrUpdate(registerCourse);
        }
    }

    private void professorInfo(String nationalId) {

        Optional<Professor> professor = professorService.professorInfo(nationalId);
        Professor professor1 = professor.get();
        System.out.println("name : " + professor1.getFirstName() + " " + professor1.getLastName() + " national id : " +
                professor1.getNationalId() + " password : " + professor1.getPassword());
    }

    private void teacherSalaryPerUnit(String nationalId) {
        Professor professor = professorService.professorInfo(nationalId)
                .orElseThrow(() -> new NotFoundException("Professor not found"));
        Long professorId = professor.getId();
        System.out.println("Teacher : " + professor);
        System.out.println("Please enter semester :");
        int semester = scanner.nextInt();
        scanner.nextLine();
        double finalSalary = courseService.professorSemesterSalary(professorId, semester);
        System.out.println(finalSalary);
    }

    public void employeeMenu(String nationalId) {
        int numberInput = -1;
        while (numberInput != 0) {
            System.out.println("** Employee Menu **");
            System.out.println("1-Add new Employee");
            System.out.println("2-Update Employee");
            System.out.println("3-Delete Employee");
            System.out.println("4-Add new Teacher");
            System.out.println("5-Update Teacher");
            System.out.println("6-Delete Teacher");
            System.out.println("7-Add new Student");
            System.out.println("8-Update Student");
            System.out.println("9-Delete Student");
            System.out.println("10-Add new Lesson");
            System.out.println("11-Update Lesson");
            System.out.println("12-Delete Lesson");
            System.out.println("13-Show your salary");
            System.out.println("0-Exit");
            System.out.println();

            try {
                System.out.println("Enter a number:");
                numberInput = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }

            switch (numberInput) {
                case 1 -> savaEmployee();
                case 2 -> updateEmployee();
                case 3 -> deleteEmployee();
                case 4 -> addProfessor();
                case 5 -> updateProfessor();
                case 6 -> deleteProfessor();
                case 7 -> addStudent();
                case 8 -> updateStudent();
                case 9 -> deleteStudent();
                case 10 -> addCourse();
                case 11 -> updateCourse();
                case 12 -> deleteCourse();
                case 13 -> employeeSalary(nationalId);
                case 0 -> System.out.println("Bye Bye");
                default -> System.out.println("Wrong input");
            }
        }
    }

    private void employeeSalary(String nationalId) {

        Optional<Employee> employee1 = employeeService.employeeSalary(nationalId);
        if (employee1.isPresent()) {
            System.out.println("name : " + employee1.get().getFirstName() + " " + employee1.get().getLastName() +

                    " national id : " + employee1.get().getNationalId() + " salary : " + employee1.get().getSalary());
        } else
            System.out.println("this employee with nationalId: " + nationalId + " not found");
    }

    private void deleteCourse() {
        List<Course> courseList = courseService.findAll();

        System.out.println(courseList);

        System.out.println("** Delete Lesson **");

        System.out.println("Please Enter id :");


        while (!scanner.hasNextLong()) {

            System.out.println("Invalid input. Please enter a valid ID:");

            scanner.next();

        }


        Long id = scanner.nextLong();

        scanner.nextLine();


        courseService.delete(id);
    }

    private String professorSignIn() {
        String nationalId = "";
        String password = "";
        while (true) {
            System.out.println("Enter your national id :");
            nationalId = scanner.nextLine();
            System.out.println("Enter your password :");
            password = scanner.nextLine();

            List<Professor> student = professorService.professorSignIn(nationalId, password);

            if (student != null) {
                System.out.println("Welcome " + nationalId);
                return nationalId;
            } else
                System.out.println("Username or password is invalid");
        }
    }

    private String studentSignIn() {
        String nationalId = "";
        String password = "";
        while (true) {
            System.out.println("Enter your national id :");
            nationalId = scanner.nextLine();
            System.out.println("Enter your password :");
            password = scanner.nextLine();

            List<Student> student = studentService.studentSignIn(nationalId, password);

            if (student != null) {
                System.out.println("Welcome " + nationalId);
                return nationalId;
            } else
                System.out.println("Username or password is invalid");
        }
    }

    private String employeeSignIn() {
        String nationalId = "";
        String password = "";
        while (true) {
            System.out.println("Enter your national id :");
            nationalId = scanner.nextLine();
            System.out.println("Enter your password :");
            password = scanner.nextLine();

            List<Employee> employee = employeeService.employeeSignIn(nationalId, password);

            if (employee != null) {
                System.out.println("Welcome " + nationalId);
                return nationalId;
            } else
                System.out.println("Username or password is invalid");
        }
    }

    private void updateCourse() {
        System.out.println("** Update Lesson **");
        System.out.println("Please Enter id :");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Please enter name :");
        String name = scanner.nextLine();
        System.out.println("Please enter number of unit");
        Integer unit = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter course code");
        Integer courseCode = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter number of semesters");
        Integer numSemester = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter number of capacity");
        Integer capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter teacher id");
        Long professorId = scanner.nextLong();
        Professor professor = professorService.findById(professorId);


        courseService.saveOrUpdate(new Course(id, name, unit, courseCode, capacity, numSemester, professor));
    }

    private void addCourse() {
        System.out.println("** Add Lesson **");
        System.out.println("Please enter name :");
        String name = scanner.nextLine();
        System.out.println("Please enter lesson code");
        Integer courseCode = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter number of unit");
        Integer unit = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter number of semesters");
        Integer numSemester = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter number of capacity");
        Integer capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter teacher id");
        Long professorId = scanner.nextLong();
        Professor professor = professorService.findById(professorId);

        Course course = new Course(name, unit, courseCode, capacity, numSemester);
        courseService.addCourse(course, professorId);

    }

    private void deleteStudent() {
        System.out.println(studentService.findAll());
        System.out.println("** Delete Student **");
        System.out.println("Please Enter id :");
        Long id = scanner.nextLong();
        scanner.nextLine();

        studentService.delete(id);
    }

    private void updateStudent() {
        System.out.println(studentService.findAll());
        System.out.println("** Update Student **");
        System.out.println("Please Enter id :");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Please enter name :");
        String name = scanner.nextLine();
        System.out.println("Please enter lastname :");
        String lastname = scanner.nextLine();
        String nationalId = getValidNationalId();
        String password = getValidPassword();

        studentService.saveOrUpdate(new Student(id, name, lastname, nationalId, password));
    }

    private void addStudent() {
        System.out.println("** Register Student **");
        System.out.println("Please enter name :");
        String name = scanner.nextLine();
        System.out.println("Please enter lastname :");
        String lastname = scanner.nextLine();
        String nationalId = getValidNationalId();
        String password = getValidPassword();

        studentService.saveOrUpdate(new Student(name, lastname, nationalId, password));
    }

    private void deleteProfessor() {
        System.out.println(professorService.findAll());
        System.out.println("** Delete Teacher **");
        System.out.println("Please Enter id :");
        Long id = scanner.nextLong();
        scanner.nextLine();
        professorService.delete(id);
    }

    private void updateProfessor() {
        System.out.println(professorService.findAll());
        System.out.println("** Update Teacher **");
        System.out.println("Please Enter id :");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Please enter name :");
        String name = scanner.nextLine();
        System.out.println("Please enter lastname :");
        String lastname = scanner.nextLine();
        String nationalId = getValidNationalId();
        String password = getValidPassword();
        ProfessorType professorType = getProfessorrRank();
        System.out.println("Please enter main salary");
        Double mainSalary = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Please enter number of semesters");
        Integer numSemester = scanner.nextInt();
        scanner.nextLine();
        Double finalSalary;
        if (professorType.equals(ProfessorType.FACULTY_MEMBER))
            finalSalary = mainSalary + (numSemester * 1000000);
        else
            finalSalary = mainSalary;

        professorService.saveOrUpdate(new Professor(id, name, lastname, nationalId, password, finalSalary, mainSalary, numSemester, professorType));
    }

    private void addProfessor() {
        System.out.println("** Register Teacher **");
        System.out.println("Please enter name :");
        String name = scanner.nextLine();
        System.out.println("Please enter lastname :");
        String lastname = scanner.nextLine();
        String nationalId = getValidNationalId();
        String password = getValidPassword();
        ProfessorType professorType = getProfessorrRank();
        System.out.println("Please enter main salary");
        Double mainSalary = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Please enter number of semesters");
        Integer numSemester = scanner.nextInt();
        scanner.nextLine();
        Double finalSalary;
        if (professorType.equals(ProfessorType.FACULTY_MEMBER))
            finalSalary = mainSalary + (numSemester * 1000000);
        else
            finalSalary = mainSalary;

        professorService.saveOrUpdate(new Professor(name, lastname, nationalId, password, finalSalary, mainSalary, numSemester, professorType));
    }

    public ProfessorType getProfessorrRank() {
        ProfessorType[] values = ProfessorType.values();
        int i = 0;
        for (ProfessorType str : ProfessorType.values()) {
            System.out.println(++i + "- " + str);
        }
        System.out.println("choose the member degree: ");
        int choice = Integer.parseInt(scanner.nextLine());
        ProfessorType professorType = values[choice - 1];
        return professorType;
    }

    private void deleteEmployee() {
        System.out.println(employeeService.findAll());
        System.out.println("** Delete Employee **");
        System.out.println("Please Enter id :");
        Long id = scanner.nextLong();
        scanner.nextLine();
        employeeService.delete(id);
    }

    private void updateEmployee() {
        System.out.println(employeeService.findAll());
        System.out.println("** Update Employee **");
        System.out.println("Please id :");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Please enter name :");
        String name = scanner.nextLine();
        System.out.println("Please enter lastname :");
        String lastname = scanner.nextLine();
        String nationalId = getValidNationalId();
        String password = getValidPassword();
        System.out.println("Please enter salary");
        Double salary = scanner.nextDouble();

        employeeService.saveOrUpdate(new Employee(id, name, lastname, nationalId, password, salary));
    }

    private void savaEmployee() {
        System.out.println("** Register Employee **");
        System.out.println("Please enter name :");
        String name = scanner.nextLine();
        System.out.println("Please enter lastname :");
        String lastname = scanner.nextLine();
        String nationalId = getValidNationalId();
        String password = getValidPassword();
        System.out.println("Please enter salary");
        Double salary = scanner.nextDouble();

        employeeService.saveOrUpdate(new Employee(name, lastname, nationalId, password, salary));
    }

    public String getValidPassword() {
        String password;
        while (true) {
            System.out.println("Please enter your password :");
            password = scanner.nextLine();
            if (Validation.isPasswordValid(password))
                break;
            else
                System.out.println("This " + password + " is not strong , try again");
        }
        return password;
    }

    public String getValidNationalId() {
        String NationalId;
        while (true) {
            System.out.println("Please enter your National id :");
            NationalId = scanner.nextLine();
            if (Validation.validateMelliCode(NationalId))
                break;
            else
                System.out.println("This " + NationalId + " is not valid");
        }
        return NationalId;
    }
}
