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

    @Column(columnDefinition = "TEXT")
    private String review;

    private Double location_y;
    private Double location_x;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Menu> menuList;

    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;
}
