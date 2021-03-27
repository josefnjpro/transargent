package transargent.transargent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import transargent.transargent.dao.CaissierRepository;
import transargent.transargent.dao.ClientRepository;
import transargent.transargent.dao.CompteRepository;
import transargent.transargent.model.Client;
import transargent.transargent.model.Compte;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteRepository compteRepository;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CAISSIER')")
    @GetMapping("/all")
    public String adminPage(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("clients", clientRepository.findAll());
        return "admin";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public String addclient(@ModelAttribute("client") Client client) {
        try {
            Compte compte=new Compte();
            compte.setSolde(0);
            compte.setType("client");
            client.setCompte(compte);
            compteRepository.save(client.getCompte());
            clientRepository.save(client);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/all";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public @ResponseBody
    Client OneClient(@PathVariable(name = "id") long clientId){
        return clientRepository.findById(clientId).get();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/remove")
    public String addclient(long clientId) {
        Client c = clientRepository.getOne(clientId);
        Compte cpt = compteRepository.getOne(c.getCompte().getId());
        clientRepository.delete(c);
        compteRepository.delete(cpt);
        return "redirect:/admin/all";
    }

    /*@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public @ResponseBody
    Client update(@PathVariable(name = "id") long clientId){
        Client lui =  clientRepository.findById(clientId).get();


        return "redirect:/admin/all";
    }*/
}
