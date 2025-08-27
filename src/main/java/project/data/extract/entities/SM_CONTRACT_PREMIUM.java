package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SM_CONTRACT_PREMIUM@DSS")
public class SM_CONTRACT_PREMIUM extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("contractId"))
            return "contract_id";
        return field;
    }

    @Id
    @Column(name = "contract_id")
    private String contractId;
    private String
            sum_assured,
            annual_prem,
            semiannual_prem,
            quarter_prem,
            month_prem,
            tot_annual_prem,
            TOT_SEMIANNUAL_PREM,
            tot_quarter_prem,
            tot_month_prem;

}
