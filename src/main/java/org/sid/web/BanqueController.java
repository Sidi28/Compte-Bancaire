package org.sid.web;
import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IbanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
    @Autowired
    private IbanqueMetier ibanqueMetier;

    @RequestMapping("/operations")
    public String index(){
        return "comptes";
    }

    @RequestMapping("/consulterCompte")
    public String consulter(Model model, String codeCompte, @RequestParam(name="page",defaultValue ="0")int page,
                            @RequestParam(name="size",defaultValue ="5")int size){
        model.addAttribute("codeCompte",codeCompte);
        try{
             Page<Operation> PageOperations=ibanqueMetier.listOperation(codeCompte,page,size);
            model.addAttribute("PageOperations",PageOperations.getContent());

            int[] pages=new int[PageOperations.getTotalPages()];
            model.addAttribute("pages",pages);

            Compte cp=ibanqueMetier.ConsulterCompte(codeCompte);
            model.addAttribute("compte",cp);
        }catch(Exception e){
            model.addAttribute("exception",e);
        }
        return "comptes";
    }

    @RequestMapping(value="/saveOperation",method=RequestMethod.POST)
    public String saveOperation(Model model,String codeCompte, String typeOperation,
                                     String codeCompte2,double montant){
        try{
            if(typeOperation.equals("VERS")){
                ibanqueMetier.verser(codeCompte,montant);
            } else if(typeOperation.equals("RET")){
                ibanqueMetier.retirer(codeCompte,montant);
            }else if(typeOperation.equals("VIR")) {
                ibanqueMetier.virement(codeCompte,codeCompte2,montant);
            }
        }catch(Exception e){
            model.addAttribute("error",e);
            return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
        }

        return "redirect:/consulterCompte?codeCompte="+codeCompte;
    }




}
