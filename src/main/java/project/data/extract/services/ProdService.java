package project.data.extract.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.data.extract.entities.journey.*;
import project.data.extract.exceptions.NoApplicationFoundException;
import project.data.extract.models.LocalFilesProp;
import project.data.extract.repositories.JourneyApplicationRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@Qualifier("ProdService")
public class ProdService {
    @Autowired
    JourneyApplicationRepository.FnaDtls_Repo fnaDtlsRepo;
    @Autowired
    JourneyApplicationRepository.FnaHdr_Repo fnaHdrRepo;
    @Autowired
    JourneyApplicationRepository.LeadInfo_Repo leadInfoRepo;
    @Autowired
    JourneyApplicationRepository.LeadReferror_Repo leadReferrorRepo;
    @Autowired
    JourneyApplicationRepository.Contract_Repo contractRepo;
    @Autowired
    JourneyApplicationRepository.ContractApplication_Repo contractApplicationRepo;
    @Autowired
    JourneyApplicationRepository.ContractAnswer_Repo contractAnswerRepo;
    @Autowired
    JourneyApplicationRepository.ContractBeneficiary_Repo contractBeneficiaryRepo;
    @Autowired
    JourneyApplicationRepository.ContractFund_Repo contractFundRepo;
    @Autowired
    JourneyApplicationRepository.ContractPartyRelation_Repo contractPartyRelationRepo;
    @Autowired
    JourneyApplicationRepository.ContractPremium_Repo contractPremiumRepo;
    @Autowired
    JourneyApplicationRepository.ContractQuotation_Repo contractQuotationRepo;
    @Autowired
    JourneyApplicationRepository.ContractRider_Repo contractRiderRepo;
    @Autowired
    JourneyApplicationRepository.ContractSignature_Repo contractSignatureRepo;
    @Autowired
    JourneyApplicationRepository.ContractTransaction_Repo contractTransactionRepo;
    @Autowired
    JourneyApplicationRepository.Document_Repo documentRepo;
    @Autowired
    JourneyApplicationRepository.Party_Repo partyRepo;
    @Autowired
    JourneyApplicationRepository.PartyAddress_Repo partyAddressRepo;
    @Autowired
    JourneyApplicationRepository.PartyContact_Repo partyContactRepo;
    @Autowired
    JourneyApplicationRepository.PartyPerson_Repo partyPersonRepo;
    @Autowired
    JourneyApplicationRepository.RiskProfile_Repo riskProfileRepo;
    @Autowired
    JourneyApplicationRepository.RiskProfileAnswer_Repo riskProfileAnswerRepo;
    @Autowired
    JourneyApplicationRepository.ContractBeneOwner_Repo contractBeneOwnerRepo;

    @Autowired @Getter private LocalFilesProp filesProp;


