package com.mysite.mm.menu;

import com.mysite.mm.store.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private String servings;

    @ManyToOne
    private Store store;
}
