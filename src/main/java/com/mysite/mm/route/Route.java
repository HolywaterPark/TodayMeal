package com.mysite.mm.route;

import com.mysite.mm.store.Store;
import com.mysite.mm.user.SiteUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String name;

    @Column
    private String explanation;

    @OneToMany
    private Set<Store> storeList;

    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;

    @Builder
    public Route(String name, String explanation, Set<Store> storeList, SiteUser author) {
        this.name = name;
        this.explanation = explanation;
        this.storeList = storeList;
        this.author = author;
    }
}
