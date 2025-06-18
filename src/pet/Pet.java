package pet;

public class Pet {
    String nome;
    Tipo tipo;
    Sexo sexo;
    String[] endereco = new String[3];
    double idade;
    double peso;
    String race;

    public Pet(String nome,String tipo,String sexo,String[] endereco,double idade,double peso,String race){
        this.nome = nome;
        this.tipo = Tipo.valueOf(tipo);
        this.sexo = Sexo.valueOf(sexo);
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.race = race;
    }
}
