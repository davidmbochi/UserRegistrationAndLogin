package com.david.sample.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Joke> jokes;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    public User(){

    }

    public User(Long id,String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Long id, @NotBlank String username, @NotBlank String password, List<Joke> jokes, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.jokes = jokes;
        this.roles = roles;
    }

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
