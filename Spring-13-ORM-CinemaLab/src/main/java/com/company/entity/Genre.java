package com.company.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Genre extends BaseEntity{

    private String name;

    @ManyToMany(mappedBy = "genreList")
    private List<Movie> movieList; // Ownership belongs to the movie class.


}
