package org.somegroup;

import jakarta.persistence.EntityGraph;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.somegroup.model.*;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Publisher.class);
        configuration.addAnnotatedClass(Magazine.class);

        configuration.addAnnotatedClass(PublisherFetchMode.class);
        configuration.addAnnotatedClass(MagazineFetchMode.class);
        configuration.addAnnotatedClass(PublisherBatchSize.class);
        configuration.addAnnotatedClass(MagazineBatchSize.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Scanner scanner = new Scanner(System.in);
            String mainMenuCommand = "";

            while (!mainMenuCommand.equals("quit")) {
                System.out.println("""
                        MAIN MENU
                        1 - First level cache demonstration
                        2 - Second level cache demonstration
                        3 - N plus one problem solutions
                        quit - Stop application
                        Type command:
                        """);

                mainMenuCommand = scanner.nextLine();

                switch (mainMenuCommand) {
                    case "1":
                        firstLevelCacheDemo(sessionFactory);
                        break;
                    case "2":
                        secondLevelCacheDemo(sessionFactory);
                        break;
                    case "3":
                        nPlusOneSolutionsDemo(sessionFactory);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void firstLevelCacheDemo(SessionFactory sessionFactory) {
        System.out.println("First level cache demonstration");
        System.out.println("------------------------------------------------");

        clearSecondLevelCache(sessionFactory);

        Session sessionFirstLevelCacheDemo = sessionFactory.openSession();
        sessionFirstLevelCacheDemo.beginTransaction();
        System.out.println("-New session created");
        System.out.println("-1st level cache is empty");

        Book book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
        Book book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
        Book book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        clearSecondLevelCache(sessionFactory);

        System.out.println("-3 books are in 1st level cache");
        System.out.println("-Getting books from 1st level cache");

        book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
        book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
        book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        sessionFirstLevelCacheDemo.close();
        System.out.println("-Session closed");

        System.out.println("------------------------------------------------");

        clearSecondLevelCache(sessionFactory);

        sessionFirstLevelCacheDemo = sessionFactory.openSession();
        sessionFirstLevelCacheDemo.beginTransaction();
        System.out.println("-New session created");
        System.out.println("-1st level cache is empty");

        book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
        book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
        book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        clearSecondLevelCache(sessionFactory);

        System.out.println("-3 books are in 1st level cache");
        System.out.println("-Getting books from 1st level cache");

        book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
        book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
        book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        sessionFirstLevelCacheDemo.close();
        System.out.println("-Session closed");
        System.out.println("------------------------------------------------");

        clearSecondLevelCache(sessionFactory);
    }

    private static void secondLevelCacheDemo(SessionFactory sessionFactory) {
        System.out.println("Second level cache demonstration");
        System.out.println("------------------------------------------------");

        clearSecondLevelCache(sessionFactory);

        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        Session session3 = sessionFactory.openSession();

        System.out.println("-Three sessions are created");
        System.out.println("-2nd level cache is empty");

        session1.beginTransaction();
        session2.beginTransaction();
        session3.beginTransaction();

        Book book4 = session1.get(Book.class, 4);
        Book book5 = session1.get(Book.class, 5);
        Book book6 = session1.get(Book.class, 6);
        System.out.println(book4);
        System.out.println(book5);
        System.out.println(book6);

        System.out.println("-3 books are in 2nd level cache");
        System.out.println("-Getting books from 2nd level cache");

        book4 = session1.get(Book.class, 4);
        book5 = session1.get(Book.class, 5);
        book6 = session1.get(Book.class, 6);
        System.out.println(book4);
        System.out.println(book5);
        System.out.println(book6);

        System.out.println("-Getting books from 2nd level cache");

        book4 = session1.get(Book.class, 4);
        book5 = session1.get(Book.class, 5);
        book6 = session1.get(Book.class, 6);
        System.out.println(book4);
        System.out.println(book5);
        System.out.println(book6);

        session1.close();
        session2.close();
        session3.close();

        System.out.println("-Three sessions closed");
        System.out.println("------------------------------------------------");

        clearSecondLevelCache(sessionFactory);
    }

    private static void clearSecondLevelCache(SessionFactory sessionFactory) {
        Cache cache = sessionFactory.getCache();

        if (cache != null) {
            cache.evictAllRegions();
        }
    }

    private static void nPlusOneSolutionsDemo(SessionFactory sessionFactory) {
        System.out.println("N plus one problem solutions");
        System.out.println("------------------------------------------------");

        clearSecondLevelCache(sessionFactory);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("-N Plus One problem:");
        System.out.println("-One plus N queries");
        List<Publisher> publishers = session.createQuery("SELECT p FROM Publisher p",
                Publisher.class).getResultList();
        for (Publisher publisher : publishers) {
            System.out.println("-Publisher " + publisher.getName()
                    + " has magazines: " + publisher.getMagazines());
        }
        session.close();

        clearSecondLevelCache(sessionFactory);
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("------------------------------------------------");
        System.out.println("-Solution one: Join fetch");
        System.out.println("-Only one query");
        List<Publisher> publishersJoinFetch = session.createQuery("SELECT p FROM Publisher p" +
                " LEFT JOIN FETCH p.magazines", Publisher.class).getResultList();
        for (Publisher publisher : publishersJoinFetch) {
            System.out.println("-Publisher " + publisher.getName()
                    + " has magazines: " + publisher.getMagazines());
        }
        session.close();

        clearSecondLevelCache(sessionFactory);
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("------------------------------------------------");
        System.out.println("-Solution two: Fetch mode SUBSELECT");
        System.out.println("-One plus one queries");
        List<PublisherFetchMode> publishersFetchMode = session.createQuery("SELECT p FROM PublisherFetchMode p",
                PublisherFetchMode.class).getResultList();
        for (PublisherFetchMode publisher : publishersFetchMode) {
            System.out.println("-Publisher " + publisher.getName()
                    + " has magazines: " + publisher.getMagazines());
        }
        session.close();

        clearSecondLevelCache(sessionFactory);
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("------------------------------------------------");
        System.out.println("-Solution three: Batch size");
        System.out.println("-One plus (N divided by batch size) queries");
        List<PublisherBatchSize> publishersBatchSize = session.createQuery("SELECT p FROM PublisherBatchSize p",
                PublisherBatchSize.class).getResultList();
        for (PublisherBatchSize publisher : publishersBatchSize) {
            System.out.println("-Publisher " + publisher.getName()
                    + " has magazines: " + publisher.getMagazines());
        }
        session.close();

        clearSecondLevelCache(sessionFactory);
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("------------------------------------------------");
        System.out.println("-Solution four: Entity graph");
        System.out.println("-Only one query");
        EntityGraph<Publisher> entityGraph = session.createEntityGraph(Publisher.class);
        entityGraph.addAttributeNodes("magazines");

        List<Publisher> publishersEnityGraph = session.createQuery("SELECT p FROM Publisher p",
                Publisher.class).setHint("jakarta.persistence.fetchgraph", entityGraph).getResultList();
        for (Publisher publisher : publishersEnityGraph) {
            System.out.println("-Publisher " + publisher.getName()
                    + " has magazines: " + publisher.getMagazines());
        }
        session.close();

        System.out.println("------------------------------------------------");
        clearSecondLevelCache(sessionFactory);
    }
}
