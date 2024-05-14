package DB;

import jakarta.persistence.*;

@Entity
public class DB_User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 10)
    private String username;
    @Column(length = 10)
    private String password;
    private int level;

    public long getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getLevel(){
        return level;
    }
}
