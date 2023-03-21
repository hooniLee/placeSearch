package place.search.service.placeSearch.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = @Index(name = "idx_column_count", columnList = "count DESC"))
public class PlaceSearchCount {
    @Id private String query;

    @Column(nullable = false)
    private long count;

}
