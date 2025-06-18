package cadastro;

import java.io.*;
import java.nio.channels.IllegalChannelGroupException;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class ArquivoFormulario {

    public String[] printArquivoFormulario() {
        Scanner scanner = new Scanner(System.in);
        File file = new File("formulario.txt");
        String[] respostas = new String[9];
        try {
            file.createNewFile();
            boolean exists = file.exists();
            try (FileWriter fw = new FileWriter(file)){
                fw.write("1 - Qual o nome e sobrenome do pet?\n:  " +
                        "2 - Qual o tipo do pet (Cachorro/Gato)?\n:  " +
                        "3 - Qual o sexo do animal (Macho/Femea)?\n:  " +
                        "4 - Qual endereço que ele foi encontrado - 4.1 - (Cidade)?\n:  " +
                        "4.2 - (Rua)\n:  " +
                        "4.3 - (Número da casa)\nn°:  " +
                        "5 - Qual a idade aproximada do pet (Em anos)?\n:  " +
                        "6 - Qual o peso aproximado do pet?\nKG:  " +
                        "7 - Qual a raça do pet?\n:  ");
                fw.flush();
            }catch (IOException e){
                e.printStackTrace();
            }

            try (BufferedReader bf = new BufferedReader(new FileReader(file))){
                String linha;
                int i = 0;
                while ((linha = bf.readLine()) != null && i < 9){
                    boolean verify = false;
                    final String naoinformado = "NÃO INFORMADO";
                    switch (i){
                        case 0:
                            try {
                                System.out.print(linha);
                                respostas[i] = scanner.nextLine();
                                respostas[i] = respostas[i].trim().replaceAll(" +", " ");
                                if (respostas[i] == ""){
                                    respostas[i] = naoinformado;
                                    continue;
                                }
                                for (int j = 0; j < respostas[0].length(); j++){
                                    if ((respostas[i].charAt(j) < 'a' || respostas[i].charAt(j) > 'z') && (respostas[i].charAt(j) < 'A'
                                            || respostas[i].charAt(j) > 'Z') && (respostas[i].charAt(j) != ' ')){
                                        throw new IllegalArgumentException("Utilize apenas letras de A-Z e espaços!");
                                    }
                                }
                                String[] partes = respostas[i].toLowerCase().split(" ");
                                if (partes.length < 2 || partes[0].isEmpty() || partes[1].isEmpty()){
                                    throw new IllegalArgumentException("O nome completo deve possuir Nome e Sobrenome");
                                }
                                if (verify && respostas[i] != naoinformado){
                                    StringBuilder sb = new StringBuilder();
                                    for (String parte : partes){
                                        if (!parte.isEmpty()){
                                            sb.append(Character.toUpperCase(parte.charAt(0))).append(parte.substring(1, parte.length())).append(" ");
                                        }
                                    }
                                    respostas[i] = sb.toString().trim();
                                }
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        case 1:
                            try {
                                System.out.print(linha);
                                respostas[i] = scanner.nextLine();
                                respostas[i] = respostas[i].trim().toUpperCase().replaceAll(" +"," ");
                                if (respostas[i] != "GATO" && respostas[i] != "CACHORRO"){
                                    throw new IllegalArgumentException("O tipo deve ser obrigatoriamente Gato ou Cachorro!");
                                }
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        case 2:
                            try {
                                System.out.println(linha);
                                respostas[i] = scanner.nextLine();
                                respostas[i] = respostas[i].trim().toUpperCase().replaceAll(" +"," ");
                                if (respostas[i] != "MACHO" && respostas[i] != "FEMEA"){
                                    throw new IllegalArgumentException("O sexo deve ser obrigatoriamente Macho ou Femea!");
                                }
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        case 3:
                            try {
                                System.out.println();
                                respostas[i] = scanner.nextLine();
                                if (respostas[i] == ""){
                                    throw new IllegalArgumentException("O campo Cidade deve ser preenchido obrigatoriamente!");
                                }
                                respostas[i] = respostas[i].trim().replaceAll(" +"," ");
                                for (int j = 0; j < respostas[i].length(); j++){
                                    if ((respostas[i].charAt(j) < 'a' || respostas[i].charAt(j) > 'z') && (respostas[i].charAt(j) < 'A' ||
                                            respostas[i].charAt(j) > 'Z') && (respostas[i].charAt(j) != ' ')){
                                        throw new IllegalArgumentException("A cidade deve conter apenas letras de A-Z e espaços!");
                                    }
                                }
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        case 4:
                            try {
                                System.out.println();
                                respostas[i] = scanner.nextLine();
                                if (respostas[i] == ""){
                                    throw new IllegalArgumentException("O campo Rua deve ser preenchido obrigatoriamente!");
                                }
                                respostas[i] = respostas[i].trim().replaceAll(" +"," ");
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        case 5:
                            try {
                                System.out.println();
                                respostas[i] = scanner.nextLine();
                                if (respostas[i] == ""){
                                    respostas[i] = naoinformado;
                                    continue;
                                }
                                respostas[i] = respostas[i].trim().replaceAll(" +"," ");
                                for (int j = 0; j < respostas[i].length(); j++){
                                    if (respostas[i].charAt(j) < '0' || respostas[i].charAt(j) > '9'){
                                        throw new IllegalArgumentException("O número da casa deve ser composto apenas de caracteres numéricos!");
                                    }
                                }
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        case 6:
                            try {
                                System.out.println();
                                respostas[i] = scanner.nextLine();
                                if (respostas[i] == ""){
                                    respostas[i] = naoinformado;
                                    continue;
                                }
                                respostas[i] = respostas[i].trim().replaceAll(" ","").replaceAll(",",".");
                                for (int j = 0; j < respostas[i].length(); j++){
                                    if ((respostas[i].charAt(j) < '0' || respostas[i].charAt(j) > '9') && (respostas[i].charAt(j) != '.'
                                            && respostas[i].charAt(j) != ' ')){
                                        throw new IllegalArgumentException("A idade deve ser composto apenas de caracteres numéricos e ponto!");
                                    }
                                }
                                double idade = Double.parseDouble(respostas[i]);
                                if (idade > 20){
                                    throw new IllegalArgumentException("A idade não pode ser maior que 20 anos!");
                                }else if (idade < 1){
                                    idade = idade/12;
                                }
                                respostas[i] = String.valueOf(idade);
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        case 7:
                            try {
                                System.out.println();
                                respostas[i] = scanner.nextLine();
                                if (respostas[i] == ""){
                                    respostas[i] = naoinformado;
                                    continue;
                                }
                                respostas[i] = respostas[i].trim().replaceAll(" ","").replaceAll(",",".");
                                for (int j = 0; j < respostas[i].length(); j++){
                                    if ((respostas[i].charAt(j) < '0' || respostas[i].charAt(j) > '9') && (respostas[i].charAt(j) != '.'
                                            && respostas[i].charAt(j) != ' ')){
                                        throw new IllegalArgumentException("O peso deve ser composto apenas de caracteres numéricos e ponto!");
                                    }
                                }
                                double peso = Double.parseDouble(respostas[i]);
                                if (peso > 60 || peso < 0.5){
                                    throw new IllegalArgumentException("O peso não pode ser maior que 60KG ou menor que 0.5KG");
                                }
                                respostas[i] = String.valueOf(peso);
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        case 8:
                            try {
                                System.out.println();
                                respostas[i] = scanner.nextLine();
                                if (respostas[i] == ""){
                                    respostas[i] = naoinformado;
                                    continue;
                                }
                                respostas[i] = respostas[i].trim().replaceAll(" +"," ");
                                for (int j = 0; j < respostas[0].length(); j++){
                                    if ((respostas[i].charAt(j) < 'a' || respostas[i].charAt(j) > 'z') && (respostas[i].charAt(j) < 'A'
                                            || respostas[i].charAt(j) > 'Z') && (respostas[i].charAt(j) != ' ')){
                                        throw new IllegalArgumentException("Utilize apenas letras de A-Z e espaços!");
                                    }
                                }
                            }catch (IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            if (exists){
                file.delete();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return respostas;
    }
}
