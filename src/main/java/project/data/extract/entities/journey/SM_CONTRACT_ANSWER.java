package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT_ANSWER@DSS")
public class SM_CONTRACT_ANSWER extends StatementWriter {


    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "CONTRACT_ID";
        return field;
    }


    @Id
    private String id;
    @Column(name = "CONTRACT_ID")
    private String contractId;
    private String QID;
    private String
            PARTY_ID, ANSWER_EXT_ID,
            ANSWER, CREATED_BY,
            UPDATED_BY;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
