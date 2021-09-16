package lambda;

public class Utente {
	private String nome;
    private String cognome;
    private int eta;
    private String cittaResidenza;
    private String email;
    private String password;
    
	public Utente(String nome, String cognome, int eta, String cittaResidenza, String email, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.cittaResidenza = cittaResidenza;
		this.email = email;
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getCittaResidenza() {
		return cittaResidenza;
	}

	public void setCittaResidenza(String cittaResidenza) {
		this.cittaResidenza = cittaResidenza;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
