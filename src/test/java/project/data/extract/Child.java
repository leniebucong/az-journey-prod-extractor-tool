package project.data.extract;

import lombok.Data;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.util.Date;


@Data
@Table(name = "child")
public class Child extends Sample{

	@Override
	public String getFieldName(String field) {
		if (field.equals("contractId"))
			return "id";
		return field;
	}

	private String contractId;

	private String
			process_state,
			status,
			lead_id,
			version,
			client_ref_id,
			delete_tag,
			CREATED_BY,
			UPDATED_BY,
			UPDATED_DT;

	private Date CREATED_DT;
}
