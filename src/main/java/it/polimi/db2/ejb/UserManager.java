package it.polimi.db2.ejb;

import it.polimi.db2.entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class UserManager {

    @PersistenceContext(unitName = "projectPersistenceUnit")
    private EntityManager em;

    public UserManager() {
    }

    public UserEntity checkCredentials(String username, String password) throws Exception {

        List<UserEntity> uList = null;

        try {

            uList = em.createNamedQuery("UserEntity.checkLogin", UserEntity.class).setParameter(1, username).setParameter(2, password)
                    .getResultList();
        } catch (PersistenceException e) {

            e.printStackTrace();

            throw new Exception("Could not verify credentials");
        }
        if (uList.isEmpty())
            return null;
        else if (uList.size() == 1)
            return uList.get(0);
        throw new NonUniqueResultException("More than one user registered with same credentials");

    }

    //TBD, don't even know if really needed
    private void registerUser(String username, String password, String email) throws Exception {

    }

    //TBD
    //when he uses offensive words
    public void banUser (UserEntity user) {}

}