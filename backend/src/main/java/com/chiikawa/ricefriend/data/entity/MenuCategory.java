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
@Table(name = "menucategory")
public class MenuCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private int id;

    private String name;
}