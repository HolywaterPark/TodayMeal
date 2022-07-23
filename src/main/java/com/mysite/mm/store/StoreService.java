package com.mysite.mm.store;

import com.mysite.mm.DataNotFoundException;
import com.mysite.mm.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public Store create(String name, String review,
                        Double location_y, Double location_x,
                        SiteUser author) {
        Store store = new Store();
        store.setName(name);
        store.setReview(review);
        store.setLocation_y(location_y);
        store.setLocation_x(location_x);
        store.setAuthor(author);
        this.storeRepository.save(store);
        return store;
    }

    public Store getStore(Integer id) {
        Optional<Store> store = this.storeRepository.findById(id);
        if(store.isPresent()) {
            return store.get();
        } else {
            throw new DataNotFoundException("store not found");
        }
    }

    public void modify(Store store, String name, String review,
                       Double location_y, Double location_x) {
        store.setName(name);
        store.setReview(review);
        store.setLocation_y(location_y);
        store.setLocation_x(location_x);
        this.storeRepository.save(store);
    }

    public void delete(Store store) {
        this.storeRepository.delete(store);
    }

    public void vote(Store store, SiteUser siteUser) {
        store.getVoter().add(siteUser);
        this.storeRepository.save(store);
    }
}
