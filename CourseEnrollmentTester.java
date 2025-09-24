import java.util.Arrays;

public class CourseEnrollmentTester {

  public static boolean createEmptyListTester() {
    String errMsg = "Bug detected: createEmptyList did not return the expected array.";
    String[][] actual = CourseEnrollment.createEmptyList(5);
    String[][] expected = new String[5][];
    if (!Arrays.deepEquals(actual, expected)) {
      System.out.println(errMsg);
      System.out.println("Expected: " + Arrays.deepToString(expected));
      System.out.println("Actual: " + Arrays.deepToString(actual));
      return false;
    }
    actual = CourseEnrollment.createEmptyList(8);
    expected = new String[8][];
    if (!Arrays.deepEquals(actual, expected)) {
      System.out.println(errMsg);
      System.out.println("Expected: " + Arrays.deepToString(expected));
      System.out.println("Actual: " + Arrays.deepToString(actual));
      return false;
    }
    return true;
  }

  public static boolean indexOfPerfectSizeArrayTester() {
    String errMsg = "Bug detected: indexOf(String, String[][]) did not return the expected result.";
    String[][] testList = new String[][] {
            {"Alice", "alice@wisc.edu", "1111111111"},
            null,
            {"Bob", "bob@wisc.edu", "2222222222"},
            {"Charlie", "charlie@wisc.edu", "3333333333"}
    };
    String[][] originalList = new String[][] {
            {"Alice", "alice@wisc.edu", "1111111111"},
            null,
            {"Bob", "bob@wisc.edu", "2222222222"},
            {"Charlie", "charlie@wisc.edu", "3333333333"}
    };
    int actual = CourseEnrollment.indexOf("1111111111", testList);
    int expected = 0;
    if (actual != expected) {
      System.out.println(errMsg);
      System.out.println("Test case 1 - Expected: " + expected + ", Actual: " + actual);
      return false;
    }
    actual = CourseEnrollment.indexOf("3333333333", testList);
    expected = 3;
    if (actual != expected) {
      System.out.println(errMsg);
      System.out.println("Test case 2 - Expected: " + expected + ", Actual: " + actual);
      return false;
    }
    actual = CourseEnrollment.indexOf("2222222222", testList);
    expected = 2;
    if (actual != expected) {
      System.out.println(errMsg);
      System.out.println("Test case 3 - Expected: " + expected + ", Actual: " + actual);
      return false;
    }
    actual = CourseEnrollment.indexOf("9999999999", testList);
    expected = -1;
    if (actual != expected) {
      System.out.println(errMsg);
      System.out.println("Test case 4 - Expected: " + expected + ", Actual: " + actual);
      return false;
    }
    if (!Arrays.deepEquals(testList, originalList)) {
      System.out.println("Bug detected: indexOf method modified the input array.");
      return false;
    }
    return true;
  }

  public static boolean indexOfOversizeSizeArrayTester() {
    String errMsg = "Bug detected: indexOf(String, String[][], int) did not return the expected result.";
    String[][] testList = new String[6][];
    testList[0] = new String[]{"Alice", "alice@wisc.edu", "1111111111"};
    testList[1] = new String[]{"Bob", "bob@wisc.edu", "2222222222"};
    testList[2] = new String[]{"Charlie", "charlie@wisc.edu", "3333333333"};
    testList[3] = new String[]{"Diana", "diana@wisc.edu", "4444444444"};
    int size = 4;
    String[][] originalList = new String[6][];
    originalList[0] = new String[]{"Alice", "alice@wisc.edu", "1111111111"};
    originalList[1] = new String[]{"Bob", "bob@wisc.edu", "2222222222"};
    originalList[2] = new String[]{"Charlie", "charlie@wisc.edu", "3333333333"};
    originalList[3] = new String[]{"Diana", "diana@wisc.edu", "4444444444"};
    int actual = CourseEnrollment.indexOf("1111111111", testList, size);
    int expected = 0;
    if (actual != expected) {
      System.out.println(errMsg);
      System.out.println("Test case 1 - Expected: " + expected + ", Actual: " + actual);
      return false;
    }
    actual = CourseEnrollment.indexOf("4444444444", testList, size);
    expected = 3;
    if (actual != expected) {
      System.out.println(errMsg);
      System.out.println("Test case 2 - Expected: " + expected + ", Actual: " + actual);
      return false;
    }
    actual = CourseEnrollment.indexOf("2222222222", testList, size);
    expected = 1;
    if (actual != expected) {
      System.out.println(errMsg);
      System.out.println("Test case 3 - Expected: " + expected + ", Actual: " + actual);
      return false;
    }
    actual = CourseEnrollment.indexOf("9999999999", testList, size);
    expected = -1;
    if (actual != expected) {
      System.out.println(errMsg);
      System.out.println("Test case 4 - Expected: " + expected + ", Actual: " + actual);
      return false;
    }
    if (!Arrays.deepEquals(testList, originalList)) {
      System.out.println("Bug detected: indexOf method modified the input array.");
      return false;
    }
    return true;
  }

