package winep.ir.twocon.DataModel;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class Course {

    private String title;
    private String description;
    private int color;
    public long id;
    public boolean selected;


    public Course(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
