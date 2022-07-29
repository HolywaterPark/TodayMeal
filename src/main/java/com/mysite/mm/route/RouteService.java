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

    public Integer create(String name, String explanation,
                          SiteUser user) {
        Route route = new Route();
        route.setName(name);
        route.setExplanation(explanation);
        route.setAuthor(user);
        return this.routeRepository.save(route).getId();
    }

    public void addStore(Route route, Store store){
        route.getStoreList().add(store);
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
