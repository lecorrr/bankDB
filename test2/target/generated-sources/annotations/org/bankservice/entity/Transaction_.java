package org.bankservice.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Transaction.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Transaction_ {

	
	/**
	 * @see org.bankservice.entity.Transaction#amount
	 **/
	public static volatile SingularAttribute<Transaction, Double> amount;
	
	/**
	 * @see org.bankservice.entity.Transaction#id
	 **/
	public static volatile SingularAttribute<Transaction, Long> id;
	
	/**
	 * @see org.bankservice.entity.Transaction#type
	 **/
	public static volatile SingularAttribute<Transaction, String> type;
	
	/**
	 * @see org.bankservice.entity.Transaction
	 **/
	public static volatile EntityType<Transaction> class_;
	
	/**
	 * @see org.bankservice.entity.Transaction#account
	 **/
	public static volatile SingularAttribute<Transaction, Account> account;
	
	/**
	 * @see org.bankservice.entity.Transaction#timestamp
	 **/
	public static volatile SingularAttribute<Transaction, LocalDateTime> timestamp;

	public static final String AMOUNT = "amount";
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String ACCOUNT = "account";
	public static final String TIMESTAMP = "timestamp";

}

