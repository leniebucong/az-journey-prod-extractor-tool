package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;
import java.util.function.UnaryOperator;

@Data
@Entity
@Table(name = "LM_LEAD_REFERROR@DSS")
public class LM_LEAD_REFERROR extends StatementWriter {


    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "CONTRACT_ID";
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

        setREFERROR_FNAME(maskName.apply(getREFERROR_FNAME()));
        setREFERROR_MNAME(maskName.apply(getREFERROR_MNAME()));
        setREFERROR_LNAME(maskName.apply(getREFERROR_LNAME()));
    };

    @Id
    @Column(name ="CONTRACT_ID")
    private String contractId;

    private String REFERROR_CD,BRANCH_CD,
            LEAD_ID,
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
