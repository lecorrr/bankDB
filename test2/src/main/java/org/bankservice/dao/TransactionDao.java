package org.bankservice.dao;


import org.bankservice.entity.Transaction;

public class TransactionDao extends BaseDao<Transaction> {
    public TransactionDao() {
        super(Transaction.class);
    }
}