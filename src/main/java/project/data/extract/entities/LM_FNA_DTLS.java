package project.data.extract.entities;

import lombok.Data;
import project.data.extract.entities.embedded_classes.LM_FNA_DTLS_PRIMARYKEYS;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@IdClass(LM_FNA_DTLS_PRIMARYKEYS.class)
@Entity
@Table(name = "LM_FNA_DTLS@DSS")
public class LM_FNA_DTLS extends StatementWriter {


    @Override
    public String getFieldName(String field) {
        if (field.equals("fnaId"))
            return "FNA_ID";
        return field;
    }


    @Id
    @Column(name = "FNA_ID")
    private String fnaId;
    @Id
    private String tag;
    private String TAG_VALUE,
            GOAL_TYPE,
            CLIENT_REF_ID,
            APP_ID;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;


}
