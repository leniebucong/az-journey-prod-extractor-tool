package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_DOCUMENT@DSS")
public class SM_DOCUMENT extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "contract_id";
        return field;
    }

    @Id
    private String ID;
    @Column(name = "contract_id")
    private String contractId;

    private String TYPE;
    private String PARENT_ID,
            MIME_TYPE,
            TEXT,
            CREATED_BY,
            UPDATED_BY
            ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;


}
