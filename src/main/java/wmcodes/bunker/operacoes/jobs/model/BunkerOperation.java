package wmcodes.bunker.operacoes.jobs.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BunkerOperation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="data_operacao")
	private Date dataOperacao;
	
	private String navio;
	
	private String cfn;
	
	@Column(name="fornecido_por")
	private String fornecidoPor;
	
	private String fornecedor;
	
	private BigDecimal recebido;
	
	private BigDecimal fornecido;
	
	private BigDecimal diferenca;
	
	private Double porcentagem;
	
	private String localidade;
	
	private String cliente;
	
	private Timestamp inicio;
	
	private Timestamp fim;
		
}
