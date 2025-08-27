package project.data.extract;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.data.extract.exceptions.NoApplicationFoundException;
import project.data.extract.repositories.ProdRepo;

import javax.persistence.Table;
import java.io.IOException;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = ExtracterTool.class)
class DemoApplicationTests {

    @Autowired
    ProdRepo.FnaDtls_Repo fnaDtlsRepo;
    @Autowired
    ProdRepo.FnaHdr_Repo fnaHdrRepo;
    @Autowired
    ProdRepo.LeadInfo_Repo leadInfoRepo;
    @Autowired
    ProdRepo.LeadReferror_Repo leadReferrorRepo;
    @Autowired
    ProdRepo.Contract_Repo contractRepo;
    @Autowired
    ProdRepo.ContractApplication_Repo contractApplicationRepo;
    @Autowired
    ProdRepo.ContractAnswer_Repo contractAnswerRepo;
    @Autowired
    ProdRepo.ContractBeneficiary_Repo contractBeneficiaryRepo;
    @Autowired
    ProdRepo.ContractFund_Repo contractFundRepo;
    @Autowired
    ProdRepo.ContractPartyRelation_Repo contractPartyRelationRepo;
    @Autowired
    ProdRepo.ContractPremium_Repo contractPremiumRepo;
    @Autowired
    ProdRepo.ContractQuotation_Repo contractQuotationRepo;
    @Autowired
    ProdRepo.ContractRider_Repo contractRiderRepo;
    @Autowired
    ProdRepo.ContractSignature_Repo contractSignatureRepo;
    @Autowired
    ProdRepo.ContractTransaction_Repo contractTransactionRepo;
    @Autowired
    ProdRepo.Document_Repo documentRepo;
    @Autowired
    ProdRepo.Party_Repo partyRepo;
    @Autowired
    ProdRepo.PartyAddress_Repo partyAddressRepo;
    @Autowired
    ProdRepo.PartyContact_Repo partyContactRepo;
    @Autowired
    ProdRepo.PartyPerson_Repo partyPersonRepo;
    @Autowired
    ProdRepo.RiskProfile_Repo riskProfileRepo;
    @Autowired
    ProdRepo.RiskProfileAnswer_Repo riskProfileAnswerRepo;
    @Autowired
    ProdRepo.ContractBeneOwner_Repo contractBeneOwnerRepo;

    @Test
    void context1()  {

    }

}
