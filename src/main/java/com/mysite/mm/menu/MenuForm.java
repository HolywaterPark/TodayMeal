package com.mysite.mm.menu;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MenuForm {
    @NotEmpty(message = "메뉴 이름은 필수항목입니다.")
    @Length(max = 50)
    private String name;

    @NotEmpty(message = "가격은 필수항목입니다.")
    private Integer price;

    @NotEmpty(message = "음식 타입은 필수항목입니다.")
    private String foodType;
}
