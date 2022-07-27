package com.mysite.mm.store;

import com.mysite.mm.DataNotFoundException;
import com.mysite.mm.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public Store create(String name, Double lat, Double lng, SiteUser user) {
        Store store = new Store();
        store.setName(name);
        store.setLat(lat);
        store.setLng(lng);
        store.setRegistrant(user);
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

    public Store getStore(String name, Double lat, Double lng) {
        Optional<Store> store = this.storeRepository.findByNameAndLatAndLng(name, lat, lng);
        if(store.isPresent()) {
            return store.get();
        } else {
            throw new DataNotFoundException("store not found");
        }
    }

    public List<Store> getList() {
        return this.storeRepository.findAll();
    }

    public void modify(Store store, String name,
                       Double lat, Double lng) {
        store.setName(name);
        store.setLat(lat);
        store.setLng(lng);
        this.storeRepository.save(store);
    }

    public void delete(Store store) {
        this.storeRepository.delete(store);
    }
}
