package com.mysite.mm.route;

import com.mysite.mm.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    List<Route> findByAuthor(SiteUser siteUser);
}
