package DB;

import jakarta.persistence.*;

@Entity
public class DB_project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PID")
    private long pid;

    @Column(length = 10)
    private String title;

    public long getPid() {
        return pid;
    }
    public String getTitle() {
        return title;
    }
}
