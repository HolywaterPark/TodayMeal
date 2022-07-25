package com.mysite.mm.store;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StoreForm {
    @NotEmpty(message="이름은 필수항목입니다.")
    @Size(max=200)
    private String name;

    private Double location_x;
    private Double location_y;
}
