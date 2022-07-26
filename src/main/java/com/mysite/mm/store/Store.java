package com.mysite.mm.store;

import java.util.Set;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import com.mysite.mm.user.SiteUser;
import com.mysite.mm.menu.Menu;

@Getter
@Setter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String name;

    private Double lat;
    private Double lng;

    @ManyToOne
    private SiteUser registrant;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Menu> menuList;
}
