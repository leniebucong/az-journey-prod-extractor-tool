package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.entities.journey.embedded_classes.SM_CONTRACT_SIGNATURE_PRIMARYKEYS;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT_SIGNATURE@DSS")
@IdClass(SM_CONTRACT_SIGNATURE_PRIMARYKEYS.class)
public class SM_CONTRACT_SIGNATURE extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "contract_id";
        return field;
    }

    @Id
    private String party_id;
    @Id
    @Column(name = "contract_id")
    private String contractId;
    private String
            mime_type,
            CREATED_BY,
            UPDATED_BY;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date sign_dt, CREATED_DT,UPDATED_DT;


}
