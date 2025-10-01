package org.bankservice.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Account.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Account_ {

	
	/**
	 * @see org.bankservice.entity.Account#ownerName
	 **/
	public static volatile SingularAttribute<Account, String> ownerName;
	
	/**
	 * @see org.bankservice.entity.Account#balance
	 **/
	public static volatile SingularAttribute<Account, Double> balance;
	
	/**
	 * @see org.bankservice.entity.Account#id
	 **/
	public static volatile SingularAttribute<Account, Long> id;
	
	/**
	 * @see org.bankservice.entity.Account#accountNumber
	 **/
	public static volatile SingularAttribute<Account, String> accountNumber;
	
	/**
	 * @see org.bankservice.entity.Account#transactions
	 **/
	public static volatile ListAttribute<Account, Transaction> transactions;
	
	/**
	 * @see org.bankservice.entity.Account
	 **/
	public static volatile EntityType<Account> class_;

	public static final String OWNER_NAME = "ownerName";
	public static final String BALANCE = "balance";
	public static final String ID = "id";
	public static final String ACCOUNT_NUMBER = "accountNumber";
	public static final String TRANSACTIONS = "transactions";

}

