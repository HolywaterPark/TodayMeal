package com.mysite.mm.store;

import com.mysite.mm.user.SiteUser;
import com.mysite.mm.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/store")
@RequiredArgsConstructor
@Controller
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @RequestMapping("/map/{id}")
    public String map(Model model, @PathVariable("id") Integer id) {
        Store store = this.storeService.getStore(id);
        model.addAttribute("store", store);
        return "map";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/register")
    public String storeRegister(StoreForm storeForm) {
        return "store_form";
    }

    // @Valid : Form의 @NotEmpty, @Size 등의 검증 기능이 작동
    // BindingResult : 검증이 수행된 결과

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/register")
    public String storeRegister(@Valid StoreForm storeForm,
                                BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()) {
            return "store_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.storeService.create(storeForm.getName(), storeForm.getLocation_x(), storeForm.getLocation_y());
        System.out.println(storeForm.getName());
        System.out.println(storeForm.getLocation_x());
        System.out.println(storeForm.getLocation_y());
        return String.format("redirect:/store/map/%d",1);
    }
}
