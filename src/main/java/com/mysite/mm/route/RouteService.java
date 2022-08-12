package com.mysite.mm.route;

import com.mysite.mm.DataNotFoundException;
import com.mysite.mm.store.Store;
import com.mysite.mm.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RouteService {
    private final RouteRepository routeRepository;

    public Route getRoute(Integer id) {
        Optional<Route> route = this.routeRepository.findById(id);
        if(route.isPresent()) {
            return route.get();
        } else {
            throw new DataNotFoundException("route not found");
        }
    }

    public Integer create(String name, String explanation,
                          SiteUser user, List<Store> storeList) {
        Route route = new Route();
        route.setName(name);
        route.setExplanation(explanation);
        route.setAuthor(user);
        route.setStoreList(storeList);
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

    public List<Route> getList() {
        return this.routeRepository.findAll();
    }

    public List<Route> getList(SiteUser siteUser) {
        return this.routeRepository.findByAuthor(siteUser);
    }
}
