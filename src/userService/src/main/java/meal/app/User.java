<<<<<<< HEAD
package meal.app;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class User extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "disliked_dishes")
    private String dislikedDishes;

    // Default constructor
    public User() {
    }

    // Constructor with fields
    public User(Integer id, String name, String role, String dislikedDishes) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.dislikedDishes = dislikedDishes;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDislikedDishes() {
        return dislikedDishes;
    }

    public void setDislikedDishes(String dislikedDishes) {
        this.dislikedDishes = dislikedDishes;
    }
}
=======
package meal.app;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class User extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "disliked_dishes")
    private String dislikedDishes;

    // Default constructor
    public User() {
    }

    // Constructor with fields
    public User(Integer id, String name, String role, String dislikedDishes) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.dislikedDishes = dislikedDishes;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDislikedDishes() {
        return dislikedDishes;
    }

    public void setDislikedDishes(String dislikedDishes) {
        this.dislikedDishes = dislikedDishes;
    }
}
>>>>>>> 060e373aa7ea42d5bbb64ecacd8113f262627fb7
