package pl.sda.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.model.Borrower;
import pl.sda.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class BorrowerRepository implements IBorrowerRepository {

    @Override
    public List<Borrower> findAll() {
        List<Borrower> borrower = new ArrayList<>();
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            borrower = session.createQuery("from Borrower").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return borrower;
    }

    @Override
    public Borrower find(Long borrowerId) {
        Transaction tx = null;
        Borrower borrower = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from Borrower b where b.id = :id")
                    .setParameter("id", borrowerId);
            borrower = (Borrower) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return borrower;
    }
}
