package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;
import java.util.function.UnaryOperator;

@Data
@Entity
@Table(name = "SM_PARTY_CONTACT@DSS")
public class SM_PARTY_CONTACT extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("partyId"))
            return "PARTY_ID";
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


        //SetValue depending on the type; If PHONE set to "09000000000", if EMAIL mask email, If Not both return as it is
        setVALUE(
                getTYPE().equals("PHONE") ? "09000000000" :
                        getTYPE().equals("EMAIL") ? maskEmail.apply(getVALUE()) :
                                getVALUE()
        );
    };

    @Id
    private String ID;
    @Column(name = "PARTY_ID")
    private String partyId;
    private String SEQ_NO,
            TYPE,
            PREFERRED,
            VALUE,
            COUNTRY_CD,
            AREA_CD,
            CREATED_BY,
            UPDATED_BY
                    ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
