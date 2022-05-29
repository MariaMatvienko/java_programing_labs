package lab2;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalcValue {

    public static double calcExactValue(double x) {
        return (x - 1) * Math.log(Math.pow(x, 2) - 2 * x + 2);
    }

    public static double calcApproxValue(double x, int n) {
        double S = 0d;

        for (int i = 1; i <= n; i++) {
            S += calcS(x, i);
        }

        return S;
    }

    public static double calcApproxValue(double x, double e) {
        double S = 0d;
        int step = 1;

        do {
            double previousValue = S;

            S += calcS(x, step++);

            if (previousValue - S <= e && previousValue != 0) {
                break;
            }
        } while (true);

        return S;
    }

    public static double calcS(double x, int n) {
        return (Math.pow(x, n) * Math.cos(n * (Math.PI / 3))) / n;
    }

    public static BigDecimal calcExactValue(BigDecimal x) {
        // (x - 1) * Math.log(Math.pow(x, 2) - 2 * x + 2)
        return x.subtract(BigDecimal.valueOf(1))
                .multiply(BigDecimal.valueOf(
                        Math.log(x.pow(2).subtract(x.multiply(BigDecimal.valueOf(2))).add(BigDecimal.valueOf(2)).doubleValue())
                ));
    }

    public static BigDecimal calcApproxValue(BigDecimal x, int n) {
        BigDecimal S = BigDecimal.valueOf(0d);

        for (int i = 1; i <= n; i++) {
            S = S.add(calcS(x, i));
        }

        return S;
    }

    public static BigDecimal calcApproxValue(BigDecimal x, double e) {
        BigDecimal S = BigDecimal.valueOf(0d);
        int step = 1;

        do {
            BigDecimal previousValue = S;

            S = S.add(calcS(x, step++));

            if (previousValue.subtract(S).compareTo(BigDecimal.valueOf(e)) < 0 && previousValue.compareTo(BigDecimal.valueOf(0)) != 0) {
                break;
            }
        } while (true);

        return S;
    }

    public static BigDecimal calcS(BigDecimal x, int n) {
        return x.pow(n).multiply(BigDecimal.valueOf(Math.cos(n * (Math.PI / 3)))).divide(BigDecimal.valueOf(n), MathContext.DECIMAL128);
    }

}
