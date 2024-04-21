//package utill;
//
//import java.util.Scanner;
//
//public class Menu {
//
//    private static Scanner scanner = new Scanner(System.in);
//    private static EmployeeDao employeeDao = new EmployeeDao();
//    private static ProfessorDao professorDao = new ProfessorDao();
//    private static StudentDao studentDao = new StudentDao();
//    private static Menu menu;
//    public static void main(String[] args) {
//            while (true) {
//                System.out.println("1. Login as employee");
//                System.out.println("2. Login as professor");
//                System.out.println("3. Login as student");
//
//                int choice = scanner.nextInt();
//
//                switch (choice) {
//                    case 1:
//                        menu = new EmployeeMenu(scanner, employeeDao, professorDao, studentDao);
//                        break;
//                    case 2:
//                        menu = new ProfessorMenu(scanner, professorDao, studentDao);
//                        break;
//                    case 3:
//                        menu = new StudentMenu(scanner, studentDao);
//                        break;
//                    default:
//                        System.out.println("Invalid choice. Please try again.");
//                }
//
//                menu.display();
//            }
//        }
//    }
//
//}
