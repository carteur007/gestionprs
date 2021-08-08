package com.carteur.gestionprs.repositories;

import java.util.List;
import com.carteur.gestionprs.missions.MisionsUser;

public interface CustomMisionUserRepository {

   public List<MisionsUser> findMisionsUserByUserId(long id_user);
   public List<MisionsUser> findMisionsUserByMisionId(long id_mission);
   public List<MisionsUser> findMisionsUserByMisionIdAndUserId(long id_mission, long id_user);

}