  private static boolean assessDeepEqualOversizeArraysHelper(String methodName,
                                                             String[][] actualRoster, String[][] expectedRoster, int actualSize, int expectedSize) {
    String errMsgBadSize =
            "Bug detected: Your " + methodName + "() method did not return the expected size.";
    String errMsgBadRoster = "Bug detected: The contents of the roster array was not as expected "
            + "after " + "your " + methodName + "() method returned.";
    if (actualSize != expectedSize) {
      System.out.println(errMsgBadSize);
      System.out.println("Expected size: " + expectedSize + ". Actual size: " + actualSize);
      return false;
    }
    if (!Arrays.deepEquals(actualRoster, expectedRoster)) {
      System.out.println(errMsgBadRoster);
      System.out.println("Expected Roster: " + Arrays.deepToString(expectedRoster));
      System.out.println("Actual Roster: " + Arrays.deepToString(actualRoster));
      return false;
    }
    return true;
  }

  private static boolean assessDeepEqualPerfectSizeArraysHelper(String methodName,
                                                                String[][] actualWaitlist, String[][] expectedWaitlist) {
    String errMsgBadWaitlist =
            "Bug detected: The contents of the waitlist array was not as expected after " + "your "
                    + methodName + "() method returned";
    if (!Arrays.deepEquals(actualWaitlist, expectedWaitlist)) {
      System.out.println(errMsgBadWaitlist);
      System.out.println("Expected Waitlist: " + Arrays.deepToString(expectedWaitlist));
      System.out.println("Actual Waitlist: " + Arrays.deepToString(actualWaitlist));
      return false;
    }
    return true;
  }

