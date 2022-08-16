package com.mysite.mm.store;

import com.mysite.mm.user.SiteUser;
import com.mysite.mm.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequestMapping("/store")
@RequiredArgsConstructor
@Controller
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @RequestMapping("/map")
    public String map_list(Model model, @RequestParam(value="kw", defaultValue = "") String kw) {
        List<Store> storeList = this.storeService.getList(kw);
        model.addAttribute("storeList", storeList);
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
        this.storeService.create(storeForm.getName(),
                storeForm.getLat(), storeForm.getLng(), siteUser);
        Store store = this.storeService.getStore(storeForm.getName(), storeForm.getLat(), storeForm.getLng());
        return String.format("redirect:/store/detail/%d", store.getId());
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Store store = this.storeService.getStore(id);
        model.addAttribute("store", store);
        return "store_detail";
    }
}
