package org.bankservice.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.bankservice.dao.AccountDao;
import org.bankservice.dao.TransactionDao;
import org.bankservice.entity.Account;
import org.bankservice.entity.Transaction;
import org.bankservice.persistence.EntityManagerFactoryProvider;

import java.time.LocalDateTime;

public class BankService {
    private final AccountDao accountDao = new AccountDao();
    private final TransactionDao transactionDao = new TransactionDao();

    public void createAccount(String accNo, String owner, double initialBalance) {
        Account existing = accountDao.findByAccountNumber(accNo);
        if (existing != null) {
            throw new IllegalArgumentException("Account already exists: " + accNo);
        }
        Account a = new Account();
        a.setAccountNumber(accNo);
        a.setOwnerName(owner);
        a.setBalance(initialBalance);
        accountDao.persist(a);
    }

    public void deposit(String accNo, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Account a = em.createQuery(
                            "SELECT a FROM Account a WHERE a.accountNumber = :accNo", Account.class)
                    .setParameter("accNo", accNo)
                    .getSingleResult();
            a.setBalance(a.getBalance() + amount);
            em.merge(a);

            Transaction t = new Transaction();
            t.setAccount(a);
            t.setAmount(amount);
            t.setTimestamp(LocalDateTime.now());
            t.setType("DEPOSIT");
            em.persist(t);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void withdraw(String accNo, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Account a = em.createQuery(
                            "SELECT a FROM Account a WHERE a.accountNumber = :accNo", Account.class)
                    .setParameter("accNo", accNo)
                    .getSingleResult();
            if (a.getBalance() < amount) {
                throw new IllegalStateException("Insufficient funds");
            }
            a.setBalance(a.getBalance() - amount);
            em.merge(a);

            Transaction t = new Transaction();
            t.setAccount(a);
            t.setAmount(amount);
            t.setTimestamp(LocalDateTime.now());
            t.setType("WITHDRAW");
            em.persist(t);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public double getBalance(String accNo) {
        Account a = accountDao.findByAccountNumber(accNo);
        if (a == null) {
            throw new IllegalArgumentException("Account not found: " + accNo);
        }
        return a.getBalance();
    }
}
