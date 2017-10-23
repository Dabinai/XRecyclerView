package bean;

/**
 * Created by Dabin on 2017/10/21.
 */

public class SelectTwo {
    private String name;
    private String pass;

    public SelectTwo(String name, String pass) {
        this.name = name;
        this.pass = pass;
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
