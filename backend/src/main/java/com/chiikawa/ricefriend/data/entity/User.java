package com.chiikawa.ricefriend.data.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.*;
import jakarta.persistence.*;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private int id;

    private String email;
    private String password;
    private String name;

    private String state;

    private String profileimg;

    @ManyToOne
    @JoinColumn(name="favfood_id1", referencedColumnName="id")
    private FoodCategory food1;
    @ManyToOne
    @JoinColumn(name="favfood_id2", referencedColumnName="id")
    private FoodCategory food2;
    @ManyToOne
    @JoinColumn(name="favfood_id3", referencedColumnName="id")
    private FoodCategory food3;

    private BigDecimal rating;
    private int ratingqty;

    @Builder
    protected User(String email, String password, String name, String state, String profileimg
            , FoodCategory food1, FoodCategory food2, FoodCategory food3, BigDecimal rating, int ratingqty) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.state = state;
        this.profileimg = profileimg;
        this.food1 = food1;
        this.food2 = food2;
        this.food3 = food3;
        this.rating = rating;
        this.ratingqty = ratingqty;
    }

    public void update(String name, String state, String profileimg
            , FoodCategory food1, FoodCategory food2, FoodCategory food3, BigDecimal rating, int ratingqty) {
        this.name = name;
        this.state = state;
        this.profileimg = profileimg;
        this.food1 = food1;
        this.food2 = food2;
        this.food3 = food3;
        this.rating = rating;
        this.ratingqty = ratingqty;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}