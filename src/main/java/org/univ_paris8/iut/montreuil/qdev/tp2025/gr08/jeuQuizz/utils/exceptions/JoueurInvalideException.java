package org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.utils.exceptions;

public class JoueurInvalideException extends DeterminerElementsDispoException {
    public JoueurInvalideException() {
        super("Liste Joueur inexistant : ");
    }
}