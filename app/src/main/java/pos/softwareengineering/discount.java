package pos.softwareengineering;

/**
 * Created by dldlw on 2017-12-20.
 */

public class discount {
    String name;
    String number;

    discount(){

    }
    discount(String name, String number){
        this.name = name;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

}
