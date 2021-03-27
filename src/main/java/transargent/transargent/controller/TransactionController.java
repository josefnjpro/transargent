package transargent.transargent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import transargent.transargent.dao.CaissierRepository;
import transargent.transargent.dao.ClientRepository;
import transargent.transargent.dao.TransactionRepository;
import transargent.transargent.model.*;


@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private CaissierRepository caissierRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @PreAuthorize("hasAuthority('ROLE_CAISSIER')")
    @GetMapping("/all")
    public String transactionPage(Model model) {
        Transaction transaction = new Transaction();
        transaction.setClient(new Client());
        transaction.setCaissier(new Caissier());
        model.addAttribute("transaction", transaction);
        model.addAttribute("client", clientRepository.findAll());
        model.addAttribute("caissier", caissierRepository.findAll());
        model.addAttribute("transactions", transactionRepository.findAll());
        return "transaction";
    }

    @PreAuthorize("hasAuthority('ROLE_CAISSIER')")
    @PostMapping("/add")

    public String addcaissier(@ModelAttribute("transaction") Transaction transaction) {
        try {
            Client c = clientRepository.findClientByNci(transaction.getClient().getNci());
            Caissier ca = caissierRepository.findCaissierByCode(transaction.getCaissier().getCode());
            long mtransac = transaction.getMontant();
            long asolde = ca.getCompte().getSolde();
            double bonus = 15./100.;
            double p = 5./100.;
            long calcul = 0;
            long frais = (long)((double)mtransac * p);
            if (transaction.getType().equals("depot"))
            {
                calcul = asolde - mtransac;
                calcul = (long)(calcul + (bonus * (double)frais));
            }else {
                calcul = asolde + (mtransac - frais);
                calcul = calcul + (long)(bonus * (double) frais);
            }
            ca.getCompte().setSolde(calcul);
            transaction.setClient(c);
            transaction.setCaissier(ca);
            transactionRepository.save(transaction);
        }catch(Exception e){
            e.printStackTrace();
        }

        return "redirect:/transaction/all";
    }

    @PreAuthorize("hasAuthority('ROLE_CAISSIER')")
    @GetMapping("/{id}")
    public @ResponseBody
    Transaction OneTransaction(@PathVariable(name = "id") long transactionId){
        return transactionRepository.findById(transactionId).get();
    }

    @PreAuthorize("hasAuthority('ROLE_CAISSIER')")
    @PostMapping("/remove")
    public String addcaissier(long transactionId) {
        Transaction t = transactionRepository.getOne(transactionId);
        transactionRepository.delete(t);
        return "redirect:/transaction/all";
    }
}
