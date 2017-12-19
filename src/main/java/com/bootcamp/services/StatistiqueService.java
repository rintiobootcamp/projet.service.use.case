package com.bootcamp.services;

//import com.bootcamp.client.AxeClient;
//import com.bootcamp.client.ProjetClient;
import com.bootcamp.client.*;
import com.bootcamp.commons.ws.usecases.pivotone.ProjetWS;
import com.bootcamp.entities.Commentaire;
import com.bootcamp.entities.Debat;
import com.bootcamp.entities.LikeTable;
import com.bootcamp.entities.Projet;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bignon.
 */

@Component
public class StatistiqueService {

    DebatClient debatClient;
    CommentaireClient commentaireClient;
    LikeClient likeClient;


    @PostConstruct
    public void init(){

        debatClient = new DebatClient();
        commentaireClient = new CommentaireClient();
        likeClient = new LikeClient();
    }

    public List<HashMap<String,Double>> getStatistique(String entity, int entityId,long startDate, long endDate) throws IOException {
        List<Debat> debats = debatClient.getByEntityType(entity,startDate,endDate);
        List<Commentaire> commentaires = commentaireClient.getAllCommentByAllEntity(entity,startDate,endDate);
        List<LikeTable> likes = likeClient.getAllLikeOrUnlikeByEntity(entity,startDate,endDate);
         //Pour debat
        double Dnbr1 = 0;
        double Dnbr2 = 0;
        //Pour Commentaire
        double Cnbr1 = 0;
        double Cnbr2 = 0;
        //Pour Like/Unlike
        double Lnbr1 = 0;
        double Lnbr2 = 0;
        double ULnbr1 = 0;
        double ULnbr2 = 0;

        for (Debat debat : debats) {
            if (debat.getEntityId() == entityId)
                Dnbr1 ++;
        }

        for (Commentaire commentaire : commentaires) {
            if (commentaire.getEntityId() == entityId)
                Cnbr1 ++;
        }

        for (LikeTable like : likes) {
            if (like.isLikeType()){
                Lnbr2 ++;
                if (like.getEntityId()==entityId)
                    Lnbr1 ++;
            }else if (!like.isLikeType()){
                ULnbr2 ++ ;
                if (like.getEntityId() == entityId)
                    ULnbr1 ++;
            }


        }
        List<HashMap<String,Double>> toreturn = null;
        HashMap<String,Double> map = null;
        map.put("Taux de like du "+entity+" "+entityId, (Lnbr1/Lnbr2)*100);
        toreturn.add(map);
        map.put("Taux de unlike du "+entity+" "+entityId, (ULnbr1/ULnbr2)*100);
        toreturn.add(map);
        map.put("Taux de commentaire de "+entity+" "+entityId, (Cnbr1/Cnbr2)*100);
        toreturn.add(map);
        map.put("Taux de Debat de "+entity+" "+entityId, (Dnbr1/Dnbr2)*100);
        toreturn.add(map);

            return toreturn;
    }

//    public List<HashMap<String,Double>> getStatistiqueByEntity(String entity,long startDate, long endDate) throws IOException {
//        List<Debat> debats = debatClient.getByEntityType(entity,startDate,endDate);
//        List<Commentaire> commentaires = commentaireClient.getAllCommentByAllEntity(entity,startDate,endDate);
//        List<LikeTable> likes = likeClient.getAllLikeOrUnlikeByEntity(entity,startDate,endDate);
//        //Pour debat
//        double Dnbr1 = 0;
//        double Dnbr2 = 0;
//        //Pour Commentaire
//        double Cnbr1 = 0;
//        double Cnbr2 = 0;
//        //Pour Like/Unlike
//        double Lnbr1 = 0;
//        double Lnbr2 = 0;
//        double ULnbr1 = 0;
//        double ULnbr2 = 0;
//
//        for (Debat debat : debats) {
//                Dnbr1 ++;
//        }
//
//        for (Commentaire commentaire : commentaires) {
//                Cnbr1 ++;
//        }
//
//        for (LikeTable like : likes) {
//            if (like.isLikeType()){
//                Lnbr2 ++;
//
//                    Lnbr1 ++;
//            }else if (!like.isLikeType()){
//                ULnbr2 ++ ;
//
//                    ULnbr1 ++;
//            }
//
//
//        }
//        List<HashMap<String,Double>> toreturn = null;
//        HashMap<String,Double> map = null;
//        map.put("Taux de like du "+entity+" est:"+, (Lnbr1/Lnbr2)*100);
//        toreturn.add(map);
//        map.put("Taux de unlike du "+entity+" "+, (ULnbr1/ULnbr2)*100);
//        toreturn.add(map);
//        map.put("Taux de commentaire de "+entity+" "+, (Cnbr1/Cnbr2)*100);
//        toreturn.add(map);
//        map.put("Taux de Debat de "+entity+", (Dnbr1/Dnbr2)*100);
//        toreturn.add(map);
//
//        return toreturn;
//    }
}
