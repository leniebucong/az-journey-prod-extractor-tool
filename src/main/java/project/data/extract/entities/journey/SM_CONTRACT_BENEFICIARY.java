package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT_BENEFICIARY@DSS")
public class SM_CONTRACT_BENEFICIARY extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "contract_id";
        return field;
    }

    @Id
    private String id;
    @Column(name = "contract_id")
    private String contractId;
    private String party_id;
    private String
            guardian_id,
            priority,
            percent_share,
            designation,
            justification,
            delete_tag,
            rel_to_pi,
            rel_to_benf,
            addr_ref,
            same_guardian_tag,
            order_no,
            CREATED_BY,
            UPDATED_BY
                    ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;


}
