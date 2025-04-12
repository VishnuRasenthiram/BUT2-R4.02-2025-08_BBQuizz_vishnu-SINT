package org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.entities.dto.DeterminerElementsDispoDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.services.impl.DeterminerElementsDispoService;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.services.impl.QuestionnaireServicesImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.utils.exceptions.JoueurInvalideException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr08.jeuQuizz.utils.exceptions.QuestionnaireInvalideException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr8.jeuQuizz.services.impl.JoueurService;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr8.jeuQuizz.utils.enums.Langue;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr8.jeuQuizz.entities.dto.JoueurDTO;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class DeterminerElementDispoTest {

    @Test
    void testDeterminerElementsDispoPourPartie_Success() throws Exception {
        ArrayList<JoueurDTO> fakeJoueurs = new ArrayList<>(Arrays.asList(
                new JoueurDTO("Joueur1", "Lucas", 2000, new ArrayList<>(), Langue.FRANCAIS),
                new JoueurDTO("Joueur2", "Marie", 1999, new ArrayList<>(), Langue.ENGLISH)
        ));

        ArrayList<QuestionnaireDTO> fakeQuestionnaires = new ArrayList<>(Arrays.asList(
                new QuestionnaireDTO("Questionnaire 1"),
                new QuestionnaireDTO("Questionnaire 2")
        ));

        try (MockedConstruction<JoueurService> mockedJoueur =
                     Mockito.mockConstruction(JoueurService.class,
                             (mock, context) -> Mockito.when(mock.listerJoueur()).thenReturn(fakeJoueurs))) {

            try (MockedConstruction<QuestionnaireServicesImpl> mockedQuest =
                         Mockito.mockConstruction(QuestionnaireServicesImpl.class,
                                 (mock, context) -> Mockito.when(mock.fournirListeQuestionnaire(Mockito.anyString()))
                                         .thenReturn(fakeQuestionnaires))) {

                DeterminerElementsDispoService service = new DeterminerElementsDispoService();
                DeterminerElementsDispoDTO result = service.determinerElementsDispoPourPartie(List.of("Lucas", "Marie"), 1);

                assertNotNull(result);
                assertEquals(fakeJoueurs, result.getListeJoueurs());
                assertEquals(fakeQuestionnaires, result.getListeQuestionnaires());
            }
        }
    }

    @Test
    void testDeterminerElementsDispoPourPartie_EmptyJoueurs() {
        try (MockedConstruction<JoueurService> mockedJoueur =
                     Mockito.mockConstruction(JoueurService.class,
                             (mock, context) -> Mockito.when(mock.listerJoueur()).thenReturn(new ArrayList<>()))) {

            try (MockedConstruction<QuestionnaireServicesImpl> mockedQuest =
                         Mockito.mockConstruction(QuestionnaireServicesImpl.class,
                                 (mock, context) -> Mockito.when(mock.fournirListeQuestionnaire(Mockito.anyString()))
                                         .thenReturn(new ArrayList<>(Arrays.asList(new QuestionnaireDTO("Q")))))) {

                DeterminerElementsDispoService service = new DeterminerElementsDispoService();

                assertThrows(JoueurInvalideException.class, () ->
                        service.determinerElementsDispoPourPartie(List.of("test"), 1));
            }
        }
    }

    @Test
    void testDeterminerElementsDispoPourPartie_EmptyQuestionnaires() {
        ArrayList<JoueurDTO> fakeJoueurs = new ArrayList<>(Arrays.asList(
                new JoueurDTO("Joueur1", "Lucas", 2000, new ArrayList<>(), Langue.FRANCAIS)
        ));

        try (MockedConstruction<JoueurService> mockedJoueur =
                     Mockito.mockConstruction(JoueurService.class,
                             (mock, context) -> Mockito.when(mock.listerJoueur()).thenReturn(fakeJoueurs))) {

            try (MockedConstruction<QuestionnaireServicesImpl> mockedQuest =
                         Mockito.mockConstruction(QuestionnaireServicesImpl.class,
                                 (mock, context) -> Mockito.when(mock.fournirListeQuestionnaire(Mockito.anyString()))
                                         .thenReturn(new ArrayList<>()))) {

                DeterminerElementsDispoService service = new DeterminerElementsDispoService();

                assertThrows(QuestionnaireInvalideException.class, () ->
                        service.determinerElementsDispoPourPartie(List.of("Lucas"), 1));
            }
        }
    }
}