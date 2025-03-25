package org.somegroup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.internal.SessionImpl;
import org.somegroup.model.Book;

import java.util.List;
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
                    quit - Stop application
                    Type command:
                    """);

                mainMenuCommand = scanner.nextLine();

                switch (mainMenuCommand) {
                    case "1":
                        System.out.println("First level cache demonstration");

                        System.out.println("------------------------------------------------");

                        Session sessionFirstLevelCacheDemo = sessionFactory.openSession();
                        sessionFirstLevelCacheDemo.beginTransaction();
                        System.out.println("-New session created");
                        System.out.println("-1st level cache is clear");

//                        PersistenceContext firstLevelCache = sessionFirstLevelCacheDemo.
//                                unwrap(SessionImpl.class).getPersistenceContextInternal();
//                        System.out.println(firstLevelCache);

//                        List<Book> list = sessionFirstLevelCacheDemo.createQuery(
//                                "select b from Book b", Book.class).getResultList();

                        Book book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
                        System.out.println(book1);

                        Book book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
                        System.out.println(book2);

                        Book book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
                        System.out.println(book3);

                        System.out.println("-3 books are in 1st level cache");
                        System.out.println("-Getting books from 1st level cache");

                        book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
                        System.out.println(book1);

                        book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
                        System.out.println(book2);

                        book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
                        System.out.println(book3);

//                        System.out.println(firstLevelCache);

                        sessionFirstLevelCacheDemo.getTransaction().commit();
                        sessionFirstLevelCacheDemo.close();
                        System.out.println("-Session closed");

                        System.out.println("------------------------------------------------");

                        sessionFirstLevelCacheDemo = sessionFactory.openSession();
                        sessionFirstLevelCacheDemo.beginTransaction();
                        System.out.println("-New session created");
                        System.out.println("-1st level cache is clear");

//                        firstLevelCache = sessionFirstLevelCacheDemo.
//                                unwrap(SessionImpl.class).getPersistenceContextInternal();
//                        System.out.println(firstLevelCache);

//                        List<Book> list = sessionFirstLevelCacheDemo.createQuery(
//                                "select b from Book b", Book.class).getResultList();

                        book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
                        System.out.println(book1);

                        book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
                        System.out.println(book2);

                        book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
                        System.out.println(book3);

                        System.out.println("-3 books are in 1st level cache");
                        System.out.println("-Getting books from 1st level cache");

                        book1 = sessionFirstLevelCacheDemo.get(Book.class, 1);
                        System.out.println(book1);

                        book2 = sessionFirstLevelCacheDemo.get(Book.class, 2);
                        System.out.println(book2);

                        book3 = sessionFirstLevelCacheDemo.get(Book.class, 3);
                        System.out.println(book3);

//                        System.out.println(firstLevelCache);

                        sessionFirstLevelCacheDemo.getTransaction().commit();
                        sessionFirstLevelCacheDemo.close();
                        System.out.println("-Session closed");

                        System.out.println("------------------------------------------------");
                        break;
                    case "2":
                        System.out.println("Second level cache demonstration");

                        break;
                    default:
                        break;
                }
            }
        }

    }
}
