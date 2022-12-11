package org.fasttrackit.Spring.Data.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String product;
    @Column(columnDefinition = "varchar(4)")
    private Type type;
    @Column
    private double amount;
}
