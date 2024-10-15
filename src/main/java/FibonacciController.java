import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.io.PrintWriter;

public class FibonacciController {
    private FibonacciService service;

    public FibonacciController(FibonacciService service) {
        this.service = service;
    }

    public void handleRequest(int index) throws IOException {
        int result = service.getFibonacciNumber(index);
        PrintWriter writer = new PrintWriter(System.out);
        writer.println("Fibonacci number for index " + index + ": " + result);
        writer.close();
        int id = 0;
        int num = index;
        int fibnum = result;
        Number number = new Number(id, num, fibnum);
        System.out.println(number.toString());

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(number);

        transaction.commit();
        sessionFactory.close();
    }
}

// Здесь идет получение числа, вывод результата в консольб, создание объекта числа для бд и отправка этого числа на бд