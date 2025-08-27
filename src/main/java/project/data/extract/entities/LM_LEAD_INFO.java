package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "LM_LEAD_INFO@DSS")
public class LM_LEAD_INFO extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("leadId"))
            return "LEAD_ID";
        return field;
    }

    @Id
    @Column(name = "LEAD_ID")
    private String leadId;
    private String ACCTMGR_ID,
            PARTY_ID,
            CLIENT_REF_ID,
            FIRST_NAME,
            MIDDLE_NAME,
            LAST_NAME,
            CONTACT_NO,
            EMAIL_ADDR,
            CIVIL_STATUS,
            GENDER,
            LEAD_STAT_CD,
            CLIENT_TYPE_CD,
            OCCUPATION_CD,
            MONTHLY_INCOME,
            EHMI,
            CONSENT_TO_CALL,
            APP_ID,
            PROPOSAL_AGE,
            LEAD_STAT_DESC,
            DELETE_TAG,
            REFERED_BY_BANK_PARTNER,
            CREATED_BY,
            UPDATED_BY
                    ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date BIRTHDAY,CREATED_DT,UPDATED_DT;


}
