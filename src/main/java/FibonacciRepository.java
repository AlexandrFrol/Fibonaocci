import java.util.ArrayList;
import java.util.List;

public class FibonacciRepository {
    private List<Number> fibonacciNumbers = new ArrayList<Number>(); // Хранение чисел фибоначи

    public void saveFibonacci(Number num) { //добавляет число Фибоначи в репозиторий
        fibonacciNumbers.add(num);
    }
    public Number getFibonacci(int n) { //возвращает число Фибоначи по его порядковому номеру. или нуль
        for (Number num : fibonacciNumbers) {
            if (num.getNum() == n) {
                return num;
            }
        }
        return null;
    }
}