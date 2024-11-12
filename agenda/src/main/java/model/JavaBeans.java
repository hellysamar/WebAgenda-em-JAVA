package model;

public class JavaBeans {
	private String id;
	private String nome;
	private String fone;
	private String email;
	private String aniversario;
		
	public JavaBeans() {
		super();
	}
	
	public JavaBeans(String id, String nome, String fone, String email, String aniversario) {
		super();
		this.id = id;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
		this.aniversario = aniversario;
	}

//	public <JavaBeans> lista = new <javaBeans>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAniversario() {		
		return aniversario;
	}
	public void setAniversario(String aniversario) {

		aniversario.replaceAll("/", "");
		String day = aniversario.substring(0, 2);
		String month = aniversario.substring(2, 4);
		String year = aniversario.substring(4, 8);
		String data = year + month + day;
		
		this.aniversario = data;
	}
}
