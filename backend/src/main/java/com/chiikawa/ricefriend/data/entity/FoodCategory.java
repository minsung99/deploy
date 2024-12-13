package com.chiikawa.ricefriend.data.entity;

import lombok.*;
import jakarta.persistence.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "foodcategory")
public class FoodCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private int id;

    @ManyToOne
    @JoinColumn(name="menuid", referencedColumnName="id")
    private MenuCategory menu;

    private String name;
}