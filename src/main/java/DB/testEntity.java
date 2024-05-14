package DB;

import jakarta.persistence.*;

@Entity
@Table(name = "test")
public class testEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10,nullable = false)
    private String name;


    //getter n setter
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
