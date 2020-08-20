package top.hapleow.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private int id;

    private String name;


    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
