package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.stream.Stream;

class HomeWorkTest {

    /**
     * Проверяется кейс, когда возвращается пустой массив
     */
    @Test
    void noSolution() {
        Assertions.assertEquals(0, HomeWork.solve(1, 0, 1).length);
    }

    /**
     * Проверяется кейс, когда возвращается массив, водержищай два корня кратности 1
     */
    @Test
    void twoSolution() {
        double[] expected = {1d, -1d};
        Assertions.assertArrayEquals(Arrays.stream(expected).sorted().toArray(), Arrays.stream(HomeWork.solve(1, 0, -1)).sorted().toArray());
    }

    /**
     * Проверяется кейс, когда возвращается массив, содержащий один корень крастности 2
     */
    @Test
    void oneSolutionV1() {
        double[] expected = {-1, -1};
        Assertions.assertArrayEquals(Arrays.stream(expected).sorted().toArray(), Arrays.stream(HomeWork.solve(1, 2, 1)).sorted().toArray());
    }

    /**
     * Проверяется кейс, когда возвращается массив, содержащий один корень крастности 2, при этом дискриминант отличен от нуля но меньше заданного
     * коэффициента эпсилон {@link HomeWork#THRESHOLD}
     */
    @Test
    void oneSolutionV2() {
        double[] expected = {-0.5, -0.5};
        Assertions.assertArrayEquals(Arrays.stream(expected).sorted().toArray(), Arrays.stream(HomeWork.solve(1, 1, 0.249999999999)).sorted().toArray());
    }

    /**
     * Проверяется условие, что коэффициент a не может быть равен 0, при этом выбрасыввается исключение {@link IllegalArgumentException}
     */
    @Test
    void coefficientANotBeNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            HomeWork.solve(0, 1, 1);
        }, "Коэффициент a не должен равняться нулю.");
    }

    /**
     * Проверяется условие, что коэффициенты a,b,c не явялется NaN или бесконечностью
     *
     * @param a коэффициент a квадратного уровнения
     * @param b коэффициент b квадратного уровнения
     * @param c коэффициент c квадратного уровнения
     * @param errorMessage сообщение об ошибке
     */
    @ParameterizedTest
    @ArgumentsSource(CoefficientProvider.class)
    void coefficientsMastBeNumeric(double a, double b, double c, String errorMessage) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            HomeWork.solve(a, b, c);
        }, errorMessage);

    }

    private static class CoefficientProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(Double.NaN, 1, 1, "Коэффициент a не должен быть NaN."),
                    Arguments.of(Double.NEGATIVE_INFINITY, 1, 1, "Коэффициент a не должен быть бесконечностью."),
                    Arguments.of(Double.POSITIVE_INFINITY, 1, 1, "Коэффициент a не должен быть бесконечностью."),
                    Arguments.of(1, Double.NaN, 1, "Коэффициент b не должен быть NaN."),
                    Arguments.of(1, Double.NEGATIVE_INFINITY, 1, "Коэффициент b не должен быть бесконечностью."),
                    Arguments.of(1, Double.POSITIVE_INFINITY, 1, "Коэффициент b не должен быть бесконечностью."),
                    Arguments.of(1, 1, Double.NaN, "Коэффициент c не должен быть NaN."),
                    Arguments.of(1, 1, Double.NEGATIVE_INFINITY, "Коэффициент c не должен быть бесконечностью."),
                    Arguments.of(1, 1, Double.POSITIVE_INFINITY, "Коэффициент c не должен быть бесконечностью.")
            );
        }
    }
}
