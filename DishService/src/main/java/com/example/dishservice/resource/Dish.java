package com.example.dishservice.resource;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "Dish")
public class Dish extends PanacheEntityBase {
    @Id
    @NotNull
    @Min(100)
    @Max(999)
    public Integer id;

    @Column(length = 255, unique = true)
    @NotNull
    @Size(max = 255)
    public String name;

    @Column(length = 10)
    @NotNull
    public String dish_type;

    @Column
    @NotNull
    @Min(0)
    @Max(7)
    public BigDecimal time_not_repeat;

    @Column
    @NotNull
    public BigDecimal price;
}