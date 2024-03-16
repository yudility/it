package it.demo.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jgrapht.graph.DefaultWeightedEdge;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomWeightEdge extends DefaultWeightedEdge {
    private Long id;
}
