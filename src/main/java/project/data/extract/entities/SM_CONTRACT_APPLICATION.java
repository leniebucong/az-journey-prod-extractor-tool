package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT_APPLICATION@DSS")
public class SM_CONTRACT_APPLICATION extends StatementWriter {


    @Override
    public String getFieldName(String field) {
        if (field.equals("polNo"))
            return "pol_no";
        if (field.equals("applNo"))
            return "appl_no";
        return field;
    }

    @Id
    private String id;
    @Column(name = "pol_no")
    private String polNo;
    @Column(name = "appl_no")
    private String applNo;
    private String si_id,
            SUM_INSURED,
            initial_premium,
            payment_scheme,
            premium_default_opt,
            settlement_opt,
            receipt_number,
            payout_opt,
            bank_pref,
            bank_branch,
            bank_addr,
            bank_acct_curr,
            bank_acct_no,
            bank_acct_nm,
            bank_tja,
            bank_codep_nm,
            bank_pwpnb,
            bank_sss,
            coowner_id,
            rel_to_pi,
            rel_to_ao,
            co_depositors_name,
            cardholder_id,
            rel_to_cardholder,
            ao_equals_bo,
            purpose,
            other_purpose;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date submitted_dt;

}
