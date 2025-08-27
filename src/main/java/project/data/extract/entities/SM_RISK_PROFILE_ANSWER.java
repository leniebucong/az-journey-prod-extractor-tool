package project.data.extract.entities;

import lombok.Data;
import project.data.extract.entities.embedded_classes.SM_RISK_PROFILE_ANSWER_PRIMARYKEYS;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;


@IdClass(SM_RISK_PROFILE_ANSWER_PRIMARYKEYS.class)
@Data
@Entity
@Table(name = "SM_RISK_PROFILE_ANSWER@DSS")
public class SM_RISK_PROFILE_ANSWER extends StatementWriter {


    @Override
    public String getFieldName(String field) {
        if (field.equals("irpqId"))
            return "IRPQ_ID";
        return field;
    }

    @Id
    private String QID;
    @Id
    private String ANSWER_EXT_ID;
    @Column(name = "IRPQ_ID")
    private String irpqId;
    private String
            ANSWER,
            CREATED_BY,
            UPDATED_BY;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
