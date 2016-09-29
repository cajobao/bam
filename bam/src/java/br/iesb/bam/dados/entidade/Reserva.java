package br.iesb.bam.dados.entidade;

import java.util.Date;

public class Reserva {
	private Integer id_reserva;
	private Integer nr_matricula;
	private Integer id_obra;
	private Date dt_validade;

	public Integer getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(Integer id_reserva) {
		this.id_reserva = id_reserva;
	}

	public Integer getNr_matricula() {
		return nr_matricula;
	}

	public void setNr_matricula(Integer nr_matricula) {
		this.nr_matricula = nr_matricula;
	}

	public Integer getId_obra() {
		return id_obra;
	}

	public void setId_obra(Integer id_obra) {
		this.id_obra = id_obra;
	}

	public Date getDt_validade() {
		return dt_validade;
	}

	public void setDt_validade(Date dt_validade) {
		this.dt_validade = dt_validade;
	}
}
