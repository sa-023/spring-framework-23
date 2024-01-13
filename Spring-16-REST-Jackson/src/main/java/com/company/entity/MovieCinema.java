package com.company.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class MovieCinema extends BaseEntity {

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;
    /*
     * 🖍️...
     * · It is good practice to use @ManyToOne instead of @OneToMany. When we don't use @ManyToOne, then we should go to
     *   movie class and create a movieCinema field, annotate it, and map it with @OneToMany(mappedBy=movie").
     * ❗️ @ManyToOne: Default Fetch Type is FetchType.EAGER.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinema;


}
