package com.company.entity;
import com.company.enums.MovieState;
import com.company.enums.MovieType;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Movie extends BaseEntity{

    private String name;
    @Column(columnDefinition = "DATE")
    private LocalDate releaseDate;
    private Integer duration;
    @Column(columnDefinition = "text") // We use this annotation to avoid character limitations.
    private String summary;
    @Enumerated(EnumType.STRING)
    private MovieType type;
    @Enumerated(EnumType.STRING)
    private MovieState state;
    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "movie_genre_rel",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList;
    /*
     * 🖍️...
     * · It is better to use Set instead of List in many-to-many relationships. List may cause performance issues when we're
     *   dealing with so much data. The reason is that List deletes all the data in both tables and inserts them back again behind the scenes.
     */



}
