package DB;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class DB_comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cid;

    @ManyToOne(targetEntity = DB_Issue.class)
    private long Issue_id;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(columnDefinition = "DATE")
    private LocalDateTime Date;

    public long getCid(){
        return cid;
    }
    public long getIssue_id(){
        return Issue_id;
    }
    public String getComment(){
        return comment;
    }
    public LocalDateTime getDate(){
        return Date;
    }
}
