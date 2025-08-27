package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

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

    @Override
    public void maskConfidentialData(){
        //For Names
        UnaryOperator<String> maskName = (field) -> {
            //check if input has more than 1 char
            try {
                if (field.length()<=1) return field;
            }catch(NullPointerException nullPointerException){return field;};

            //proceed masking
            StringBuilder masked = new StringBuilder(field.substring(0,1));
            for (int i = 1; i < field.length(); i++) {
                masked.append("*");
            }
            return masked.toString();
        };

        //For Email Address
        UnaryOperator<String> maskEmail = (email) -> {
            int atIndex = email.indexOf('@');
            if (atIndex <= 1) return email;
            return maskName.apply(email.substring(0, atIndex)) + email.substring(atIndex);
        };

        setFIRST_NAME(maskName.apply(getFIRST_NAME()));
        setLAST_NAME(maskName.apply(getLAST_NAME()));
        setMIDDLE_NAME(maskName.apply(getMIDDLE_NAME()));
        setEMAIL_ADDR(maskEmail.apply(getEMAIL_ADDR()));
        setCONTACT_NO("09000000000");
    };


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
