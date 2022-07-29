package com.mysite.mm.route;

import com.mysite.mm.store.Store;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class RouteForm {
    @NotEmpty(message="이름은 필수항목입니다.")
    @Size(max=200)
    private String name;

    private String explanation;

    private List<Integer> store_clicked;
}
