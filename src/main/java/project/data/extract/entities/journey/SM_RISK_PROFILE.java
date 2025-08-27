package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_RISK_PROFILE@DSS")
public class SM_RISK_PROFILE extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("IRPQID"))
            return "IRPQ_ID";
        return field;
    }

    @Id
    @Column(name ="IRPQ_ID")
    private String IRPQID;
    private String PARTY_ID,
            FNA_ID,
            SCORE,
            STATUS,
            VERSION,
            DELETE_TAG,
            CLIENT_REF_ID,
            CREATED_BY,
            UPDATED_BY
                    ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
