package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

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
