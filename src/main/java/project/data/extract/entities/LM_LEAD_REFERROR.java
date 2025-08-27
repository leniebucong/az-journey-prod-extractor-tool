package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "LM_LEAD_REFERROR@DSS")
public class LM_LEAD_REFERROR extends StatementWriter {


    @Override
    public String getFieldName(String field) {
        if (field.equals("leadId"))
            return "LEAD_ID";
        return field;
    }


    @Id
    @Column(name ="LEAD_ID")
    private String leadId;

    private String REFERROR_CD,BRANCH_CD,
            REFERROR_FNAME,
            REFERROR_MNAME,
            REFERROR_LNAME,
            CLIENT_REF_ID,
            APP_ID,
            CREATED_BY,
            UPDATED_BY
            ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
