package com.mysite.mm.menu;

import com.mysite.mm.store.Store;
import com.mysite.mm.store.StoreService;
import com.mysite.mm.user.SiteUser;
import com.mysite.mm.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/menu")
@RequiredArgsConstructor
@Controller
public class MenuController {

    private final MenuService menuService;
    private final StoreService storeService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createMenu(Model model, @PathVariable("id") Integer id,
                             @Valid MenuForm menuForm, BindingResult bindingResult,
                             Principal principal) {
        Store store = this.storeService.getStore(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("store", store);
            return "menu_register";
        }
        Menu menu = this.menuService.create(store, menuForm.getName(), menuForm.getPrice(), menuForm.getFoodType(), siteUser);
        return String.format("redirect:/store/detail/%s", menu.getStore().getId());
    }
}
