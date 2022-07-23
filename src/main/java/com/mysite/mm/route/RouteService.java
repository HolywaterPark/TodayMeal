package com.mysite.mm.route;

import com.mysite.mm.DataNotFoundException;
import com.mysite.mm.store.Store;
import com.mysite.mm.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RouteService {
    private RouteRepository routeRepository;

    public Route getRoute(Integer id) {
        Optional<Route> route = this.routeRepository.findById(id);
        if(route.isPresent()) {
            return route.get();
        } else {
            throw new DataNotFoundException("route not found");
        }
    }

    public void create(String name, SiteUser user) {
        Route route = new Route();
        route.setName(name);
        route.setAuthor(user);
        this.routeRepository.save(route);
    }

    public void delete(Route route) {
        this.routeRepository.delete(route);
    }

    public void vote(Route route, SiteUser siteUser) {
        route.getVoter().add(siteUser);
        this.routeRepository.save(route);
    }
}
