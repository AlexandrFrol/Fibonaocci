public class FibonacciCalculator {  // Калькулятор вычесления чисел фибоначи
    public static Integer getFibonacciNumber(int index) {
        if (index < 1) throw new IllegalArgumentException("Index should be greater or equal to 1"); //Проверка на корректность
        if (index == 1 || index == 2) {
            return 1;
        }
        return getFibonacciNumber(index - 1) + getFibonacciNumber(index - 2); // Рекурсивный вычисел
    }
}
