package project.data.extract.entities;

import lombok.Data;
import project.data.extract.entities.embedded_classes.SM_CONTRACT_FUND_PRIMARYKEYS;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT_FUND@DSS")
@IdClass(SM_CONTRACT_FUND_PRIMARYKEYS.class)
public class SM_CONTRACT_FUND extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "contract_id";
        return field;
    }

    @Id
    private String fund_cd;
    @Id
    @Column(name = "contract_id")
    private String contractId;
    private String
            version,
            basic_plan_perc,
            topup_perc,
            risk_cd,
            created_by,updated_by;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
