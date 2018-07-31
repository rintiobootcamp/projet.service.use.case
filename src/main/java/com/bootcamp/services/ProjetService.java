package com.bootcamp.services;

import com.bootcamp.client.MediaClient;
import com.bootcamp.client.ProjetClient;
import com.bootcamp.entities.Media;
import com.bootcamp.entities.Projet;
import com.bootcamp.helpers.ProjetHelper;
import com.bootcamp.helpers.ProjetWS;
import com.rintio.elastic.client.ElasticClient;
import infos.ProjetAIO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rintio.
 */
@Component
public class ProjetService {
    ElasticClient elasticClient;
    ProjetClient projetClient;
    MediaClient mediaClient;
    ProjetHelper projetHelper = new ProjetHelper();
    List<Projet> projets;
    List<ProjetAIO> projetAIOS ;
    List<Media> medias;

    /**
     * Loading Projet Web Service client
     */
    @PostConstruct
    public void init() {
        elasticClient = new ElasticClient();
        mediaClient = new MediaClient();
        projetClient = new ProjetClient();
        projetAIOS = new ArrayList<>();
       medias =new ArrayList<>();

    }

    /**
     * Get all the projects in the database
     *
     * @return projects list
     * @throws IOException
     */
    public List<Object> getAll() throws IOException {
        List<Projet> projets = projetClient.findAll();
        List<Object> result = new ArrayList<>();
        for (Projet current : projets) {
           ProjetWS projetWS = new ProjetWS();
            projetWS = projetHelper.buildProjetWS(current);
            result.add(projetWS);
        }
        return result;
    }

    public List<Projet> getAllProjet() throws Exception{
        elasticClient = new ElasticClient();
        List<Object> objects = elasticClient.getAllObject("projets");
        ModelMapper modelMapper = new ModelMapper();
        List<Projet> rest = new ArrayList<>();
        for(Object obj:objects){
            rest.add(modelMapper.map(obj,Projet.class));
        }
        this.projets= rest;
        return rest;
    }

    public List<Media> getAllMediaIndex() throws Exception{
        ElasticClient elasticClient = new ElasticClient();
        List<Object> objects = elasticClient.getAllObject("medias");
        ModelMapper modelMapper = new ModelMapper();
        List<Media> rest = new ArrayList<>();
        for(Object obj:objects){
            rest.add(modelMapper.map(obj,Media.class));
        }
        return rest.stream().filter(t->t.getEntityType().equalsIgnoreCase("PROJET")).collect(Collectors.toList());
    }

    public List<ProjetAIO> buildProjetAIO() throws Exception{
        //List<ProjetAIO> projetAIOS = new ArrayList<>();
        if(this.projetAIOS.isEmpty()){
            this.projets = getAllProjet();
            this.medias = getAllMediaIndex();
            this.projets.forEach(t->{
                try {
                    List<Media> mediaList = getAllMediaIndex()
                            .stream().filter(k->k.getEntityId()==t.getId()).collect(Collectors.toList());
                    ProjetWS projetWS = new ProjetHelper().buildProjetWS(t);
                    this.projetAIOS.add(new ProjetAIO(projetWS,mediaList));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
//            this.projetAIOS = projetAIOS;
        }

        return projetAIOS;
    }

    public ProjetAIO getProjetAIOById(int id) throws Exception{
        if(this.projetAIOS==null)
            buildProjetAIO();
        return  this.projetAIOS.stream()
                .filter(t->t.getProjet().getId()==id).findFirst().get();
    }

    /**
     * Get a project knowing its id
     *
     * @param idProjet the project id
     * @return a project
     * @throws IOException
     */
    public ProjetWS getProjet(int idProjet) throws IOException {
        Projet projet = projetClient.getById(idProjet);
        return projetHelper.buildProjetWS(projet);
    }
}
