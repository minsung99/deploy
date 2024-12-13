package com.chiikawa.ricefriend.data.dto;

import java.math.BigDecimal;
import java.sql.Clob;

import com.chiikawa.ricefriend.data.entity.FoodCategory;
import com.chiikawa.ricefriend.data.entity.User;

import jakarta.persistence.Column;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.w3c.dom.Text;

public class UserDto {
    @Builder
    @Getter
    @Setter
    public static class UserSaveDto{
        private String email;
        private String password;
        private String name;
        private String profileimg;
        private FoodCategory food1;
        private FoodCategory food2;
        private FoodCategory food3;
        private BigDecimal rating;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .name(name)
                    .profileimg(profileimg)
                    .food1(food1)
                    .food2(food2)
                    .food3(food3)
                    .rating(rating)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class UserUpdateDto{
        private String password;
        private String name;

        private String state;

        private String profileimg;

        private FoodCategory food1;
        private FoodCategory food2;
        private FoodCategory food3;

        private BigDecimal rating;
        private int ratingqty;
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserResponseDto{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String email;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;

        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String state;

        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String profileimg;

        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private FoodCategory food1;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private FoodCategory food2;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private FoodCategory food3;

        private BigDecimal rating;
        private int ratingqty;

        public UserResponseDto(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.state = user.getState();
            this.profileimg = user.getProfileimg();
            this.food1 = user.getFood1();
            this.food2 = user.getFood2();
            this.food3 = user.getFood3();
            this.rating = user.getRating();
            this.ratingqty = user.getRatingqty();
        }
    }
}