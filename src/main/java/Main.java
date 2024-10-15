import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FibonacciRepository repository = new FibonacciRepository();
        FibonacciService service = new FibonacciService(repository);
        FibonacciController controller = new FibonacciController(service);

        try {
            controller.handleRequest(14);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

//Мейн, выводит индекс в фибоначи
//Если IOException то выводи ошибку