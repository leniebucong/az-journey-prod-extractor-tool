package project.data.extract.entities;

import lombok.Data;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SM_CONTRACT_QUOTATION@DSS")
public class SM_CONTRACT_QUOTATION extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("qoutationId"))
            return "id";
        return field;
    }


    @Id
    @Column(name = "id")
    private String qoutationId;
    private String FNA_ID,
            IRPQ_ID,
            OWNER_ID,
            INSURED_ID,
            LIFE_PRIORITY,
            PRODUCT_CD,
            PRODUCT_CATEGORY,
            GAE_TAG,
            CURRENCY,
            PAYMENT_FREQ,
            CALCU_TYPE,
            BASIC_AMT,
            MULTIPLIER_FACTOR,
            PAYMENT_DURATION,
            DIVIDEND_OPT,
            DEATH_BENEFIT,
            FNA_WAIVED,
            BASE_RATING_CD,
            FLAT_EXTRA,
            OWNER_AGE,
            INSURED_AGE,
            BASIC_AMT_TEMP,
            CALCU_AMT,
            INITIAL_PREMIUM,
            SUM_ASSURED,
            ADDR_REF,
            AO_EQUALS_PI,
            TOPUP_IS_SAME_BASIC,
            PAYMENT_SCHEME,
            PREMIUM_DEFAULT_OPT,
            SETTLEMENT_OPT,
            PRODUCT_VERSION,
            CREATED_BY,

    UPDATED_BY
            ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
