package io.winty.alura.learningspring.domain.medico;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ActiveProfiles("test")
public class MedicoRepositoryTest {
    
    
    @Autowired
    private MedicoRepository repository;
    
    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao está disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario1(){
        var proximaSegundaAs10 = LocalDate.now()
        .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
        .atTime(10, 0);
        
        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.ORTOPEDIA, proximaSegundaAs10);
        assertNotNull(medicoLivre);
    }
}