  public static boolean enrollOneStudentTester() {
    String[][] actualWaitlist = new String[][] {{"Andy", "andy@wisc.edu", "9087654321"},
            {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};
    String[][] expectedWaitlist = new String[][] {{"Andy", "andy@wisc.edu", "9087654321"},
            {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};
    String[][] actualRoster = new String[3][];
    int actualSize = 0;
    actualSize = CourseEnrollment.enrollOneStudent("George", "george@wisc.edu", "9780563421", true,
            actualRoster, actualSize, actualWaitlist);
    String[][] expectedRoster =
            new String[][] {{"George", "george@wisc.edu", "9780563421"}, null, null};
    int expectedSize = 1;
    boolean resultCase1 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
            expectedRoster, actualSize, expectedSize);
    actualRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
            {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};
    actualSize = 2;
    actualSize = CourseEnrollment.enrollOneStudent("Matt", "matt@wisc.edu", "9745632180", true,
            actualRoster, actualSize, actualWaitlist);
    expectedRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
            {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"}, null};
    expectedSize = 3;
    boolean resultCase2 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
            expectedRoster, actualSize, expectedSize);
    actualRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
            {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"}, null};
    actualSize = 3;
    actualSize = CourseEnrollment.enrollOneStudent("Lisa", "lisa@wisc.edu", "9784563211", true,
            actualRoster, actualSize, actualWaitlist);
    expectedRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
            {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"},
            {"Lisa", "lisa@wisc.edu", "9784563211"}};
    expectedSize = 4;
    boolean resultCase3 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
            expectedRoster, actualSize, expectedSize);
    boolean assessWaitlistContents = assessDeepEqualPerfectSizeArraysHelper("enrollOneStudent",
            actualWaitlist, expectedWaitlist);
    return resultCase1 && resultCase2 && resultCase3 && assessWaitlistContents;
  }

  public static boolean enrollOneStudentMoveFromWaitlistTester() {
    String[][] actualWaitlist = new String[][] {
            {"Andy", "andy@wisc.edu", "9087654321"},
            {"Sarah", "sarah@wisc.edu", "9876543210"},
            null,
            null
    };
    String[][] actualRoster = new String[][] {
            {"George", "george@wisc.edu", "9780563421"},
            null,
            null,
            null
    };
    int actualSize = 1;
    actualSize = CourseEnrollment.enrollOneStudent("Sarah", "sarah@wisc.edu", "9876543210", true,
            actualRoster, actualSize, actualWaitlist);
    String[][] expectedRoster = new String[][] {
            {"George", "george@wisc.edu", "9780563421"},
            {"Sarah", "sarah@wisc.edu", "9876543210"},
            null,
            null
    };
    int expectedSize = 2;
    String[][] expectedWaitlist = new String[][] {
            {"Andy", "andy@wisc.edu", "9087654321"},
            null,
            null,
            null
    };
    boolean rosterResult = assessDeepEqualOversizeArraysHelper("enrollOneStudent",
            actualRoster, expectedRoster, actualSize, expectedSize);
    boolean waitlistResult = assessDeepEqualPerfectSizeArraysHelper("enrollOneStudent",
            actualWaitlist, expectedWaitlist);
    return rosterResult && waitlistResult;
  }

  public static boolean successfulDropCourseTester() {
    String[][] actualRoster1 = new String[][] {
            {"Alice", "alice@wisc.edu", "1111111111"},
            {"Bob", "bob@wisc.edu", "2222222222"},
            {"Charlie", "charlie@wisc.edu", "3333333333"},
            null
    };
    int actualSize1 = 3;
    actualSize1 = CourseEnrollment.dropCourse("1111111111", actualRoster1, actualSize1);
    String[][] expectedRoster1 = new String[][] {
            {"Bob", "bob@wisc.edu", "2222222222"},
            {"Charlie", "charlie@wisc.edu", "3333333333"},
            null,
            null
    };
    int expectedSize1 = 2;
    boolean result1 = assessDeepEqualOversizeArraysHelper("dropCourse",
            actualRoster1, expectedRoster1, actualSize1, expectedSize1);
    String[][] actualRoster2 = new String[][] {
            {"Alice", "alice@wisc.edu", "1111111111"},
            {"Bob", "bob@wisc.edu", "2222222222"},
            {"Charlie", "charlie@wisc.edu", "3333333333"},
            null
    };
    int actualSize2 = 3;
    actualSize2 = CourseEnrollment.dropCourse("2222222222", actualRoster2, actualSize2);
    String[][] expectedRoster2 = new String[][] {
            {"Alice", "alice@wisc.edu", "1111111111"},
            {"Charlie", "charlie@wisc.edu", "3333333333"},
            null,
            null
    };
    int expectedSize2 = 2;
    boolean result2 = assessDeepEqualOversizeArraysHelper("dropCourse",
            actualRoster2, expectedRoster2, actualSize2, expectedSize2);
    String[][] actualRoster3 = new String[][] {
            {"Alice", "alice@wisc.edu", "1111111111"},
            {"Bob", "bob@wisc.edu", "2222222222"},
            {"Charlie", "charlie@wisc.edu", "3333333333"},
            null
    };
    int actualSize3 = 3;
    actualSize3 = CourseEnrollment.dropCourse("3333333333", actualRoster3, actualSize3);
    String[][] expectedRoster3 = new String[][] {
            {"Alice", "alice@wisc.edu", "1111111111"},
            {"Bob", "bob@wisc.edu", "2222222222"},
            null,
            null
    };
    int expectedSize3 = 2;
    boolean result3 = assessDeepEqualOversizeArraysHelper("dropCourse",
            actualRoster3, expectedRoster3, actualSize3, expectedSize3);
    return result1 && result2 && result3;
  }

  public static boolean unsuccessfulDropCourseTester() {
    String[][] actualRoster = new String[][] {
            {"Alice", "alice@wisc.edu", "1111111111"},
            {"Bob", "bob@wisc.edu", "2222222222"},
            {"Charlie", "charlie@wisc.edu", "3333333333"},
            null
    };
    int actualSize = 3;
    String[][] expectedRoster = new String[][] {
            {"Alice", "alice@wisc.edu", "1111111111"},
            {"Bob", "bob@wisc.edu", "2222222222"},
            {"Charlie", "charlie@wisc.edu", "3333333333"},
            null
    };
    int expectedSize = 3;
    actualSize = CourseEnrollment.dropCourse("9999999999", actualRoster, actualSize);
    return assessDeepEqualOversizeArraysHelper("dropCourse",
            actualRoster, expectedRoster, actualSize, expectedSize);
  }

  public static boolean runAllTests() {
    boolean createEmptyListTesterResult = createEmptyListTester();
    System.out
            .println("createEmptyListTester: " + (createEmptyListTesterResult ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    boolean indexOfOversizeSizeArrayTesterResult = indexOfOversizeSizeArrayTester();
    System.out.println("indexOfOversizeSizeArrayTester: "
            + (indexOfOversizeSizeArrayTesterResult ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    boolean indexOfPerfectSizeArrayTesterResult = indexOfPerfectSizeArrayTester();
    System.out.println("indexOfPerfectSizeArrayTester: "
            + (indexOfPerfectSizeArrayTesterResult ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    boolean enrollOneStudentTesterResult = enrollOneStudentTester();
    System.out
            .println("enrollOneStudentTester: " + (enrollOneStudentTesterResult ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    boolean enrollOneStudentMoveFromWaitlistTesterResult = enrollOneStudentMoveFromWaitlistTester();
    System.out.println("enrollOneStudentMoveFromWaitlistTester: "
            + (enrollOneStudentMoveFromWaitlistTesterResult ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    boolean successfulDropCourseTesterResult = successfulDropCourseTester();
    System.out.println(
            "successfulDropCourseTester: " + (successfulDropCourseTesterResult ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    boolean unsuccessfulDropCourseTesterResult = unsuccessfulDropCourseTester();
    System.out.println("unsuccessfulDropCourseTester: "
            + (unsuccessfulDropCourseTesterResult ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    return createEmptyListTesterResult && indexOfOversizeSizeArrayTesterResult
            && indexOfPerfectSizeArrayTesterResult && enrollOneStudentTesterResult
            && enrollOneStudentMoveFromWaitlistTesterResult && successfulDropCourseTesterResult
            && unsuccessfulDropCourseTesterResult;
  }

  public static void main(String[] args) {
    System.out.println("-----------------------------------------------");
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }
}