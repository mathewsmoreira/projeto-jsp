package model;

import java.io.Serializable;
import java.sql.Date;

public class ModelLoginServlet implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String login;
	private String email;
	private String senha;
	private boolean usuarioadmin;
	private String sexo;
	private String fotoUser;
	private String fotoExtensao;
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private int numero;
	private String complemento;
	private Date datanascimento;
	private double rendamensal;
	
	
	
	
	
	
	
	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public double getRendamensal() {
		return rendamensal;
	}

	public void setRendamensal(double rendamensal) {
		this.rendamensal = rendamensal;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getFotoUser() {
		return fotoUser;
	}

	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	public String getFotoExtensao() {
		return fotoExtensao;
	}

	public void setFotoExtensao(String fotoExtensao) {
		this.fotoExtensao = fotoExtensao;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isUsuarioadmin() {
		return usuarioadmin;
	}

	public void setUsuarioadmin(boolean usuarioadmin) {
		this.usuarioadmin = usuarioadmin;
	}

	public boolean isNew() {
		if(this.id ==null) {
			return true;
			
		}else if(this.id != null && this.id > 0){
			return false;
		}else {
			return false;
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	

}