    public void extractApp(String polNo) throws IOException, NoApplicationFoundException, SQLException, ClassNotFoundException {
        /*NEWLY ADDED*/
        new LM_FNA_DTLS().init(filesProp.getInsert_scripts_dir(), polNo);
        new LM_FNA_HDR().init(filesProp.getInsert_scripts_dir(), polNo);
        new LM_LEAD_INFO().init(filesProp.getInsert_scripts_dir(), polNo);
        new LM_LEAD_REFERROR().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_ANSWER().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_APPLICATION().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_BENE_OWNER().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_BENEFICIARY().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_FUND().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_PARTY_RELATION().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_PREMIUM().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_QUOTATION().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_RIDER().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_SIGNATURE().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_CONTRACT_TRANSACTION().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_DOCUMENT().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_PARTY().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_PARTY_ADDR().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_PARTY_CONTACT().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_PARTY_PERSON().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_RISK_PROFILE().init(filesProp.getInsert_scripts_dir(), polNo);
        new SM_RISK_PROFILE_ANSWER().init(filesProp.getInsert_scripts_dir(), polNo);
        /*END*/

        PrintWriter insertWriter = new PrintWriter(new FileWriter(filesProp.getInsert_queries_file(), false));
        insertWriter.println("--Generated "+ LocalDateTime.now());
        insertWriter.close();

        SM_CONTRACT_APPLICATION application = contractApplicationRepo.findByPolNo(polNo)
                .orElse(contractApplicationRepo.findByApplNo(polNo).orElse(null));

        if(application !=null){
            List<String> ids = new ArrayList<>();
            ids.add(application.getSi_id());
            ids.add(application.getId());

            List<SM_CONTRACT> contract = new ArrayList<>();
            List<SM_CONTRACT_BENEFICIARY> beneficiaries = new ArrayList<>();
            List<SM_CONTRACT_FUND> funds = new ArrayList<>();
            List<SM_CONTRACT_PARTY_RELATION> relations = new ArrayList<>();
            List<SM_CONTRACT_PREMIUM> premiums = new ArrayList<>();
            List<SM_CONTRACT_RIDER> riders = new ArrayList<>();
            List<SM_CONTRACT_SIGNATURE> signatures = new ArrayList<>();
            List<SM_CONTRACT_TRANSACTION> transactions = new ArrayList<>();
            List<SM_CONTRACT_BENE_OWNER> beneOwners = new ArrayList<>();

            for (String id : ids) {
                contract.addAll(contractRepo.findByContractId(id));
                beneficiaries.addAll(contractBeneficiaryRepo.findByContractId(id)
                        .stream()
                        .filter(contractBeneficiary -> contractBeneficiary.getDelete_tag()==null)
                        .collect(Collectors.toList()));

                relations.addAll(contractPartyRelationRepo.findByContractId(id));
                premiums.addAll(contractPremiumRepo.findByContractId(id));
                riders.addAll(contractRiderRepo.findByContractId(id));
                signatures.addAll(contractSignatureRepo.findByContractId(id));
                transactions.addAll(contractTransactionRepo.findByContractId(id));
                beneOwners.addAll(contractBeneOwnerRepo.findByContractId(id));
                funds.addAll(contractFundRepo.findByContractId(id));
            }

            SM_CONTRACT_QUOTATION quotation = contractQuotationRepo.findById(application.getSi_id()).orElse(null);
            List<SM_CONTRACT_ANSWER> contractAnswer = contractAnswerRepo.findByContractId(application.getId());

            String leadId = contract.get(0).getLead_id();

            SM_RISK_PROFILE riskProfile = null;
            List<SM_RISK_PROFILE_ANSWER> riskProfileAnswers = null;
            LM_FNA_HDR fnaHeader = null;
            List<LM_FNA_DTLS> fnaDetails = null;


            if (quotation!=null){
                if (quotation.getIRPQ_ID()!=null || !quotation.getIRPQ_ID().isEmpty()){
                    riskProfile = riskProfileRepo.findById(quotation.getIRPQ_ID()).orElse(null);
                    riskProfileAnswers = riskProfileAnswerRepo.findByIrpqId(quotation.getIRPQ_ID());
                }

                String fnaID = quotation.getFNA_ID()==null? riskProfile.getFNA_ID() :  quotation.getFNA_ID();

                fnaHeader = fnaHdrRepo.findByLeadIdAndFnaId(leadId, fnaID).orElse(null);
                fnaDetails = fnaDtlsRepo.findByFnaId(fnaID);
            }

            LM_LEAD_INFO lead = leadInfoRepo.findById(leadId).orElse(null);
            LM_LEAD_REFERROR referror = leadReferrorRepo.findById(application.getId()).orElse(null);

            TreeSet<String> partyPersons = new TreeSet<>();
            if (quotation!=null){
                partyPersons.add(quotation.getOWNER_ID());
                partyPersons.add(quotation.getINSURED_ID());
            }
            if (beneficiaries!=null)
                beneficiaries.stream().forEach(bene -> partyPersons.add(bene.getParty_id()));
            if (application.getCoowner_id()!=null)
                partyPersons.add(application.getCoowner_id());
            if (lead.getPARTY_ID()!=null)
                partyPersons.add(lead.getPARTY_ID());

            ArrayList<SM_PARTY> party = new ArrayList<>();
            ArrayList<SM_PARTY_ADDR> partyAddrs = new ArrayList<>();
            ArrayList<SM_PARTY_CONTACT> partyContacts = new ArrayList<>();
            ArrayList<SM_PARTY_PERSON> partyPeople = new ArrayList<>();

            for (String partyPerson : partyPersons) {
                party.addAll(partyRepo.findByPartyId(partyPerson));
                partyAddrs.addAll(partyAddressRepo.findByPartyId(partyPerson));
                partyContacts.addAll(partyContactRepo.findByPartyId(partyPerson));
                partyPeople.addAll(partyPersonRepo.findByPartyId(partyPerson));
            }

            List<SM_DOCUMENT> documents = documentRepo.findByContractId(application.getId());

            // GETTING INSERT STATEMENTS
            List<Map<String, Map<String, String>>> tables = new ArrayList<>();

            tables.add(application.writeInsertQuery(filesProp));
            contract.forEach(cntrt -> tables.add(cntrt==null ? null : cntrt.writeInsertQuery(filesProp)));
            beneficiaries.forEach(bene -> tables.add(bene==null ? null : bene.writeInsertQuery(filesProp)));
            funds.forEach(fund -> tables.add(fund==null ? null : fund.writeInsertQuery(filesProp)));
            relations.forEach(relation -> tables.add(relation==null ? null : relation.writeInsertQuery(filesProp)));

            premiums.forEach(prem -> tables.add(prem ==null ? null : prem.writeInsertQuery(filesProp)));
            riders.forEach(rider -> tables.add(rider==null ? null : rider.writeInsertQuery(filesProp)));
            signatures.forEach(signature -> tables.add(signature==null ? null : signature.writeInsertQuery(filesProp)));
            transactions.forEach(transact -> tables.add(transact==null ? null : transact.writeInsertQuery(filesProp)));
            beneOwners.forEach(beneOwner -> tables.add(beneOwner==null ? null : beneOwner.writeInsertQuery(filesProp)));

            tables.add(quotation==null ? null : quotation.writeInsertQuery(filesProp));
            contractAnswer.forEach(answer -> tables.add(answer==null ? null : answer.writeInsertQuery(filesProp)));

            tables.add(riskProfile==null ? null : riskProfile.writeInsertQuery(filesProp));
            riskProfileAnswers.forEach(riskAnswer -> tables.add(riskAnswer==null ? null : riskAnswer.writeInsertQuery(filesProp)));

            tables.add(fnaHeader==null ? null : fnaHeader.writeInsertQuery(filesProp));
            fnaDetails.forEach(fnaDtls -> tables.add(fnaDtls==null ? null : fnaDtls.writeInsertQuery(filesProp)));
            tables.add(lead==null ? null : lead.writeInsertQuery(filesProp));
            tables.add(referror==null ? null : referror.writeInsertQuery(filesProp));

            party.forEach(x -> tables.add(x==null ? null : x.writeInsertQuery(filesProp)));
            partyAddrs.forEach(x -> tables.add(x==null ? null : x.writeInsertQuery(filesProp)));
            partyContacts.forEach(x -> tables.add(x==null ? null : x.writeInsertQuery(filesProp)));
            partyPeople.forEach(x -> tables.add(x==null ? null : x.writeInsertQuery(filesProp)));
            documents.forEach(x -> tables.add(x==null ? null : x.writeInsertQuery(filesProp)));

            //GETTING SELECT STATEMENT
            application.writeSelectQuery(tables, filesProp);

        }else{
            throw new NoApplicationFoundException("No Application "+polNo+" Found!");
        }

    }




}
