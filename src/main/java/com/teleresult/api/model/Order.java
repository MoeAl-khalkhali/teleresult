package com.teleresult.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Date;

@Entity
@EnableJpaAuditing
@Table(name = "`order`")
public class Order {
    @javax.persistence.Id
    @Id
    @NotNull
    private long id;
    @NotNull
    private String title;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private Date date;
    @NotNull
    @Column(name = "type", nullable = false)
    private String type;
    @NotNull
    private String customer;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setId(Long id) {
        this.id = id;
    }

}