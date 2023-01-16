package br.com.ifpe.rhcontrolapi.config.listeners;

import br.com.ifpe.rhcontrolapi.model.DiaAbonado;
import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.MessageKafka;
import br.com.ifpe.rhcontrolapi.model.Ponto;
import br.com.ifpe.rhcontrolapi.model.enums.StatusPonto;
import br.com.ifpe.rhcontrolapi.repository.PontoRepository;
import br.com.ifpe.rhcontrolapi.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Component
public class AtestadoListener {

    @Autowired
    PontoRepository repository;

    @Autowired
    FuncionarioService funcionarioService;

    public static final String ATESTADO_TOPICO = "atestado-topico";

    @KafkaListener(groupId = "group-1", topics = ATESTADO_TOPICO, containerFactory = "jsonContainerFactory")
    public void listener(@Payload MessageKafka messageKafka) {

        saveOrUpdatePontoAtestado(messageKafka.getCodigoFuncionario(), messageKafka.getDiasAbonados(), messageKafka.getStatus());

    }

    private void saveOrUpdatePontoAtestado(Long codigoFuncionario, List<DiaAbonado> diasAbonados, String status) {

        diasAbonados.forEach(diaAbonado -> {
            Optional<Ponto> ponto = repository.findByCodigoFuncionarioAndDate(codigoFuncionario, diaAbonado.getDiaAbonado());
            if (ponto.isPresent()) {
                repository.save(configPontoAtestado(ponto.get(), status, diaAbonado.getDiaAbonado()));
            } else {
                try {
                    Funcionario funcionario = funcionarioService.getFuncionarioById(codigoFuncionario);
                    Ponto newPonto = new Ponto(funcionario, diaAbonado.getDiaAbonado());
                    newPonto.setCodigoPonto(repository.incrementarCodigoPonto() + 1);
                    newPonto.setStatus(StatusPonto.valueOf(StatusPonto.class, status.toUpperCase()));
                    repository.save(newPonto);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private Ponto configPontoAtestado(Ponto ponto, String status, LocalDate diaAbonado) {
        if (status.equalsIgnoreCase("ATESTADO")) {
            ponto.setHoraEntradaInicio(LocalDateTime.of(diaAbonado, LocalTime.of(9, 0, 0)));
            ponto.setHoraSaidaAlmoco(LocalDateTime.of(diaAbonado, LocalTime.of(12, 0, 0)));
            ponto.setHoraEntradaAlmoco(LocalDateTime.of(diaAbonado, LocalTime.of(13, 0, 0)));
            ponto.setHorasaidaFim(LocalDateTime.of(diaAbonado, LocalTime.of(18, 0, 0)));
            ponto.setStatus(StatusPonto.ATESTADO);

            return ponto;
        } else {
            ponto.setHoraEntradaInicio(null);
            ponto.setHoraSaidaAlmoco(null);
            ponto.setHoraEntradaAlmoco(null);
            ponto.setHorasaidaFim(null);
            ponto.setStatus(StatusPonto.ATESTADO_EM_ANALISE);
            return ponto;
        }
    }
}
