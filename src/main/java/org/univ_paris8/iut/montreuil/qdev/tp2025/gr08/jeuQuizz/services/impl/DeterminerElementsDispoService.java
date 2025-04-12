package org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.services.impl;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.entities.dto.DeterminerElementsDispoDTO;


import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.utils.exceptions.*;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr8.jeuQuizz.entities.dto.JoueurDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr8.jeuQuizz.services.impl.JoueurService;

import java.util.ArrayList;
import java.util.List;

public class DeterminerElementsDispoService {
    public DeterminerElementsDispoDTO determinerElementsDispoPourPartie(List<String> pseudos, int idQuestionnaire)
            throws DeterminerElementsDispoException, QuestInvalideException, FichierInexistantException {

        JoueurService joueurService = JoueurService.getInstance();
        QuestionnaireServicesImpl questionnaireServices = new QuestionnaireServicesImpl();

        ArrayList<JoueurDTO> listJoueurs = joueurService.listerJoueur();
        ArrayList<QuestionnaireDTO> listeQuestionnaire = questionnaireServices.fournirListeQuestionnaire("src/main/resources/quest_invalide.csv");

        if (listJoueurs.isEmpty()) {
            throw new JoueurInvalideException();
        }

        if (listeQuestionnaire.isEmpty()){
            throw new QuestionnaireInvalideException();
        }

        return  new DeterminerElementsDispoDTO(listeQuestionnaire,listJoueurs);
    }
}