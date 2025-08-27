package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_PARTY@DSS")
public class SM_PARTY extends StatementWriter {
    @Override
    public String getFieldName(String field) {
        if (field.equals("partyId"))
            return "ID";
        return field;
    }

    @Id
    @Column(name = "ID")
    private String partyId;
    private String TIN,
            SSS,
            TYPE,
            CATEGORY,
            CREATED_BY,
            UPDATED_BY
                    ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;


}
