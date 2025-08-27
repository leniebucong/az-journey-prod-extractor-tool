package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SM_CONTRACT_BENE_OWNER@DSS")
public class SM_CONTRACT_BENE_OWNER extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "CONTRACT_ID";
        return field;
    }


    @Column(name = "CONTRACT_ID")
    private String contractId;
    @Id
    private String ID;
    private String PARTY_ID,
            OWNER_PERCENT;

}
