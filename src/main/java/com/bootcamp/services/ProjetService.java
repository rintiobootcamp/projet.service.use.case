package com.bootcamp.services;

//import com.bootcamp.client.AxeClient;
//import com.bootcamp.client.ProjetClient;
import com.bootcamp.client.ProjetClient;
import com.bootcamp.client.SecteurClient;
import com.bootcamp.commons.ws.usecases.pivotone.ProjetWS;
import com.bootcamp.entities.Projet;
import com.bootcamp.helpers.ProjetHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darextossa on 11/29/17.
 */

@Component
public class ProjetService {

    ProjetClient projetClient;

    @PostConstruct
    public void init(){

        projetClient = new ProjetClient();
    }

    public List<ProjetWS> getAll() throws IOException {
        List<Projet> projets = projetClient.findAll();
        List<ProjetWS> result = new ArrayList<>();
        for (Projet current : projets) {
            ProjetWS projetWS = new ProjetWS();
            projetWS = ProjetHelper.buildProjetWsObject(current);
            result.add(projetWS);
        }
            return result;
    }

    public ProjetWS getProjet(int idProjet) throws IOException{
       Projet projet= projetClient.getById(idProjet);
        return ProjetHelper.buildProjetWsObject(projet);
    }
}
