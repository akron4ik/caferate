package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "date_time")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "price")
    @NotNull
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    @NotNull
    private Cafe cafe;//для получения кафе ай ди

    public Meal() {
    }

    public Meal(String name, LocalDateTime dateTime, long price){
        this(null, name, dateTime, price);
    }

    public Meal(Integer id, String name, LocalDateTime dateTime, long price) {
        super(id);
        this.name = name;
        this.dateTime = dateTime;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isNew(){
        return this.id == null;
    }



}
