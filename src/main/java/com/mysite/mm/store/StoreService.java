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

    public Store create(String name, Double location_x, Double location_y) {
        Store store = new Store();
        store.setName(name);
        store.setLocation_x(location_x);
        store.setLocation_y(location_y);
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

    public void modify(Store store, String name,
                       Double location_x, Double location_y) {
        store.setName(name);
        store.setLocation_x(location_x);
        store.setLocation_y(location_y);
        this.storeRepository.save(store);
    }

    public void delete(Store store) {
        this.storeRepository.delete(store);
    }
}
