package com.mysite.mm.route;

import com.mysite.mm.store.Store;
import com.mysite.mm.store.StoreService;
import com.mysite.mm.user.SiteUser;
import com.mysite.mm.user.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@RequestMapping("/route")
@RequiredArgsConstructor
@Controller
public class RouteController {

    private final RouteService routeService;
    private final UserService userService;
    private final StoreService storeService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/register")
    public String routeRegister(Model model){
        List<Store> storeList = this.storeService.getList();
        model.addAttribute("storeList", storeList);
        return "route_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/register")
    public String routeRegister(@RequestBody RouteRegisterRequestDto routeRegisterRequestDto,
                                Principal principal) {
        SiteUser siteUser = this.userService.getUser(principal.getName());
        String name = routeRegisterRequestDto.getName();
        String explanation = routeRegisterRequestDto.getExplanation();
        List<Integer> storeIdList = routeRegisterRequestDto.getStoreIdList();
        System.out.println(name);
        System.out.println(explanation);
        System.out.println(storeIdList);
        Integer routeId = this.routeService.create(name, explanation, siteUser);
        Route route = this.routeService.getRoute(routeId);
        for(Integer id : storeIdList) {
            Store store = this.storeService.getStore(id);
            this.routeService.addStore(route, store);
        }
        //return String.format("redirect:/route/{%d}", routeId);
        return "redirect:/";
    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/register")
//    public String routeRegister(@RequestBody RouteRegisterRequestDto
//                                 routeRegisterRequestDto,
//                                 Principal principal) {
//        SiteUser siteUser = this.userService.getUser(principal.getName());
//        String name = routeRegisterRequestDto.getName();
//        String explanation = routeRegisterRequestDto.getExplanation();
//        List<Integer> storeList = routeRegisterRequestDto.getStoreIdList();
//        Integer routeId = this.routeService.create(name, explanation, siteUser);
//        Route route = this.routeService.getRoute(routeId);
//        for(Integer id : storeList) {
//            Store store = this.storeService.getStore(id);
//            this.routeService.addStore(route, store);
//        }
//        //return String.format("redirect:/route/{%d}", routeId);
//        return "redirect:/";
//    }

    @RequestMapping(value = "/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Route route = this.routeService.getRoute(id);
        model.addAttribute("route", route);
        return "route_detail";
    }

}
