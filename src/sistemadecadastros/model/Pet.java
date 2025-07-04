package sistemadecadastros.model;

public class Pet {
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private String rua;
    private int num_casa;
    private String cidade;
    private double idade;
    private double peso;
    private String race;
    private String data_de_cadastro;


    public Pet(String nome,String tipo,String sexo,String rua,int num_casa,String cidade,double idade,double peso,String race){
        this.nome = nome;
        this.tipo = Tipo.valueOf(tipo);
        this.sexo = Sexo.valueOf(sexo);
        this.rua = rua;
        this.num_casa = num_casa;
        this.cidade = cidade;
        this.idade = idade;
        this.peso = peso;
        this.race = race;
        this.data_de_cadastro = data_de_cadastro;
    }

    public Pet(String nome,String tipo,String sexo,String rua,int num_casa,String cidade,double idade,double peso,String race,String data_de_cadastro){
        this.nome = nome;
        this.tipo = Tipo.valueOf(tipo);
        this.sexo = Sexo.valueOf(sexo);
        this.rua = rua;
        this.num_casa = num_casa;
        this.cidade = cidade;
        this.idade = idade;
        this.peso = peso;
        this.race = race;
        this.data_de_cadastro = data_de_cadastro;
    }

    public Pet(Pet petCopia){
        this.nome = petCopia.getNome();
        this.tipo = petCopia.getTipo();
        this.sexo = petCopia.getSexo();
        this.rua = petCopia.getRua();
        this.num_casa = petCopia.getNum_casa();
        this.cidade = petCopia.getCidade();
        this.idade = petCopia.getIdade();
        this.peso = petCopia.getPeso();
        this.race = petCopia.getRace();
        this.data_de_cadastro = petCopia.getData_de_cadastro();
    }

    public String getData_de_cadastro() {
        return data_de_cadastro.substring(0,6);
    }

    public String getData_de_cadastro2(){
        return data_de_cadastro;
    }

    public void setData_de_cadastro(String data_de_cadastro) {
        this.data_de_cadastro = data_de_cadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNum_casa() {
        return num_casa;
    }

    public void setNum_casa(int num_casa) {
        this.num_casa = num_casa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
