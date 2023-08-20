package io.winty.alura.learningspring.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.winty.alura.learningspring.domain.consulta.validacoes.ValidadorDadosAgendamentoConsulta;
import io.winty.alura.learningspring.domain.medico.Medico;
import io.winty.alura.learningspring.domain.medico.MedicoRepository;
import io.winty.alura.learningspring.domain.paciente.PacienteRepository;
import io.winty.alura.learningspring.infra.exception.ValidacaoException;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private List<ValidadorDadosAgendamentoConsulta> validadores;
    
    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        
        validadores.forEach(v->v.validar(dados));
        
        
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        
        if( medico == null){
            throw new ValidacaoException("Não tem médico disponível");
        }
        var consulta = new Consulta(null, medico,paciente, dados.data());
        consultaRepository.save(consulta);
        
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null ) {
           return medicoRepository.getReferenceById(dados.idMedico());
        }
        
        if ( dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
