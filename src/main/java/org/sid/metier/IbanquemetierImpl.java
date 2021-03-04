package org.sid.metier;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class IbanquemetierImpl implements IbanqueMetier {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;


    @Override
    public Compte ConsulterCompte(String codeCompte) {
        Compte cp =compteRepository.findById(codeCompte).get();
        if(cp==null) throw new RuntimeException("compte introuvable");
        return cp;
    }

    @Override
    public void verser(String codeCpte, double Montant) {
       Compte cp=ConsulterCompte(codeCpte);
       Versement V = new Versement(new Date(),Montant,cp);
        operationRepository.save(V);
        cp.setSolde(cp.getSolde()+ Montant);
        compteRepository.save(cp);
    }

    @Override
    public void retirer(String codeCpte, double Montant) {
        Compte cp=ConsulterCompte(codeCpte);
        double facilitecaisse =0;
        if(cp instanceof CompteCourant)
            facilitecaisse =((CompteCourant) cp).getDecouvert();
        if(cp.getSolde()+facilitecaisse < Montant)
            throw new RuntimeException("Solde insuffisant");
        Retrait R = new Retrait(new Date(),Montant,cp);
        operationRepository. save(R);
        cp.setSolde(cp.getSolde() - Montant);
        compteRepository.save(cp);
    }

    @Override
    public void virement(String codeCpte1, String codeCpte2, double Montant) {
        if(codeCpte1.equals(codeCpte2))
            throw new RuntimeException("impossible un virement sur le même compte!");
        retirer(codeCpte1,Montant);
        verser(codeCpte2,Montant);
    }

    @Override
    public Page<Operation> listOperation(String codeCpte, int page, int size) {
        return operationRepository.listOperation(codeCpte, PageRequest.of(page,size));
    }
}
