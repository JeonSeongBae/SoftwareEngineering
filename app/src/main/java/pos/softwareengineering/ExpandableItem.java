package pos.softwareengineering;

import java.util.ArrayList;

/**
 * Created by dldlw on 2017-12-19.
 */

public class ExpandableItem {
    public String title;
    public ArrayList<String> childList;

    public ExpandableItem() {

    }

    public ExpandableItem(String title, ArrayList<String> childList) {
        this.title = title;
        if(childList == null) {
            this.childList = new ArrayList<>();
        } else {
            this.childList = childList;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<String> childList) {
        this.childList = childList;
    }

}
