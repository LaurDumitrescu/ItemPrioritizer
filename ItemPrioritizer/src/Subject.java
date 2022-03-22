import java.io.Serializable;

public class Subject implements Comparable<Subject>, Serializable {

    private String name;
    private int priority;

    public Subject(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Subject o) {
        if(priority == o.priority || name.equals(o.name)) return 0;
        if(priority > o.priority) return 1;
        if(priority < o.priority) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return priority + "." + " " + name + "\n";
    }


}
