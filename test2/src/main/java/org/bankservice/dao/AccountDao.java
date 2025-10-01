package org.bankservice.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.bankservice.entity.Account;
import org.bankservice.persistence.EntityManagerFactoryProvider;

public class AccountDao extends BaseDao<Account> {
    public AccountDao() {
        super(Account.class);
    }

    public Account findByAccountNumber(String accountNumber) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        try {
            TypedQuery<Account> q = em.createQuery(
                    "SELECT a FROM Account a WHERE a.accountNumber = :accNo", Account.class);
            q.setParameter("accNo", accountNumber);
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}