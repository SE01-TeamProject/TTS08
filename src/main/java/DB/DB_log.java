package DB;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DB_log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(targetEntity = DB_project.class)
    private long project_id;

    @Column(columnDefinition = "TEXT")
    private String log;

    @Column(columnDefinition = "DATE")
    private LocalDateTime date;

    public long getId(){
        return id;
    }
    public long getProject_id(){
        return project_id;
    }
    public String getLog(){
        return log;
    }
    public LocalDateTime getDate(){
        return date;
    }
}
