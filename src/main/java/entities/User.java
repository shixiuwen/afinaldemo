package entities;

import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by shixiuwen on 15-11-3.
 */

@Table(name = "user")
public class User {
    private int id;
    private String name;
    private String psw;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
