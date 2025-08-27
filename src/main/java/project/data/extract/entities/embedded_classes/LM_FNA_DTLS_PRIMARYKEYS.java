package project.data.extract.entities.embedded_classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LM_FNA_DTLS_PRIMARYKEYS implements Serializable {

    private String fnaId, tag;

}
