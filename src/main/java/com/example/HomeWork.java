package com.example;

/**
 * Класс, реализующий логику домашненго задания о модульных тестах
 */
public class HomeWork {

    private HomeWork(){}
    /**
     * Коэффициент, определяющий погрешность при вычислении равенства нулю
     */
    public static final double THRESHOLD = 0.0000000001;

    /**
     * Метод, реализующий логику вычисления корней квадратного уравнения
     *
     * @param a коэффициент a квадратного уравнения
     * @param b коэффициент b квадратного уравнения
     * @param c коэффициент c квадратного уравнения
     * @return Массив, содержащий значения корней квадратного уравнения,  [0] - первый корень, [1] - второй корень, если корни отсутствуют озвращается пустой массив
     * @throws IllegalArgumentException, исключение выбрасывается в случае если коэффициент a == 0 или коэффициенты a,b,c не чила или бесконечности
     */
    public static double[] solve(double a, double b, double c) throws IllegalArgumentException {
        if (isZero(a)) {
            throw new IllegalArgumentException("Коэффициент a не должен равняться нулю.");
        }
        checkCoefficient("a", a);
        checkCoefficient("b", b);
        checkCoefficient("c", c);
        double[] resultArray;
        var d = b * b - 4 * a * c;
        if (d > THRESHOLD) {
            var x1 = (-b - Math.sqrt(d)) / (2 * a);
            var x2 = (-b + Math.sqrt(d)) / (2 * a);
            resultArray = new double[]{x1, x2};
        } else if (isZero(d)) {
            var oneValue = -b / (2 * a);
            resultArray = new double[]{oneValue, oneValue};

        } else {
            resultArray = new double[0];
        }
        return resultArray;
    }

    private static void checkCoefficient(String nameCoefficient, double value) throws IllegalArgumentException {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException(String.format("Коэффициент %s не должен быть NaN.", nameCoefficient));
        } else if (Double.isInfinite(value)) {
            throw new IllegalArgumentException(String.format("Коэффициент %s не должен быть бесконечностью.", nameCoefficient));
        }
    }

    private static boolean isZero(double value) {
        return value >= -THRESHOLD && value <= THRESHOLD;
    }
}
