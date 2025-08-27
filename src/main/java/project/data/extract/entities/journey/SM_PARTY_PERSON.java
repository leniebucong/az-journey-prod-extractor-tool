package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;
import java.util.function.UnaryOperator;

@Data
@Entity
@Table(name = "SM_PARTY_PERSON@DSS")
public class SM_PARTY_PERSON extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("partyId"))
            return "ID";
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

        setFIRST_NM(maskName.apply(getFIRST_NM()));
        setMIDDLE_NM(maskName.apply(getMIDDLE_NM()));
        setLAST_NM(maskName.apply(getLAST_NM()));
        setOTHER_LEGAL_FIRST_NM(maskName.apply(getOTHER_LEGAL_FIRST_NM()));
        setOTHER_LEGAL_MIDDLE_NM(maskName.apply(getOTHER_LEGAL_MIDDLE_NM()));
        setOTHER_LEGAL_NM(maskName.apply(getOTHER_LEGAL_NM()));
        setMOTHER_MAIDEN_FIRST_NM(maskName.apply(getMOTHER_MAIDEN_FIRST_NM()));
        setMOTHER_MAIDEN_MIDDLE_NM(maskName.apply(getMOTHER_MAIDEN_MIDDLE_NM()));
        setMOTHER_MAIDEN_NM(maskName.apply(getMOTHER_MAIDEN_NM()));
    };

    @Id
    @Column(name = "ID")
    private String partyId;

    private String FIRST_NM,
            MIDDLE_NM,
            LAST_NM,
            OTHER_LEGAL_FIRST_NM,
            OTHER_LEGAL_MIDDLE_NM,
            OTHER_LEGAL_NM,
            MOTHER_MAIDEN_FIRST_NM,
            MOTHER_MAIDEN_MIDDLE_NM,
            MOTHER_MAIDEN_NM,
            BIRTHPLACE,
            GENDER,
            NATIONALITY,
            IS_US_PERSON,
            CIVIL_STATUS,
            EST_ANN_INC,
            OCCUPATION,
            EMPLOYER,
            NATURE_OF_BUSINESS,
            OCCUPATION_DTLS,
            FUND_SOURCE,
            HEIGHT,
            HEIGHT_UNIT,
            WEIGHT,
            WEIGHT_UNIT,
            AGE,
            OTHER_FUND_SOURCE,
            ANN_INC_CURRENCY,
            HAS_MIDDLE_NM,
            OCCUPATION_TITLE,
            CREATED_BY,
            UPDATED_BY
                    ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date BIRTHDAY,CREATED_DT,UPDATED_DT;


}
