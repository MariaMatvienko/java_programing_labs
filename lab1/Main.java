package lab1;
public class Main {
    private static final double gradesInDegree = 1.11;
    public static void main(String[] args) {

        System.out.println("Matvienko M.K. 122-20ck-1 #21");

        double x = 122 + 20 + 21;
        double y = convertToElbow(x);
        double z = convertToFathom(x);

        System.out.printf("X = %f cm\n", x);
        System.out.printf("Y = %f elbows\n", y, x);
        System.out.printf("Z = %f fathoms", z, x);
    }

    public static double convertToElbow(double degrees) {
        return degrees / 73.2;
    }

    public static double convertToFathom(double degrees) {
        return degrees / (3 * 73.2);
    }

}
