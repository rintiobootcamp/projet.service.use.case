package com.bootcamp.services;

import com.bootcamp.client.*;
import com.bootcamp.output.SondagePrime;
import com.bootcamp.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SondageService {

    private ProjetClient projetClient;
    private AxeClient axeClient;
    private PilierClient pilierClient;
    private SecteurClient secteurClient;
    private DebatClient debatClient;
    private SondageClient sondageClient;

    @PostConstruct
    public void init(){
        projetClient = new ProjetClient();
        axeClient = new AxeClient();
        pilierClient = new PilierClient();
        secteurClient = new SecteurClient();
        debatClient = new DebatClient();
        sondageClient = new SondageClient();
    }


    public String getEntityTitle(String entityType,int entityId){
        String entity = entityType.toUpperCase();
        String entityLabel = "";

        switch (entity){
            case "PROJET":
                try {
                    Projet projet = projetClient.getById(entityId);
                    entityLabel = projet.getNom();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "AXE" :
                try {
                    Axe axe = axeClient.getById(entityId);
                    entityLabel = axe.getNom();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "PILIER" :
                try {
                    Pilier pilier = pilierClient.getById(entityId);
                    entityLabel = pilier.getNom();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "SECTEUR" :
                try {
                    Secteur secteur = secteurClient.getById(entityId);
                    entityLabel = secteur.getNom();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "DEBAT" :
                try {
                    Debat debat = debatClient.getById(entityId);
                    entityLabel = debat.getSujet();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return entityLabel;

    }

    public Question getUneSongade(){
        List<Question> unes = new ArrayList<>();
        try {
            unes  = sondageClient.getUnesQuestion();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return unes.get(0);
    }

    public SondagePrime getPrime(){
        SondagePrime sondagePrime = new SondagePrime();
        Question question = getUneSongade();
        String entityLabel = getEntityTitle(question.getEntityType(),question.getEntityId());
        ModelMapper modelMapper = new ModelMapper();
        sondagePrime = modelMapper.map(question,SondagePrime.class);
        sondagePrime.setEntityTypelabel(entityLabel);
        return sondagePrime;
    }


}
