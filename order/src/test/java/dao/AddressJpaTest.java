package dao;

import config.props;
import dao.jpa.AddressDaoJpa;
import org.junit.Before;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressJpaTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(props.testPU);
    private EntityManager em;
    private EntityTransaction tx;
    private AddressDaoJpa ad;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(AddressJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        ad = new AddressDaoJpa();
        ad.setEm(em);

    }

    /**
    @Test
    public void createAndFind() {
        tx.begin();
        Address a = new Address();
        ad.create(a);
        tx.commit();
        Assert.assertNotNull(ad.find(a.getId()));
    }

    @Test
    public void edit() {
        tx.begin();
        Address a = new Address();
        ad.create(a);
        tx.commit();

        tx.begin();
        String id = "testId";
        a.setCity(id);
        ad.edit(a);
        tx.commit();

        Assert.assertEquals(ad.find(a.getId()).getCity(), id);
    }

    @Test
    public void delete() {
        tx.begin();
        Address a = new Address();
        ad.create(a);
        tx.commit();

        tx.begin();
        ad.delete(a);
        tx.commit();

        Assert.assertNull(ad.find(a.getId()));
    }
    **/
}
