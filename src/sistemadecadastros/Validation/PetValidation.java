package sistemadecadastros.Validation;

import sistemadecadastros.UI.ConsoleUi;

import java.time.Year;

public class PetValidation {

    final String naoinformado = "NÃO INFORMADO";
    final private ConsoleUi consoleUi;

    public PetValidation(ConsoleUi consoleUi){
        this.consoleUi = consoleUi;
    }

    public String validaNome(String nome) {
        nome = nome.trim().replaceAll(" +", " ");
        if (nome.isBlank()) {
            nome = naoinformado;
            return nome;
        }
        for (int i = 0; i < nome.length(); i++) {
            if ((nome.charAt(i) < 'a' || nome.charAt(i) > 'z') && (nome.charAt(i) < 'A'
                    || nome.charAt(i) > 'Z') && (nome.charAt(i) != ' ')) {
                throw new IllegalArgumentException("Utilize apenas letras de A-Z e espaços!");
            }
        }
        String[] partes = nome.toLowerCase().split(" ");
        if (partes.length < 2 || partes[0].isEmpty() || partes[1].isEmpty()) {
            throw new IllegalArgumentException("O nome completo deve possuir Nome e Sobrenome");
        }
        if (!nome.equals(naoinformado)) {
            StringBuilder sb = new StringBuilder();
            for (String parte : partes) {
                if (!parte.isEmpty()) {
                    sb.append(Character.toUpperCase(parte.charAt(0))).append(parte.substring(1, parte.length())).append(" ");
                }
            }
            nome = sb.toString().trim();
        }
        return nome;
    }

    public String validaTipo(String tipo){
        tipo = tipo.trim().toUpperCase().replaceAll(" +"," ");
        if (!tipo.equals("GATO") && !tipo.equals("CACHORRO")){
            throw new IllegalArgumentException("O tipo deve ser obrigatoriamente Gato ou Cachorro!");
        }
        return tipo;
    }

    public String validaSexo(String sexo){
        sexo = sexo.trim().toUpperCase().replaceAll(" +"," ");
        if (!sexo.equals("MACHO") && !sexo.equals("FEMEA")){
            throw new IllegalArgumentException("O sexo deve ser obrigatoriamente Macho ou Femea!");
        }
        return sexo;
    }

    public String validaCidade(String cidade){
        if (cidade.isBlank()){
            throw new IllegalArgumentException("O campo Cidade deve ser preenchido obrigatoriamente!");
        }
        cidade = cidade.trim().replaceAll(" +"," ");
        for (int i = 0; i < cidade.length(); i++) {
            if ((cidade.charAt(i) < 'a' || cidade.charAt(i) > 'z') && (cidade.charAt(i) < 'A' ||
                    cidade.charAt(i) > 'Z') && (cidade.charAt(i) != ' ')) {
                throw new IllegalArgumentException("A cidade deve conter apenas letras de A-Z e espaços!");
            }
        }
        return cidade;
    }

    public String validaRua(String rua){
        if (rua.isBlank()){
            throw new IllegalArgumentException("O campo Rua deve ser preenchido obrigatoriamente!");
        }
        rua = rua.trim().replaceAll(" +"," ");
        return rua;
    }

    public String validaNumeroDaCasa(String numCasa){
        if (numCasa.isBlank()){
            numCasa = naoinformado;
            return numCasa;
        }
        numCasa = numCasa.trim().replaceAll(" +"," ");
        for (int i = 0; i < numCasa.length(); i++){
            if (numCasa.charAt(i) < '0' || numCasa.charAt(i) > '9'){
                throw new IllegalArgumentException("O número da casa deve ser composto apenas de caracteres numéricos!");
            }
        }
        return numCasa;
    }

    public String validaIdade(String idade){
        if (idade.isBlank()){
            idade = naoinformado;
            return idade;
        }
        idade = idade.trim().replaceAll(" ","").replaceAll(",",".");
        for (int i = 0; i < idade.length(); i++){
            if ((idade.charAt(i) < '0' || idade.charAt(i) > '9') && (idade.charAt(i) != '.'
                    && idade.charAt(i) != ' ')){
                throw new IllegalArgumentException("A idade deve ser composto apenas de caracteres numéricos e ponto!");
            }
        }
        double idadeAux = Double.parseDouble(idade);
        if (idadeAux > 20){
            throw new IllegalArgumentException("A idade não pode ser maior que 20 anos!");
        }else if (idadeAux < 1){
            idadeAux = idadeAux/12;
        }
        idade = String.valueOf(idadeAux);
        return idade;
    }

    public String validaPeso(String peso){
        if (peso.isBlank()){
            peso = naoinformado;
            return peso;
        }
        peso = peso.trim().replaceAll(" ","").replaceAll(",",".");
        for (int i = 0; i < peso.length(); i++){
            if ((peso.charAt(i) < '0' || peso.charAt(i) > '9') && (peso.charAt(i) != '.'
                    && peso.charAt(i) != ' ')){
                throw new IllegalArgumentException("A idade deve ser composto apenas de caracteres numéricos e ponto!");
            }
        }
        double pesoAux = Double.parseDouble(peso);
        if (pesoAux > 60 || pesoAux < 0.5){
            throw new IllegalArgumentException("O peso não pode ser maior que 60KG ou menor que 0.5KG");
        }
        peso = String.valueOf(pesoAux);
        return peso;
    }

    public String validaRaça(String race){
        if (race.isBlank()){
            race = naoinformado;
            return race;
        }
        race = race.trim().replaceAll(" +"," ");
        for (int i = 0; i < race.length(); i++){
            if ((race.charAt(i) < 'a' || race.charAt(i) > 'z') && (race.charAt(i) < 'A'
                    || race.charAt(i) > 'Z') && (race.charAt(i) != ' ')){
                throw new IllegalArgumentException("Utilize apenas letras de A-Z e espaços!");
            }
        }
        return race;
    }

    public String validaDataDeCadastro(String dataDeCadastro){
        while (true){
            try {
                if (dataDeCadastro.isBlank()){
                    throw new IllegalArgumentException("Digite um ano!");
                }
                dataDeCadastro.trim().replaceAll(" +"," ");
                int verify = Integer.parseInt(dataDeCadastro);
                if (verify < 1950 || verify > Year.now().getValue()){
                    throw new IllegalArgumentException("Insira um ano válido!");
                }

                String mes = consoleUi.pedir("Digite o número correspondente ao mês:  ");
                verify = Integer.parseInt(mes.trim().replaceAll(" +"," "));
                if (verify < 1 || verify > 12){
                    throw new IllegalArgumentException("Insira um mês válido (de 1 à 12)!");
                }
                mes = String.format("%02d", verify);
                dataDeCadastro += mes;
                break;
            }catch (NumberFormatException e){
                System.out.println("Erro encontrado: Digite apenas números!");
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return dataDeCadastro;
    }

}
