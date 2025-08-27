package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT_TRANSACTION@DSS")
public class SM_CONTRACT_TRANSACTION extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "contract_id";
        return field;
    }

    @Id
    @Column(name = "contract_id")
    private String contractId;
    private String policy_year,
            withdrawal_amt,
            topup_amt,
            withdrawal_start_age,
            withdrawal_end_age,
            age,
            delete_tag,
            CREATED_BY,
            UPDATED_BY;


    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
