package lab2;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            double start = scanner.nextDouble();
            double end = scanner.nextDouble();
            double step = scanner.nextDouble();
            int n = scanner.nextInt();
            double e = scanner.nextDouble();
            scanner.close();

            for (double x = start; x <= end; x += step) {
                double y = CalcValue.calcExactValue(x);
                double sn = CalcValue.calcApproxValue(x, n);
                double se = sn - y;
                double en = CalcValue.calcApproxValue(x, e);
                double ee = en - y;

                System.out.printf("x = %f, y = %f, sn = %f, se = %f, en = %f, ee = %f\n", x, y, sn, se, en, ee);
            }

            System.out.println("With BigDecimal below");

            for (BigDecimal x = BigDecimal.valueOf(start); x.compareTo(BigDecimal.valueOf(end)) < 0; x = x.add(BigDecimal.valueOf(step))) {
                BigDecimal y = CalcValue.calcExactValue(x);
                BigDecimal sn = CalcValue.calcApproxValue(x, n);
                BigDecimal se = sn.subtract(y);
                BigDecimal en = CalcValue.calcApproxValue(x, e);
                BigDecimal ee = en.subtract(y);

                System.out.printf("x = %f, y = %f, sn = %f, se = %f, en = %f, ee = %f\n", x, y, sn, se, en, ee);
            }
    }
}
