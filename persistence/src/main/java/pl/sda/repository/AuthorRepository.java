package pl.sda.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.model.Author;
import pl.sda.util.HibernateUtil;

public class AuthorRepository implements IAuthorRepository {


    @Override
    public void save(Author author) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            session.persist(author);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public Author find(Long authorId) {
        Transaction tx = null;
        Author author = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("select a from Author a where a.id = :id")
                    .setParameter("id", authorId);
            author = (Author) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return author;
    }
}
