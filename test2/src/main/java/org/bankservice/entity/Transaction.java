package org.bankservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String type;  // e.g. "DEPOSIT", "WITHDRAW"

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}