package org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.entities.dto;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr8.jeuQuizz.entities.dto.JoueurDTO;

import java.util.ArrayList;

public class DeterminerElementsDispoDTO {
    private ArrayList<QuestionnaireDTO> listeQuestionnaires;
    private ArrayList<JoueurDTO> listeJoueurs;

    public DeterminerElementsDispoDTO(ArrayList<QuestionnaireDTO> listeQuestionnaires, ArrayList<JoueurDTO> listeJoueurs) {
        this.listeQuestionnaires = listeQuestionnaires;
        this.listeJoueurs = listeJoueurs;
    }

    public ArrayList<QuestionnaireDTO> getListeQuestionnaires() {
        return listeQuestionnaires;
    }

    public ArrayList<JoueurDTO> getListeJoueurs() {
        return listeJoueurs;
    }
}
