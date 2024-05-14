package DB;

import jakarta.persistence.*;

@Entity
public class DB_Proj_to_User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(targetEntity = DB_project.class)
    private long pid;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(targetEntity = DB_User.class)
    private long uid;

    public long getPid(){
        return pid;
    }
    public long getUid(){
        return uid;
    }
}
