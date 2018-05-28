package my.demo.app;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public static List<Account> allAccounts=new ArrayList<>();
    String name;

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
