package project.data.extract.entities.journey.embedded_classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SM_RISK_PROFILE_ANSWER_PRIMARYKEYS implements Serializable {
    private String QID,ANSWER_EXT_ID;
}
