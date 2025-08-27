package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

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
