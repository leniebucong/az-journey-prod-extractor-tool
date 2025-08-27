package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT@DSS")
public class SM_CONTRACT extends StatementWriter {

    @Override
	public String getFieldName(String field) {
		if (field.equals("contractId"))
			return "id";
		return field;
	}


	@Id
	@Column(name = "id")
	private String contractId;

	private String
			process_state,
			status,
			lead_id,
			version,
			client_ref_id,
			delete_tag,
			CREATED_BY,
			UPDATED_BY;


	@Temporal(value = TemporalType.TIMESTAMP)
	private Date CREATED_DT,UPDATED_DT;

}
