package project.data.extract.entities.journey;

import lombok.Data;
import project.data.extract.entities.journey.embedded_classes.SM_PARTY_ADDR_PRIMARYKEYS;
import project.data.extract.services.StatementWriter;

import javax.persistence.*;
import java.util.Date;
import java.util.function.UnaryOperator;

@Data
@Entity
@Table(name = "SM_PARTY_ADDR@DSS")
@IdClass(SM_PARTY_ADDR_PRIMARYKEYS.class)
public class SM_PARTY_ADDR extends StatementWriter {

    @Override
    public String getFieldName(String field) {
        if (field.equals("partyId"))
            return "PARTY_ID";
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

        setUNIT_BLDG(maskName.apply(getUNIT_BLDG()));
        setLOT_BLK(maskName.apply(getLOT_BLK()));
        setSTREET(maskName.apply(getSTREET()));
        setBRGY_SUBD(maskName.apply(getBRGY_SUBD()));
    };


    @Id
    private String ID;
    @Id
    @Column(name = "PARTY_ID")
    private String partyId;

    private String TYPE,
            UNIT_BLDG,
            LOT_BLK,
            STREET,
            BRGY_SUBD,
            CITY_CD,
            PROVINCE_CD,
            COUNTRY_CD,
            ZIP_CD,
            CREATED_BY,
            UPDATED_BY
                    ;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date CREATED_DT,UPDATED_DT;

}
