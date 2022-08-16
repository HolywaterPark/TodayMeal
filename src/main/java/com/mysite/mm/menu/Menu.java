package com.mysite.mm.menu;

import com.mysite.mm.store.Store;
import com.mysite.mm.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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
    private String foodType;

    @ManyToOne
    private Store store;

    @ManyToOne
    private SiteUser author;
}
