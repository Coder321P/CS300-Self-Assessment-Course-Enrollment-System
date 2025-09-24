public class CourseEnrollment {

    public static String[][] createEmptyList(int capacity) {
        return new String[capacity][];
    }

    public static void printRoster(String[][] roster, int size) {
        System.out.println("Maximum capacity: " + roster.length);
        System.out.println("Number of enrolled students: " + size);
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + roster[i][0] + " (" +
                    roster[i][1] + ", " + roster[i][2] + ")");
        }
    }

    public static void printWaitlist(String[][] waitlist) {
        System.out.println("Waitlist capacity: " + waitlist.length);
        int count = 1;
        for (int i = 0; i < waitlist.length; i++) {
            if (waitlist[i] != null) {
                System.out.println(count + ". " + waitlist[i][0] + " (" +
                        waitlist[i][1] + ", " + waitlist[i][2] + ")");
                count++;
            }
        }
    }

    public static int indexOf(String campusId, String[][] list, int size) {
        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i][2].equals(campusId)) return i;
        }
        return -1;
    }

    public static int indexOf(String campusId, String[][] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i][2].equals(campusId)) return i;
        }
        return -1;
    }

    public static boolean addWaitlist(String name, String email, String campusId,
                                      boolean prerequisiteSatisfied, String[][] waitlist) {
        if (!prerequisiteSatisfied) {
            System.out.println("Error: You do not meet the prerequisite(s) for this course.");
            return false;
        }

        if (indexOf(campusId, waitlist) != -1) {
            System.out.println("Error: You are already in the waitlist of this course.");
            return false;
        }

        for (int i = 0; i < waitlist.length; i++) {
            if (waitlist[i] == null) {
                waitlist[i] = new String[]{name, email, campusId};
                System.out.println("You are successfully added to the waitlist of this course.");
                return true;
            }
        }

        System.out.println("Error: Course closed! Waitlist full.");
        return false;
    }

    public static int enrollOneStudent(String name, String email, String campusId,
                                       boolean prerequisiteSatisfied, String[][] roster, int size, String[][] waitlist) {
        if (!prerequisiteSatisfied) {
            System.out.println("Error: You do not meet the prerequisite(s) for this course.");
            return size;
        }

        if (indexOf(campusId, roster, size) != -1) {
            System.out.println("Error: You are already enrolled in this class.");
            return size;
        }

        if (size >= roster.length) {
            System.out.println("The course is full. Please make another selection or try adding yourself to the waitlist.");
            return size;
        }

        roster[size] = new String[]{name, email, campusId};

        int waitlistIndex = indexOf(campusId, waitlist);
        if (waitlistIndex != -1) {
            waitlist[waitlistIndex] = null;
        }

        System.out.println("You are successfully enrolled in this class.");
        return size + 1;
    }

    public static int dropCourse(String campusId, String[][] roster, int size) {
        int index = indexOf(campusId, roster, size);
        if (index == -1) {
            System.out.println("Error: You are not enrolled in the course!");
            return size;
        }

        for (int i = index; i < size - 1; i++) {
            roster[i] = roster[i + 1];
        }
        roster[size - 1] = null;

        System.out.println("You just dropped the course. This action cannot be revoked.");
        return size - 1;
    }
}