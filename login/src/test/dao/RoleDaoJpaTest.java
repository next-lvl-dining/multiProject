package dao;

import dao.interfaces.RoleDao;
import dao.jpa.RoleDaoJpa;
import domain.Role;
import domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("nldTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private RoleDaoJpa roleDao;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDaoJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        roleDao = new RoleDaoJpa();
        roleDao.setEm(em);
    }

    @Test
    public void createAndFindUser() {
        tx.begin();
        Role role = new Role();
        roleDao.create(role);
        tx.commit();
        Assert.assertNotNull(roleDao.find(role.getId()));
    }

    @Test
    public void editUser() {
        tx.begin();
        Role role = new Role();
        roleDao.create(role);
        tx.commit();

        tx.begin();
        String name = "testRole";
        role.setName(name);
        roleDao.edit(role);
        tx.commit();

        Assert.assertEquals(roleDao.find(role.getId()).getName(), name);
    }

    @Test
    public void deleteUser() {
        tx.begin();
        Role role = new Role();
        roleDao.create(role);
        tx.commit();

        tx.begin();
        roleDao.delete(role);
        tx.commit();

        Assert.assertNull(roleDao.find(role.getId()));
    }
}
