package DB;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DB_Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(targetEntity = DB_project.class)
    @Column(name="PID")
    private long project_id;

    @Column(length = 10)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(targetEntity = DB_User.class)
    @Column(length = 10)
    private String reporter;
    @ManyToOne(targetEntity = DB_User.class)
    @Column(length = 10)
    private String assignee;

    private int priority;
    private int status;
    private int type;

    @Column(columnDefinition = "DATE")
    private LocalDateTime dateTime;

    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getReporter() {
        return reporter;
    }
    public String getAssignee() {
        return assignee;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public long getProject_id() {
        return project_id;
    }
    public int getPriority() {
        return priority;
    }
    public int getStatus() {
        return status;
    }
    public int getType() {
        return type;
    }
}
