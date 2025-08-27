package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT_PARTY_RELATION@DSS")
public class SM_CONTRACT_PARTY_RELATION extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "contract_id";
        return field;
    }


    @Id
    private String id;
    private String related_party;
    @Column(name = "contract_id")
    private String contractId;
    private String
            leading_party,
            relationship,
            CREATED_BY,
            UPDATED_BY
                    ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
