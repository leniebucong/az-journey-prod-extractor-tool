package project.data.extract.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.data.extract.entities.journey.*;
import project.data.extract.entities.journey.embedded_classes.*;

import java.util.List;
import java.util.Optional;

/**
 * Journey Application nested Repositories
 * @author Lenie.Gonzales
 * @since 2023
 */
@Repository
public class JourneyApplicationRepository {

    public interface ContractApplication_Repo extends JpaRepository<SM_CONTRACT_APPLICATION, String>{
        /**
         * Gets the application data by policy number from Table SM_CONTRACT_APPLICATION
         * @param polNo policy number (8/9 digits in Journey)
         * @return Optional
         */
        Optional<SM_CONTRACT_APPLICATION> findByPolNo(String polNo);

        /**
         * Gets the application data by application number from Table SM_CONTRACT_APPLICATION
         * @param applNo application number (14 digits in Journey)
         * @return Optional
         */
        Optional<SM_CONTRACT_APPLICATION> findByApplNo(String applNo);
    }
    public interface ContractBeneOwner_Repo extends JpaRepository<SM_CONTRACT_BENE_OWNER, String>{
        /**
         * Gets the application beneficial owner by contract id from Table SM_CONTRACT_BENE_OWNER
         * @param id contract id
         * @return List of Beneficial Owners
         */
        List<SM_CONTRACT_BENE_OWNER> findByContractId(String id);
    }
    public interface Contract_Repo extends JpaRepository<SM_CONTRACT, String>{
        /**
         * Gets the application contract by contract id from Table SM_CONTRACT
         * @param contractId contract id
         * @return List of contracts (application and quotation)
         */
        List<SM_CONTRACT> findByContractId(String contractId);
    }
    public interface ContractBeneficiary_Repo extends JpaRepository<SM_CONTRACT_BENEFICIARY, String>{
        /**
         * Gets the application beneficiaries by contract id from Table SM_CONTRACT_BENEFICIARY
         * @param contractId contract id
         * @return List of beneficiaries
         */
        List<SM_CONTRACT_BENEFICIARY> findByContractId(String contractId);
    }


    public interface ContractFund_Repo extends JpaRepository<SM_CONTRACT_FUND, SM_CONTRACT_FUND_PRIMARYKEYS>{
        /**
         * Gets the application funds by contract id from Table SM_CONTRACT_FUND
         * @param contractId contract id
         * @return List of funds
         */
        List<SM_CONTRACT_FUND> findByContractId(String contractId);
    }
    public interface ContractPartyRelation_Repo extends JpaRepository<SM_CONTRACT_PARTY_RELATION, String>{
        /**
         * Gets the application party relations by contract id from Table SM_CONTRACT_PARTY_RELATION
         * @param contractId contract id
         * @return List of relations
         */
        List<SM_CONTRACT_PARTY_RELATION> findByContractId(String contractId);
    }
    public interface ContractPremium_Repo extends JpaRepository<SM_CONTRACT_PREMIUM, String>{
        /**
         * Gets the application premiums by contract id from Table SM_CONTRACT_PREMIUM
         * @param contractId contract id
         * @return List of premiums
         */
        List<SM_CONTRACT_PREMIUM> findByContractId(String contractId);
    }
    public interface ContractRider_Repo extends JpaRepository<SM_CONTRACT_RIDER, SM_CONTRACT_RIDER_PRIMARYKEYS>{       //findby the si_id and contract_id
        /**
         * Gets the application riders by contract id from Table SM_CONTRACT_RIDER
         * @param contractId contract id
         * @return List of riders
         */
        List<SM_CONTRACT_RIDER> findByContractId(String contractId);
    }
    public interface ContractSignature_Repo extends JpaRepository<SM_CONTRACT_SIGNATURE, SM_CONTRACT_SIGNATURE_PRIMARYKEYS>{
        /**
         * Gets the application signatures by contract id from Table SM_CONTRACT_SIGNATURE
         * @param contractId contract id
         * @return List of signatures (FA and clients)
         */
        List<SM_CONTRACT_SIGNATURE> findByContractId(String contractId);
    }
    public interface ContractTransaction_Repo extends JpaRepository<SM_CONTRACT_TRANSACTION, String>{
        List<SM_CONTRACT_TRANSACTION> findByContractId(String ids);
    }
    public interface ContractQuotation_Repo extends JpaRepository<SM_CONTRACT_QUOTATION, String>{}  //findById
    public interface ContractAnswer_Repo extends JpaRepository<SM_CONTRACT_ANSWER, String>{
        List<SM_CONTRACT_ANSWER> findByContractId(String contractId);
    }
    public interface LeadInfo_Repo extends JpaRepository<LM_LEAD_INFO, String>{ }  //findByID
    public interface FnaHdr_Repo extends JpaRepository<LM_FNA_HDR, String>{
        Optional<LM_FNA_HDR> findByLeadIdAndFnaId(String leadId, String fnaId);
    }
    public interface FnaDtls_Repo extends JpaRepository<LM_FNA_DTLS, LM_FNA_DTLS_PRIMARYKEYS>{
        List<LM_FNA_DTLS> findByFnaId(String fnaID);
    }
    public interface LeadReferror_Repo extends JpaRepository<LM_LEAD_REFERROR, String>{

    }  //findByID
    public interface Party_Repo extends JpaRepository<SM_PARTY, String>{
        List<SM_PARTY> findByPartyId(String id);
    }
    public interface PartyAddress_Repo extends JpaRepository<SM_PARTY_ADDR, SM_PARTY_ADDR_PRIMARYKEYS>{        //Iterate the partyID of persons related to the app
        List<SM_PARTY_ADDR> findByPartyId(String id);
    }
    public interface PartyContact_Repo extends JpaRepository<SM_PARTY_CONTACT, String>{
        List<SM_PARTY_CONTACT> findByPartyId(String id);
    }
    public interface PartyPerson_Repo extends JpaRepository<SM_PARTY_PERSON, String>{
        List<SM_PARTY_PERSON> findByPartyId(String id);
    }

    public interface RiskProfile_Repo extends JpaRepository<SM_RISK_PROFILE, String>{  }    //findById
    public interface RiskProfileAnswer_Repo extends JpaRepository<SM_RISK_PROFILE_ANSWER, SM_RISK_PROFILE_ANSWER_PRIMARYKEYS>{
        List<SM_RISK_PROFILE_ANSWER> findByIrpqId(String irpqId);
    }

    public interface Document_Repo extends JpaRepository<SM_DOCUMENT, String>{
        List<SM_DOCUMENT> findByContractId(String contractId);
    }


}

