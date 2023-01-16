package br.com.ifpe.rhcontrolapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.rhcontrolapi.model.enums.StatusPonto;

@Entity
public class Ponto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoPonto;

	@ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
	@JoinColumn(name="codigo_funcionario")
	private Funcionario funcionario;

	@Enumerated(EnumType.STRING)
	private StatusPonto status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime horaEntradaInicio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime horaSaidaAlmoco;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime horaEntradaAlmoco;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime horaSaidaFim;

	public Ponto() {
	}
	
	public Ponto(Funcionario funcionario, LocalDate data, LocalDateTime horaEntradaInicio) {
		this.funcionario = funcionario;
		this.data = this.buildCompetencia(data);
		this.horaEntradaInicio = horaEntradaInicio;
	}

	public Ponto(Funcionario funcionario, LocalDate data) {
		this.funcionario = funcionario;
		this.data = this.buildCompetencia(data);
	}

	public Long getCodigoPonto() {
		return codigoPonto;
	}

	public void setCodigoPonto(Long codigoPonto) {
		this.codigoPonto = codigoPonto;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public StatusPonto getStatus() {
		return status;
	}

	public void setStatus(StatusPonto status) {
		this.status = status;
	}

	public LocalDateTime getHoraEntradaInicio() {
		return horaEntradaInicio;
	}

	public void setHoraEntradaInicio(LocalDateTime horaEntradaInicio) {
		this.horaEntradaInicio = horaEntradaInicio;
	}

	public LocalDateTime getHoraSaidaAlmoco() {
		return horaSaidaAlmoco;
	}

	public void setHoraSaidaAlmoco(LocalDateTime horaSaidaAlmoco) {
		this.horaSaidaAlmoco = horaSaidaAlmoco;
	}

	public LocalDateTime getHoraEntradaAlmoco() {
		return horaEntradaAlmoco;
	}

	public void setHoraEntradaAlmoco(LocalDateTime horaEntradaAlmoco) {
		this.horaEntradaAlmoco = horaEntradaAlmoco;
	}

	public LocalDateTime getHoraSaidaFim() {
		return horaSaidaFim;
	}

	public void setHorasaidaFim(LocalDateTime horasaidaFim) {
		this.horaSaidaFim = horasaidaFim;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = this.buildCompetencia(data);
	}
	
	private LocalDate buildCompetencia(LocalDate localDate) {
		Calendar c = Calendar.getInstance();
		Date data = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		c.setTime(data);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		LocalDate comp = c.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return comp;
	}
	
}
