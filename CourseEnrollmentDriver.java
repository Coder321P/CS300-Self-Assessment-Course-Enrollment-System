import java.util.Scanner;

public class CourseEnrollmentDriver {

    private static final String WELCOME_MSG = "--- Welcome to the Course Enrollment System! ----";
    private static final String GOOD_BYE_MSG = "---------- BYE! Thanks for using our App! ----------";
    private static final String SYNTAX_ERROR_MSG = "Syntax Error: Please enter a valid command!";
    private static final String NO_COURSE_ENROLLMENT_MSG =
            "Error: Create a new course enrollment first!";

    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);
        Scanner scanner = new Scanner(System.in);
        processUserCommands(scanner);
        scanner.close();
        System.out.println(GOOD_BYE_MSG);
    }

    private static void displayMenu() {
        System.out.println("\n================================ MENU ===============================");
        System.out.println("Enter one of the following options:");
        System.out.println("[1 <roster_capacity> <waitlist_capacity>] Create a new course enrollment");
        System.out.println("[2 <name>:<wisc_email>:<campus_ID>:boolean(true/false)] Enroll student");
        System.out.println("[3 <name>:<wisc_email>:<campus_ID>:boolean] Add student to waitlist");
        System.out.println("[4 <campus_ID>] Drop the course");
        System.out.println("[5] Print roster");
        System.out.println("[6] Print waitlist");
        System.out.println("[7] Logout and EXIT");
        System.out.println("-----------------------------------------------------------------------");
    }

    private static void processUserCommands(Scanner scanner) {
        String promptCommandLine = "ENTER COMMAND: ";
        String command = null;

        String[][] roster = null;
        int rosterSize = 0;
        String[][] waitlist = null;

        do {
            displayMenu();
            System.out.print(promptCommandLine);
            command = scanner.nextLine();

            if (command == null || command.isBlank()) {
                System.out.println(SYNTAX_ERROR_MSG);
                continue;
            }

            char cmd = command.charAt(0);

            if (cmd == '7') break;

            if (cmd == '1') {
                String[] parts = command.split(" ");
                if (parts.length != 3) {
                    System.out.println(SYNTAX_ERROR_MSG);
                    continue;
                }
                roster = CourseEnrollment.createEmptyList(Integer.parseInt(parts[1]));
                waitlist = CourseEnrollment.createEmptyList(Integer.parseInt(parts[2]));
                rosterSize = 0;
                continue;
            }

            if (roster == null || waitlist == null) {
                System.out.println(NO_COURSE_ENROLLMENT_MSG);
                continue;
            }

            switch (cmd) {
                case '2':
                    String[] parts2 = command.split(" ");
                    if (parts2.length != 2) {
                        System.out.println(SYNTAX_ERROR_MSG);
                        break;
                    }
                    String[] studentInfo2 = parts2[1].split(":");
                    if (studentInfo2.length != 4) {
                        System.out.println(SYNTAX_ERROR_MSG);
                        break;
                    }
                    String name2 = studentInfo2[0];
                    String email2 = studentInfo2[1];
                    String campusId2 = studentInfo2[2];
                    boolean prereq2 = Boolean.parseBoolean(studentInfo2[3]);

                    rosterSize = CourseEnrollment.enrollOneStudent(
                            name2, email2, campusId2, prereq2, roster, rosterSize, waitlist);
                    break;

                case '3':
                    String[] parts3 = command.split(" ");
                    if (parts3.length != 2) {
                        System.out.println(SYNTAX_ERROR_MSG);
                        break;
                    }
                    String[] studentInfo3 = parts3[1].split(":");
                    if (studentInfo3.length != 4) {
                        System.out.println(SYNTAX_ERROR_MSG);
                        break;
                    }
                    String name3 = studentInfo3[0];
                    String email3 = studentInfo3[1];
                    String campusId3 = studentInfo3[2];
                    boolean prereq3 = Boolean.parseBoolean(studentInfo3[3]);

                    CourseEnrollment.addWaitlist(name3, email3, campusId3, prereq3, waitlist);
                    break;

                case '4':
                    String[] parts4 = command.split(" ");
                    if (parts4.length != 2) {
                        System.out.println(SYNTAX_ERROR_MSG);
                        break;
                    }
                    rosterSize = CourseEnrollment.dropCourse(parts4[1], roster, rosterSize);
                    break;

                case '5':
                    CourseEnrollment.printRoster(roster, rosterSize);
                    break;

                case '6':
                    CourseEnrollment.printWaitlist(waitlist);
                    break;

                default:
                    System.out.println(SYNTAX_ERROR_MSG);
            }

        } while (true);
    }
}