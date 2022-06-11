package com.ensah.core.web.controllers;


import com.ensah.core.bo.Module;
import com.ensah.core.bo.*;
import com.ensah.core.service.*;
import com.ensah.core.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/note")
public class noteControlller {



    @Autowired
    IPersonService iPersonService;
    @Autowired
    ICompteService iCompteService;
    @Autowired
    IElementsService iElementsService;
    @Autowired
    INiveauService iNiveauService;
    @Autowired
    IModuleService iModuleService;
    @Autowired
    IInscriptionService iInscriptionService;
    @Autowired
    IInscriptionElementService iInscriptionElementService;
    @Autowired
    IInscriptionModuleService iInscriptionModuleService;
    @Autowired
    IInscriptionAnnuelleService iInscriptionAnnuelleService;


    @RequestMapping("/form")
    public String forExcel(Model model){
        model.addAttribute("Message");
        return "Excel";
    }



    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        //check excel format
        if (Helper.checkExcelFormat(file)) {


            //vérifier que le nom de l’enseignant est celui existant dans la base de données
            List<String> Enseignant=Helper.getEnseignant(file.getInputStream());

            for(int i=0;i<Enseignant.size();i++){
                List<Utilisateur> user=iPersonService.getPersonByName(Enseignant.get(i).split(" ")[1],Enseignant.get(i).split(" ")[0]);
                if(user!=null){
                    for(int j=0;j<user.size();j++){

                        if(iCompteService.getRole(user.get(j).getIdUtilisateur())==1){
                            System.out.println(Enseignant.get(i));
                        }else{
                            model.addAttribute("Message",new Message("l’enseignant "+user.get(j).getNom()+" "+ user.get(j).getPrenom()+" n'existe pas dans la base de données",Message.ERROR));
                            return "ErrorNote";
                        }
                    }
                }
            }

            System.out.println(Helper.countStudents(file.getInputStream()));
            System.out.println(Helper.getAllCells(file.getInputStream()));



          for(int contS=0;contS<Helper.countStudents(file.getInputStream());contS++){


              //Les indices des modules
            List<Integer> cellNumber=Helper.getCells(file.getInputStream());

            //Recuperer les modules depuis le fichier excel
            List<String> ModulesExcel = Helper.getModule(file.getInputStream());


            List<Double> notes=new ArrayList<>();

            for(int i=0;i<ModulesExcel.size();i++) {

                //Recuperer les elements

                List<String> elementsExcel = Helper.getElements(file.getInputStream(), cellNumber.get(i));

                //Recuperer les notes

                Map<String, Map<String, Double>> n = Helper.getNotes(file.getInputStream(), contS, elementsExcel, ModulesExcel.get(i), cellNumber.get(i));

                for (int j = 0; j < n.get(ModulesExcel.get(i)).size(); j++) {

                    System.out.println(elementsExcel.get(j));

                    double val = n.get(ModulesExcel.get(i)).get(elementsExcel.get(j));

                    //Verifier que les notes sont compris entre 0 et 20
                    if (val > 20 || val < 0) {

                        model.addAttribute("Message",new Message("la note de l element "+elementsExcel.get(j)+" de l etudiant "+contS+" doivent être des réels entre 0.0 et 20.0",Message.ERROR));
                        return "ErrorNote";
                    } else {
                        notes.add(val);
                    }

                }
            }

            List<Niveau> list=iNiveauService.getAllNiveau();

            int cnt=0;

            for(int i=0;i<list.size();i++){

                System.out.println(list.get(i).getTitre());

                //Verifier le niveau
                if(Helper.getNiveau(file.getInputStream()).equals(list.get(i).getTitre())){
                    //Recuperer les modules par niveau
                    List<Module> Modules=iModuleService.getModulesByNiveau(list.get(i).getIdNiveau());

                    List<String> ModulesExcel2 = Helper.getModule(file.getInputStream());

                    String Annee=iInscriptionService.getAnnee(list.get(i).getIdNiveau())+"";

                    //Verifier l’année universitaire

                    if(Helper.getAnnees(file.getInputStream()).split("/")[0].equals(Annee)){
                        System.out.println("YEAR :2021");
                    }else{
                        model.addAttribute("Message",new Message(" l’année universitaire n est pas correcte",Message.ERROR));
                        return "ErrorNote";
                    }
                    double Sm=0;


                    //Inserer les notes des modules dans la base de donnees

                    for(int j=0;j<Modules.size();j++){

                        if(ModulesExcel2.get(j).equals(Modules.get(j).getTitre())){

                            List<Element> elements=iElementsService.getElementsByModule(Modules.get(j).getIdModule());

                            List<String> elementsExcel2 = Helper.getElements(file.getInputStream(),cellNumber.get(j));

                            double Somme=0;

                            //Inserer les notes des elements
                            for (int k=0;k<elements.size();k++){

                              // System.out.println(elements.get(k).getNom());
                                InscriptionMatiere var=iInscriptionElementService.getInscriptionMatiereBy(elements.get(k).getIdMatiere());
                                if(elementsExcel2.get(k).equals(elements.get(k).getNom())){
                                    if(contS==0){
                                        iInscriptionElementService.Update((Double) notes.get(cnt),elements.get(k).getIdMatiere(),Long.valueOf(1));
                                    }
                                   //Verifier la Moyenne
                                    Somme=Somme+notes.get(cnt)*var.getCoefficient();
                                    cnt++;

                                }else {
                                    model.addAttribute("Message",new Message("Les elements ne sont pas cohérents avec la classe",Message.ERROR));
                                    return "ErrorNote";
                                }

                            }
                            System.out.println(Somme);

                            //Inserer les notes des modules

                            Map<String, Double> Moyenne = Helper.getMoyenne(file.getInputStream(), contS, elementsExcel2, ModulesExcel.get(j), cellNumber.get(j));
                            Map<String,String> validation=Helper.getValidationModule(file.getInputStream(), contS, elementsExcel2, ModulesExcel.get(j), cellNumber.get(j));
                            System.out.println(Moyenne);

                            if(Moyenne.get(ModulesExcel.get(j))==Somme){

                                if((Somme<12 && validation.get(ModulesExcel.get(j)).equals("V"))||(Somme>12 && validation.get(ModulesExcel.get(j)).equals("NV")) ){
                                    model.addAttribute("Message",new Message("La formule de validation est incorrecte",Message.ERROR));
                                    return "ErrorNote";
                                }

                                if(contS==0){

                                    List<InscriptionModule> noteD=iInscriptionModuleService.getAll();

                                    for(int c=0;c<noteD.size();c++){

                                       // if(noteD.get(c).getNoteFinale()!=0){
                                                iInscriptionModuleService.update(Moyenne.get(ModulesExcel.get(j)),validation.get(ModulesExcel.get(j)),Modules.get(j).getIdModule(),Long.valueOf(1));

                                     //   }
                                    }

                                }

                               //La somme des moyennes des modules

                                Sm+=Somme;

                                System.out.println("Formule de moyenne est correcte");

                            }else{
                                model.addAttribute("Message",new Message("La formule de calcul des moyennes des modules est incorrecte",Message.ERROR));
                                return "ErrorNote";
                            }

                        }else {

                            model.addAttribute("Message",new Message("Les modules ne sont pas cohérents avec la classe",Message.ERROR));
                            return "ErrorNote";

                        }
                    }

                    //Inserer la note final

                    double moy=Sm/Modules.size();

                    double moyy=Helper.AllMoyenne(file.getInputStream(),contS,Helper.getAllCells(file.getInputStream()));

                    int rang=Helper.getRang(file.getInputStream(),contS,Helper.getAllCells(file.getInputStream()));

                    if(moy==moyy){
                        if(contS==0){
                            if(moy>12){

                                iInscriptionAnnuelleService.update(rang,"V",Long.valueOf(1));
                            }else{
                                iInscriptionAnnuelleService.update(rang,"NV",Long.valueOf(1));
                            }
                        }


                        System.out.println("Moyenne generale a ete bien verifier");
                    }else{
                        model.addAttribute("Message",new Message("La formule de calcul de moyenne generale est incorrecte",Message.ERROR));
                        return "ErrorNote";
                    }

                }else{
                    model.addAttribute("Message",new Message(" le niveau n est pas correcte",Message.ERROR));
                    return "ErrorNote";
                }

                }
          }

            model.addAttribute("Message",new Message("Les notes sont insérées avec succès",Message.INFO));
            return "Message";


        }
        model.addAttribute("Message",new Message("Le format de fichier n est pas valide",Message.ERROR));
        return "ErrorNote";
    }
}
