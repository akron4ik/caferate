package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Cafe.ALL_SORTED, query = "SELECT c FROM Cafe c WHERE c.user.id=:user_id ORDER BY c.description"),
})
@Entity
@Table(name = "cafes")
public class Cafe {
    public static final int START_SEQ = 100000;

    public static final String ALL_SORTED = "Cafe.getAllSorted";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Cafe() {
    }

    public Cafe(int id, String name, int rating) {
        this.id = id;
        this.description = name;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "id=" + id +
                ", name='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
