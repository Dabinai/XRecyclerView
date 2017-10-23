package bean;

/**
 * Created by Dabin on 2017/10/21.
 */

public class SelectList {
    private String name;
    private String pass;

    public SelectList(String pass, String name) {
        this.pass = pass;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
