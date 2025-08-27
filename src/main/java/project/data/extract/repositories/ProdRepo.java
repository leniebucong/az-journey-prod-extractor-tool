package project.data.extract.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.data.extract.entities.*;

import java.util.List;
import java.util.Optional;

@Repository
public class ProdRepo {

    public interface ContractApplication_Repo extends JpaRepository<SM_CONTRACT_APPLICATION, String>{
        Optional<SM_CONTRACT_APPLICATION> findByPolNo(String polNo);
        Optional<SM_CONTRACT_APPLICATION> findByApplNo(String applNo);
    }
    public interface ContractBeneOwner_Repo extends JpaRepository<SM_CONTRACT_BENE_OWNER, String>{
        List<SM_CONTRACT_BENE_OWNER> findByContractId(String id);
    }
    public interface Contract_Repo extends JpaRepository<SM_CONTRACT, String>{
        List<SM_CONTRACT> findByContractId(String contractId);
    }
    public interface ContractBeneficiary_Repo extends JpaRepository<SM_CONTRACT_BENEFICIARY, String>{
        List<SM_CONTRACT_BENEFICIARY> findByContractId(String contractId);
    }
    public interface ContractFund_Repo extends JpaRepository<SM_CONTRACT_FUND, String>{
        List<SM_CONTRACT_FUND> findByContractId(String contractId);
    }
    public interface ContractPartyRelation_Repo extends JpaRepository<SM_CONTRACT_PARTY_RELATION, String>{
        List<SM_CONTRACT_PARTY_RELATION> findByContractId(String contractId);
    }
    public interface ContractPremium_Repo extends JpaRepository<SM_CONTRACT_PREMIUM, String>{
        List<SM_CONTRACT_PREMIUM> findByContractId(String contractId);
    }
    public interface ContractRider_Repo extends JpaRepository<SM_CONTRACT_RIDER, String>{       //findby the si_id and contract_id
        List<SM_CONTRACT_RIDER> findByContractId(String contractId);
    }
    public interface ContractSignature_Repo extends JpaRepository<SM_CONTRACT_SIGNATURE, String>{
        List<SM_CONTRACT_SIGNATURE> findByContractId(String ids);
    }
    public interface ContractTransaction_Repo extends JpaRepository<SM_CONTRACT_TRANSACTION, String>{
        List<SM_CONTRACT_TRANSACTION> findByContractId(String ids);
    }
    public interface ContractQuotation_Repo extends JpaRepository<SM_CONTRACT_QUOTATION, String>{}  //findById
    public interface ContractAnswer_Repo extends JpaRepository<SM_CONTRACT_ANSWER, String>{
        List<SM_CONTRACT_ANSWER> findByContractId(String contractId);
    }
    public interface LeadInfo_Repo extends JpaRepository<LM_LEAD_INFO, String>{}  //findByID
    public interface FnaHdr_Repo extends JpaRepository<LM_FNA_HDR, String>{
        Optional<LM_FNA_HDR> findByLeadIdAndFnaId(String leadId, String fnaId);
    }
    public interface FnaDtls_Repo extends JpaRepository<LM_FNA_DTLS, String>{
        List<LM_FNA_DTLS> findByFnaId(String fnaID);
    }
    public interface LeadReferror_Repo extends JpaRepository<LM_LEAD_REFERROR, String>{}  //findByID
    public interface Party_Repo extends JpaRepository<SM_PARTY, String>{
        List<SM_PARTY> findByPartyId(String id);
    }
    public interface PartyAddress_Repo extends JpaRepository<SM_PARTY_ADDR, String>{        //Iterate the partyID of persons related to the app
        List<SM_PARTY_ADDR> findByPartyId(String id);
    }
    public interface PartyContact_Repo extends JpaRepository<SM_PARTY_CONTACT, String>{
        List<SM_PARTY_CONTACT> findByPartyId(String id);
    }
    public interface PartyPerson_Repo extends JpaRepository<SM_PARTY_PERSON, String>{
        List<SM_PARTY_PERSON> findByPartyId(String id);
    }

    public interface RiskProfile_Repo extends JpaRepository<SM_RISK_PROFILE, String>{  }    //findById
    public interface RiskProfileAnswer_Repo extends JpaRepository<SM_RISK_PROFILE_ANSWER, String>{
        List<SM_RISK_PROFILE_ANSWER> findByIrpqId(String irpqId);
    }

    public interface Document_Repo extends JpaRepository<SM_DOCUMENT, String>{
        List<SM_DOCUMENT> findByContractId(String contractId);
    }


}

