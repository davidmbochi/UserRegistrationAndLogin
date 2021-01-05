package com.david.sample.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "joke")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "joke must not be blank or empty")
    @Column(name = "joke")
    private String joke;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid",nullable = false,updatable = true,insertable = true)
    private User user;

    public Joke(){

    }

    public Joke(Long id, String joke){
        this.id = id;
        this.joke = joke;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getJoke(){
        return this.joke;
    }

    public void setJoke(String joke){
        this.joke = joke;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User users) {
        this.user = users;
    }
}
