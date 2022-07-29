package com.mysite.mm.route;

import com.mysite.mm.store.Store;
import com.mysite.mm.store.StoreService;
import com.mysite.mm.user.SiteUser;
import com.mysite.mm.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @ResponseBody
    @PostMapping("/register")
    public String routeRegister(HttpServletRequest request) throws Exception{
        String name = request.getParameter("name");
        String explanation = request.getParameter("explanation");
        String storeList = request.getParameter("storeList");
        System.out.println(name);
        System.out.println(explanation);
        System.out.println(storeList);

        return "redirect:/route/register";
    }


//    @PreAuthorize("isAuthenticated()")
//    @ResponseBody
//    @PostMapping("/register")
//    public String routeRegister(@RequestParam(value="name") String name,
//                                @RequestParam(value="explanation") String explaneation,
//                                @RequestParam(value="store_list") List<Integer> storeList,
//                                BindingResult bindingResult, Principal principal) {
//        if(bindingResult.hasErrors()) {
//            return "route_form";
//        }
//        SiteUser siteUser = this.userService.getUser(principal.getName());
//        Integer routeId = this.routeService.create(name, explaneation, siteUser);
//        Route route = this.routeService.getRoute(routeId);
//        for(Integer id : storeList) {
//            Store store = this.storeService.getStore(id);
//            this.routeService.addStore(route, store);
//        }
//        return String.format("redirect:/route/{%d}", routeId);
//    }

    @RequestMapping(value = "/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Route route = this.routeService.getRoute(id);
        model.addAttribute("route", route);
        return "route_detail";
    }

}
