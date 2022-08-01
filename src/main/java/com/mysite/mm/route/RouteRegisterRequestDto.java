package com.mysite.mm.route;

import com.mysite.mm.store.Store;
import com.mysite.mm.user.SiteUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class RouteRegisterRequestDto {
    private String name;
    private String explanation;
    private List<Integer> storeIdList;

    @Builder
    public RouteRegisterRequestDto(String name, String explanation,
                                   List<Integer> storeIdList) {
        this.name = name;
        this.explanation = explanation;
        this.storeIdList = storeIdList;
    }
}
