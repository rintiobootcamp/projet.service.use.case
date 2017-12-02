package com.bootcamp.helpers;

import com.bootcamp.commons.ws.usecases.pivotone.ProjetWS;
import com.bootcamp.commons.ws.usecases.pivotone.SecteurWS;
import com.bootcamp.entities.Axe;
import com.bootcamp.entities.Pilier;
import com.bootcamp.entities.Projet;
import com.bootcamp.entities.Secteur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by darextossa on 11/29/17.
 */
public class SecteurHelper {

    public static SecteurWS buildSecteurWsObject(Secteur secteur, List<Projet> projets, Boolean addParent) throws IOException {
        SecteurWS secteurWS = new SecteurWS();
        secteurWS.setId(secteur.getId());
        secteurWS.setDateMiseAJour(secteur.getDateMiseAJour());
        secteurWS.setDateCreation(secteur.getDateCreation());
        secteurWS.setIcone(secteur.getIcone());
        secteurWS.setNom(secteur.getNom());
        if(addParent)
            secteurWS = addParent(secteur, secteurWS);

        List<ProjetWS> projetWSS = new ArrayList<>();
        projets = getListProjets(secteur.getId(), projets);
        for(Projet projet: projets){
            ProjetWS projetWS = ProjetHelper.buildProjetWsObject(projet);
            projetWSS.add(projetWS);
        }
        return secteurWS;
    }

    public static SecteurWS buildNoParentSecteurWs(Secteur secteur,Boolean addParent) {
        SecteurWS secteurWS = new SecteurWS();
        secteurWS.setId(secteur.getId());
        secteurWS.setDateMiseAJour(secteur.getDateMiseAJour());
        secteurWS.setDateCreation(secteur.getDateCreation());
        secteurWS.setIcone(secteur.getIcone());
        secteurWS.setNom(secteur.getNom());
        if(addParent)
            secteurWS = addParent(secteur, secteurWS);
        return secteurWS;
    }

    private static List<Projet> getListProjets(int secteurId, List<Projet> projets){
        List<Projet> selectedProjets = new ArrayList<>();
        for(Projet projet: projets){
            if(secteurId == projet.getIdSecteur())
                selectedProjets.add(projet);
        }

        return selectedProjets;
    }

    public static SecteurWS addParent(Secteur secteur, SecteurWS secteurWS){
        Axe axe = secteur.getAxe();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", axe.getId());
        map.put("dateCreation", axe.getDateCreation());
        map.put("dateMiseAJour", axe.getDateMiseAJour());
        map.put("nom", axe.getNom());
        map.put("description", axe.getDescription());
        map.put("titre",axe.getTitre());
        map.put("titreFocus",axe.getTitreFocus());
        map.put("descriptionFocus",axe.getDescriptionFocus());

        HashMap<String, Object> pilierMap = new HashMap<>();
        Pilier pilier = secteur.getAxe().getPilier();
        pilierMap.put("id", pilier.getId());
        pilierMap.put("dateCreation", pilier.getDateCreation());
        pilierMap.put("dateMiseAJour", pilier.getDateMiseAJour());
        pilierMap.put("nom", pilier.getNom());
        pilierMap.put("description", pilier.getDescription());

        map.put("pilier", pilierMap);

        secteurWS.setAxe(map);

        return secteurWS;
    }


    public static List<SecteurWS> buildSecteurWSList(List<Secteur> secteurs, List<Projet> projetList) throws IOException{
        List<SecteurWS> secteurWSS = new ArrayList<>();
        for(Secteur secteur: secteurs){
            SecteurWS secteurWS = buildSecteurWsObject(secteur, projetList, true);
            secteurWSS.add(secteurWS);
        }
        return secteurWSS;
    }

}
