import java.util.Scanner;

public class STUDENT_GRADE_CALCULATOR {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numSubjects;

        System.out.println("Welcome to the Marks & Grade Calculator!");
        System.out.print("Enter the number of subjects: ");

        while (true) {
            try {
                numSubjects = Integer.parseInt(scanner.nextLine());
                if (numSubjects <= 0) {
                    System.out.print("Number of subjects must be positive. Try again: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("That's not a valid number. Try again: ");
            }
        }

        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
            while (true) {
                try {
                    int mark = Integer.parseInt(scanner.nextLine());
                    if (mark < 0 || mark > 100) {
                        System.out.print("Marks should be between 0 and 100. Try again: ");
                        continue;
                    }
                    marks[i] = mark;
                    totalMarks += mark;
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number between 0 and 100: ");
                }
            }
        }

        double average = (double) totalMarks / numSubjects;
        String grade = calculateGrade(average);

        // Display Results
        System.out.println("\nResults Summary:");
        System.out.println("------------------------------");
        System.out.println("Total Marks        : " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Average Percentage : %.2f%%\n", average);
        System.out.println("🎓 Grade            : " + grade);
        System.out.println("------------------------------");
        System.out.println("Well done and keep learning!");

        scanner.close();
    }

    public static String calculateGrade(double percentage) {
        if (percentage >= 90)
            return "A+";
        else if (percentage >= 80)
            return "A";
        else if (percentage >= 70)
            return "B";
        else if (percentage >= 60)
            return "C";
        else if (percentage >= 50)
            return "D";
        else
            return "F";
    }
}
