package pl.knpj.servlet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class with base methods to access data in database
 */
public abstract class BaseDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizowanie");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Method used to select data from database.
     *
     * @param namedQuery name of named query
     * @param resultType class with type of result
     * @param params additional parameters to do query, assigned from 1st position to last
     * @return retrieved and parsed object from db
     */
    protected <T> List<T> executeNamedQuery(String namedQuery, Class<T> resultType, Object... params) {
        EntityManager em = getEntityManager();
        List<T> result = null;
        try {
            TypedQuery<T> query = em.createNamedQuery(namedQuery, resultType);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
            result = query.getResultList();
        } finally {
            em.close();
        }
        return result;
    }

}
