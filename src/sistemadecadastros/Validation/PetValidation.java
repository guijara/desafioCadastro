package sistemadecadastros.Validation;

import sistemadecadastros.UI.ConsoleUi;

import java.time.Year;
import java.util.InputMismatchException;

public class PetValidation {
    ConsoleUi consoleUi = new ConsoleUi();
    final String naoinformado = "NÃO INFORMADO";

    public String validaNome(String pergunta) {
        String nome;
        while (true) {
            nome = consoleUi.pedir(pergunta);
            nome = nome.trim().replaceAll(" +", " ");
            if (nome.isBlank()) {
                nome = naoinformado;
                break;
            }
            try {
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
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return nome;
    }

    public String validaTipo(String pergunta){
        String tipo;
        while (true){
            try {
                tipo = consoleUi.pedir(pergunta);
                tipo = tipo.trim().toUpperCase().replaceAll(" +"," ");
                if (!tipo.equals("GATO") && !tipo.equals("CACHORRO")){
                    throw new IllegalArgumentException("O tipo deve ser obrigatoriamente Gato ou Cachorro!");
                }
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        return tipo;
    }

    public String validaSexo(String pergunta){
        String sexo;
        while (true){
            try {
                sexo = consoleUi.pedir(pergunta);
                sexo = sexo.trim().toUpperCase().replaceAll(" +"," ");
                if (!sexo.equals("MACHO") && !sexo.equals("FEMEA")){
                    throw new IllegalArgumentException("O sexo deve ser obrigatoriamente Macho ou Femea!");
                }
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        return sexo;
    }

    public String validaCidade(String pergunta){
        String cidade;
        while (true){
            try {
                cidade = consoleUi.pedir(pergunta);
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
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        return cidade;
    }

    public String validaRua(String pergunta){
        String rua;
        while (true){
            try {
                rua = consoleUi.pedir(pergunta);
                if (rua.isBlank()){
                    throw new IllegalArgumentException("O campo Rua deve ser preenchido obrigatoriamente!");
                }
                rua = rua.trim().replaceAll(" +"," ");
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        return rua;
    }

    public String validaNumeroDaCasa(String pergunta){
        String numCasa;
        while (true){
            try {
                numCasa = consoleUi.pedir(pergunta);
                if (numCasa.isBlank()){
                    numCasa = naoinformado;
                    break;
                }
                numCasa = numCasa.trim().replaceAll(" +"," ");
                for (int i = 0; i < numCasa.length(); i++){
                    if (numCasa.charAt(i) < '0' || numCasa.charAt(i) > '9'){
                        throw new IllegalArgumentException("O número da casa deve ser composto apenas de caracteres numéricos!");
                    }
                }
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        return numCasa;
    }

    public String validaIdade(String pergunta){
        String idade;
        while (true){
            try {
                idade = consoleUi.pedir(pergunta);
                if (idade.isBlank()){
                    idade = naoinformado;
                    break;
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
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch (NullPointerException e){
                System.out.println("Erro encontrado: Valor inválido!");
            }
        }
        return idade;
    }

    public String validaPeso(String pergunta){
        String peso;
        while (true){
            try {
                peso = consoleUi.pedir(pergunta);
                if (peso.isBlank()){
                    peso = naoinformado;
                    break;
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
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch (NullPointerException e){
                System.out.println("Erro encontrado: Valor inválido!");
            }
        }
        return pergunta;
    }

    public String validaRaça(String pergunta){
        String race;
        while (true){
            try {
                race = consoleUi.pedir(pergunta);
                if (race.isBlank()){
                    race = naoinformado;
                    break;
                }
                race = race.trim().replaceAll(" +"," ");
                for (int i = 0; i < race.length(); i++){
                    if ((race.charAt(i) < 'a' || race.charAt(i) > 'z') && (race.charAt(i) < 'A'
                            || race.charAt(i) > 'Z') && (race.charAt(i) != ' ')){
                        throw new IllegalArgumentException("Utilize apenas letras de A-Z e espaços!");
                    }
                }
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        return race;
    }

    public String validaDataDeCadastro(String pergunta){
        String dataDeCadastro;
        while (true){
            try {
                dataDeCadastro = consoleUi.pedir(pergunta);
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
