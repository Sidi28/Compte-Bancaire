package org.sid.metier;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;

public interface IbanqueMetier {

    public  Compte ConsulterCompte(String codeCompte);
    public void verser(String codeCpte,double Montant);
    public void retirer(String codeCpte,double Montant);
    public void virement(String codeCpte1,String codeCpte2,double Montant);
    public Page<Operation> listOperation(String codeCpte, int page, int size);
}
