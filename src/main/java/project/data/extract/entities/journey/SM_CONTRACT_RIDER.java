package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.entities.journey.embedded_classes.SM_CONTRACT_RIDER_PRIMARYKEYS;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT_RIDER@DSS")
@IdClass(SM_CONTRACT_RIDER_PRIMARYKEYS.class)
public class SM_CONTRACT_RIDER extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "contract_id";
        return field;
    }

    @Id
    private String rider_cd;
    @Id
    @Column(name = "contract_id")
    private String contractId;
    private String
            version,
            coverage_amt,
            shorten_payterm_to,
            risk_cd,
            prem_amt,
            CREATED_BY,
            UPDATED_BY;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
