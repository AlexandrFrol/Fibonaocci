import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class FibonacciService {
    private FibonacciRepository repository;

    public FibonacciService(FibonacciRepository repository) {
        this.repository = repository;
    }

    public int getFibonacciNumber(int index) { // Возвращает число по порядковому номеру
        if (index < 1) {
            throw new IllegalArgumentException("Index should be greater or equal to 1");
        }

        Number existingNumber = repository.getFibonacci(index);
        if (existingNumber != null) {
            return existingNumber.getFibnum();
        }

        int fibonacciNumber = FibonacciCalculator.getFibonacciNumber(index);
        Number newNumber = new Number(index, fibonacciNumber);
        repository.saveFibonacci(newNumber);
        return fibonacciNumber;
    }

    public List<Number> getFibonacciNumber() { // возвращает список всех чисел фибоначи в бд
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Number> courseCriteriaQuery = criteriaBuilder.createQuery(Number.class);
        Root<Number> root = courseCriteriaQuery.from(Number.class);
        List<Number> baselist = session.createQuery(courseCriteriaQuery).getResultList();
        transaction.commit();
        session.close();
        return baselist;
    }
}