package com.mysite.mm.menu;

import com.mysite.mm.DataNotFoundException;
import com.mysite.mm.store.Store;
import com.mysite.mm.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu create(Store store, String name, Integer price, String foodType, SiteUser author) {
        Menu menu = new Menu();
        menu.setStore(store);
        menu.setName(name);
        menu.setPrice(price);
        menu.setFoodType(foodType);
        menu.setAuthor(author);
        this.menuRepository.save(menu);
        return menu;
    }

    public Menu getMenu(Integer id) {
        Optional<Menu> menu = this.menuRepository.findById(id);
        if (menu.isPresent()) {
            return menu.get();
        } else {
            throw new DataNotFoundException("menu not found");
        }
    }
}
