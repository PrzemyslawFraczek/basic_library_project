package pl.sda.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.model.Borrow;
import pl.sda.util.HibernateUtil;

public class BorrowRepository implements IBorrowRepository {

    @Override
    public void save(Borrow borrow) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            session.persist(borrow);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public Borrow find(Long borrowId) {
        Transaction tx = null;
        Borrow borrow = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from Borrow b where b.id = :id")
                    .setParameter("id", borrowId);
            borrow = (Borrow) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return borrow;
    }

    @Override
    public void delete(Borrow borrow) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            session.delete(borrow);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
