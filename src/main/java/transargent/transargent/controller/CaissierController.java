package transargent.transargent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import transargent.transargent.dao.CaissierRepository;
import transargent.transargent.dao.CompteRepository;
import transargent.transargent.dao.ContratRepository;
import transargent.transargent.dao.RoleRepository;
import transargent.transargent.model.Caissier;
import transargent.transargent.model.Compte;
import transargent.transargent.model.Contrat;
import transargent.transargent.model.Role;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/caissier")
public class CaissierController {

    private static String UPLOADED_FOLDER = "C://Users//DELL//Downloads//images//";
    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private RoleRepository  roleRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private CaissierRepository caissierRepository;


    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CAISSIER')")
    @GetMapping("/all")
    public String caissierPage(Model model) {
        Caissier caissier = new Caissier();
        caissier.setContrat(new Contrat());
        model.addAttribute("caissier", caissier);
        model.addAttribute("contrats", contratRepository.findAll());
        model.addAttribute("caissiers", caissierRepository.findAll());
        return "caissier";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public String addcaissier(@ModelAttribute("caissier") Caissier caissier) {
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepository.findByLibelle("ROLE_CAISSIER"));

        byte[] bytes = {};
        Path path = null;
        try {
            if(!caissier.getFiles()[0].getName().equals("")){
                MultipartFile file = caissier.getFiles()[0];
                bytes = file.getBytes();
                path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                caissier.setPhoto(file.getOriginalFilename());
            }
            else{
                caissier.setPhoto("imgui-zac.jpg");
            }
            Compte compte = new Compte();
            caissier.getContrat().setType("cdd");
            compte.setSolde(100000);
            compte.setType("caissier");
            caissier.setCompte(compte);
            caissier.setRoles(roleList);
            compteRepository.save(caissier.getCompte());
            contratRepository.save(caissier.getContrat());
            caissier.setPassword(encoder.encode("passer"));
            //caissier.setContrat(contratRepository.findById(contrat.getId()).get());
            caissierRepository.save(caissier);
            if(bytes.length != 0) {
                Files.write(path, bytes);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return "redirect:/caissier/all";
    }

    @RequestMapping(value = "image/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
        System.out.println("called");
        File serverFile = new File("C://Users//DELL//Downloads//images//" + imageName + ".jpg");
        return Files.readAllBytes(serverFile.toPath());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public @ResponseBody
    Caissier OneCaissier(@PathVariable(name = "id") long caissierId){
        return caissierRepository.findById(caissierId).get();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/remove")
    public String addcaissier(long caissierId) {
        Caissier c = caissierRepository.getOne(caissierId);
        Compte cpt = compteRepository.getOne(c.getCompte().getId());
        Contrat ctr = contratRepository.getOne(c.getContrat().getId());
        compteRepository.delete(cpt);
        contratRepository.delete(ctr);
        caissierRepository.delete(c);
        return "redirect:/caissier/all";
    }

    public String randomCode(int length) {
        Random random = new Random();
        final int alphabetLength = 'Z' - 'A' + 1;
        StringBuilder result = new StringBuilder(length);
        while (result.length() < length) {
            final char charCaseBit = (char) (random.nextInt(2) << 5);
            result.append((char) (charCaseBit | ('A' + random.nextInt(alphabetLength))));
        }
        return result.toString();
    }

}
