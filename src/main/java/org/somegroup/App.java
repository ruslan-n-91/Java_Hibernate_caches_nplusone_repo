package org.somegroup;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.somegroup.model.Book;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Book.class);

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

        // clearing the 2nd level cache so it does not interfere with the 1st level cache demonstration
        clearSecondLevelCache(sessionFactory);

        Session sessionFirstLevelCacheDemo = sessionFactory.openSession();
        sessionFirstLevelCacheDemo.beginTransaction();
        System.out.println("-New session created");
        System.out.println("-1st level cache is clear");

        Book book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
        System.out.println(book1);

        Book book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
        System.out.println(book2);

        Book book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
        System.out.println(book3);

        // clearing the 2nd level cache so it does not interfere with the 1st level cache demonstration
        clearSecondLevelCache(sessionFactory);

        System.out.println("-3 books are in 1st level cache");
        System.out.println("-Getting books from 1st level cache");

        book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
        System.out.println(book1);

        book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
        System.out.println(book2);

        book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
        System.out.println(book3);

        sessionFirstLevelCacheDemo.getTransaction().commit();
        sessionFirstLevelCacheDemo.close();
        System.out.println("-Session closed");

        System.out.println("------------------------------------------------");

        // clearing the 2nd level cache so it does not interfere with the 1st level cache demonstration
        clearSecondLevelCache(sessionFactory);

        sessionFirstLevelCacheDemo = sessionFactory.openSession();
        sessionFirstLevelCacheDemo.beginTransaction();
        System.out.println("-New session created");
        System.out.println("-1st level cache is clear");

        book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
        System.out.println(book1);

        book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
        System.out.println(book2);

        book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
        System.out.println(book3);

        // clearing the 2nd level cache so it does not interfere with the 1st level cache demonstration
        clearSecondLevelCache(sessionFactory);

        System.out.println("-3 books are in 1st level cache");
        System.out.println("-Getting books from 1st level cache");

        book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
        System.out.println(book1);

        book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
        System.out.println(book2);

        book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
        System.out.println(book3);

        sessionFirstLevelCacheDemo.getTransaction().commit();
        sessionFirstLevelCacheDemo.close();
        System.out.println("-Session closed");
        System.out.println("------------------------------------------------");

        // clearing the 2nd level cache so it does not interfere with the 1st level cache demonstration
        clearSecondLevelCache(sessionFactory);
    }

    private static void secondLevelCacheDemo(SessionFactory sessionFactory) {
        System.out.println("Second level cache demonstration");
        System.out.println("------------------------------------------------");

        clearSecondLevelCache(sessionFactory);

        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        Session session3 = sessionFactory.openSession();

        System.out.println("-Three sessions created");
        System.out.println("-2nd level cache is clear");

        session1.beginTransaction();
        session2.beginTransaction();
        session3.beginTransaction();

        Book book4 = session1.get(Book.class, 4);
        System.out.println(book4);
        Book book5 = session1.get(Book.class, 5);
        System.out.println(book5);
        Book book6 = session1.get(Book.class, 6);
        System.out.println(book6);

        System.out.println("-3 books are in 2nd level cache");
        System.out.println("-Getting books from 2nd level cache");

        book4 = session2.get(Book.class, 4);
        System.out.println(book4);
        book5 = session2.get(Book.class, 5);
        System.out.println(book5);
        book6 = session2.get(Book.class, 6);
        System.out.println(book6);

        System.out.println("-Getting books from 2nd level cache");

        book4 = session3.get(Book.class, 4);
        System.out.println(book4);
        book5 = session3.get(Book.class, 5);
        System.out.println(book5);
        book6 = session3.get(Book.class, 6);
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
}
