package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "LM_FNA_HDR@DSS")
public class LM_FNA_HDR extends StatementWriter {


    @Override
    public String getFieldName(String field) {
        if (field.equals("fnaId"))
            return "FNA_ID";
        if (field.equals("leadId"))
            return "LEAD_ID";
        return field;
    }

    @Column(name = "LEAD_ID")
    private String leadId;
    @Id
    @Column(name = "FNA_ID")
    private String fnaId;
    private String GOAL_CAT,
            NEED,
            FNA_STATUS,
            COMPLETED_DT,
            DELETE_TAG,
            CLIENT_REF_ID,
            APP_ID,
            ISWAVED;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
