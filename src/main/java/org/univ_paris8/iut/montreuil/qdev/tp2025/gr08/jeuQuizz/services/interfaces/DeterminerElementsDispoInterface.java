package org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.services.interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.entities.dto.DeterminerElementsDispoDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.utils.exceptions.DeterminerElementsDispoException;

public interface DeterminerElementsDispoInterface {
    public DeterminerElementsDispoDTO DeterminerElementsDispo() throws DeterminerElementsDispoException;
}
