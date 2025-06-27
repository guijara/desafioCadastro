package sistemadecadastros.cadastro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.Scanner;

public class TratamentoEntrada {
    Scanner scanner = new Scanner(System.in);

    public String[] listaPets(){ // Mostra todos os pets dos arquivos selecionados na formatação correta
        int i = calculaQuantidadeDeRegistros();
        String[] resultadosBusca = new String[i];
        try {
            int contaLinha = 1;
            for (File file : trataBusca()){
                try (BufferedReader bf = new BufferedReader(new FileReader(file))){
                    resultadosBusca[contaLinha-1] = String.valueOf(contaLinha)+".  ";
                    if (file.length() == 0){
                        resultadosBusca[contaLinha-1] = "";
                        break;
                    }
                    String linha;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((linha = bf.readLine()) != null){
                        String[] tipo = linha.split(" ");
                        if (tipo.length > 4){
                            stringBuilder = stringBuilder.append(" - ").append(tipo[2]).append(" - ").append(tipo[3]).append(" - ").append(tipo[4]);
                        }else if (tipo.length > 3){
                            stringBuilder = stringBuilder.append(" - ").append(tipo[2]).append(" - ").append(tipo[3]);
                        }else {
                            stringBuilder = stringBuilder.append(" - ").append(tipo[2]);
                        }
                    }
                    resultadosBusca[contaLinha-1] += stringBuilder.substring(3,stringBuilder.length());
                    contaLinha++;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return resultadosBusca;
    }

    public File[] trataBusca(){ // seleciona os arquivos da pasta conforme os critérios selecioandos
        TratamentoEntrada te = new TratamentoEntrada();
        String[] input = te.trataEntrada();

        int i = calculaQuantidadeDeRegistros();
            File[] files = new File[i];
            i = 2;
            if (!input[2].isEmpty()) {
                i++;
            }
            try {
                for (File file1 : retornaPastaDeRegistros().listFiles()) {
                    try (BufferedReader bf = new BufferedReader(new FileReader(file1))) {
                        String linha;
                        boolean verify = true;
                        int j = 0;
                        while (j < i && verify) {
                            verify = false;
                            int contador = 1;
                            while ((linha = bf.readLine()) != null) {
                                String[] tipoDaLinha = linha.split(" ");
                                if (contador == 4) {
                                    String[] comparaEndereco = input[j].split(" ");
                                    for (int k = 2; k < 5;k++){
                                        for (int l = 0;l < 3;l++){
                                            if (!comparaEndereco[j].isEmpty() && tipoDaLinha[k].equals(comparaEndereco[j])){
                                                verify = true;
                                            }
                                        }
                                    }
                                }else if (contador == 5 || contador == 6){
                                    float numAux1 = Float.parseFloat(tipoDaLinha[2]);
                                    float numAux2 = Float.parseFloat(input[j]);
                                    if (numAux1 == numAux2){
                                        verify = true;
                                    }
                                } else if (tipoDaLinha[2].toLowerCase().contains(input[j].toLowerCase())) {
                                    verify = true;
                                }
                                contador++;
                            }
                            j++;
                        }
                        if (verify) {
                            int k = 0;
                            while (files[k].length() != 0) {
                                k++;
                            }
                            files[k] = file1;
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            return files;
    }


    public String[] trataEntrada() { // Recebe 2 ou 3 filtros selecionados para listagem
            for (int i = 1; i < count; i++) {

                switch (j) {
                    case 1:
                        try {
                            System.out.println("Digite um nome e/ou um sobrenome");
                            System.out.print(":  ");
                            filtro[i] = scanner.nextLine();
                            filtro[i] = filtro[i].trim().replaceAll(" +", " ");
                            if (filtro[i] == "") {
                                throw new IllegalArgumentException("O nome ou sobrenome deve ser preenchida obrigatoriamente!");
                            }
                            for (int k = 0; k < filtro[i].length(); k++) {
                                if ((filtro[i].charAt(k) < 'a' || filtro[i].charAt(k) > 'z') && (filtro[i].charAt(k) < 'A'
                                        || filtro[i].charAt(k) > 'Z') && (filtro[i].charAt(k) != ' ')) {
                                    throw new IllegalArgumentException("Utilize apenas letras de A-Z e espaços!");
                                }
                            }
                            String[] partes = filtro[i].toLowerCase().split(" ");
                            StringBuilder sb = new StringBuilder();
                            for (String parte : partes) {
                                if (!parte.isEmpty()) {
                                    sb.append(Character.toUpperCase(parte.charAt(0))).append(parte.substring(1, parte.length())).append(" ");
                                }
                            }
                            filtro[i] = sb.toString().trim();
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("Digite o sexo do Pet");
                            System.out.print(":  ");
                            filtro[i] = scanner.nextLine();
                            filtro[i] = filtro[i].trim().toUpperCase().replaceAll(" +", " ");
                            if (filtro[i] != "MACHO" && filtro[i] != "FEMEA") {
                                throw new IllegalArgumentException("O sexo deve ser obrigatoriamente Macho ou Femea!");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        for (int k = 0; k < 4; k++) {
                            switch (k) {
                                case 0:
                                    System.out.println("Digite o nome da Rua");
                                    System.out.print(":  ");
                                    filtro[i] = scanner.nextLine();
                                    if (filtro[i] == "") {
                                        continue;
                                    }
                                    filtro[i] = filtro[i].trim().replaceAll(" +", " ").toLowerCase();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(Character.toUpperCase(filtro[i].charAt(0))).append(filtro[i].substring(1, filtro.length));
                                    filtro[i] = sb.toString();
                                    break;
                                case 1:
                                    try {
                                        System.out.println("Digite o número da casa");
                                        System.out.print(":  ");
                                        String aux = scanner.nextLine();
                                        if (aux == "") {
                                            continue;
                                        }
                                        aux = aux.trim().replaceAll(" +", " ");
                                        for (int l = 0; l < aux.length(); l++) {
                                            if (aux.charAt(l) < '0' || aux.charAt(l) > '9') {
                                                throw new IllegalArgumentException("O número da casa deve ser composto apenas de caracteres numéricos!");
                                            }
                                        }
                                        StringBuilder sc = new StringBuilder();
                                        filtro[i] = sc.append(filtro[i]).append(", ").append(aux).toString();
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        System.out.println("Digite o nome da Cidade");
                                        System.out.print(":  ");
                                        String aux = scanner.nextLine();
                                        if (aux == "") {
                                            continue;
                                        }
                                        aux = aux.trim().replaceAll(" +", " ").toLowerCase();
                                        for (int l = 0; l < aux.length(); l++) {
                                            if ((aux.charAt(l) < 'a' || aux.charAt(l) > 'z') && (aux.charAt(l) < 'A' ||
                                                    aux.charAt(l) > 'Z') && (aux.charAt(l) != ' ')) {
                                                throw new IllegalArgumentException("A cidade deve conter apenas letras de A-Z e espaços!");
                                            }
                                        }
                                        StringBuilder sc = new StringBuilder();
                                        filtro[i] = sc.append(filtro[i]).append(", ").append(Character.toUpperCase(aux.charAt(0))).append(aux.substring(1, aux.length())).toString();
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 3:
                                    try {
                                        if (filtro[i].isEmpty()) {
                                            throw new IllegalArgumentException("Ao menos algum campo do endereço deve ser preenchido!");
                                        }
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }

                            }
                        }
                    case 4:
                        try {
                            System.out.println("Digite a idade do Pet");
                            System.out.print(":  ");
                            filtro[i] = scanner.nextLine();
                            if (filtro[i] == "") {
                                throw new IllegalArgumentException("O campo idade não pode estar vazio!");
                            }
                            filtro[i] = filtro[i].trim().replaceAll(" ", "").replaceAll(",", ".");
                            for (int k = 0; k < filtro[i].length(); k++) {
                                if ((filtro[i].charAt(k) < '0' || filtro[i].charAt(k) > '9') && (filtro[i].charAt(k) != '.'
                                        && filtro[i].charAt(k) != ' ')) {
                                    throw new IllegalArgumentException("A idade deve ser composto apenas de caracteres numéricos e ponto!");
                                }
                            }
                            double idade = Double.parseDouble(filtro[i]);
                            if (idade > 20) {
                                throw new IllegalArgumentException("A idade não pode ser maior que 20 anos!");
                            } else if (idade < 1) {
                                idade = idade / 12;
                            }
                            filtro[i] = String.valueOf(idade);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("Digite o peso do Pet");
                            System.out.print(":  ");
                            filtro[i] = scanner.nextLine();
                            if (filtro[i] == "") {
                                throw new IllegalArgumentException("O peso deve ser preenchido obrigatoriamente!");
                            }
                            filtro[i] = filtro[i].trim().replaceAll(" ", "").replaceAll(",", ".");
                            for (int k = 0; k < filtro[i].length(); k++) {
                                if ((filtro[i].charAt(k) < '0' || filtro[i].charAt(k) > '9') && (filtro[i].charAt(k) != '.'
                                        && filtro[i].charAt(k) != ' ')) {
                                    throw new IllegalArgumentException("O peso deve ser composto apenas de caracteres numéricos e ponto!");
                                }
                            }
                            double peso = Double.parseDouble(filtro[i]);
                            if (peso > 60 || peso < 0.5) {
                                throw new IllegalArgumentException("O peso não pode ser maior que 60KG ou menor que 0.5KG");
                            }
                            filtro[i] = String.valueOf(peso);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        try {
                            System.out.println("Digite a raça do Pet");
                            System.out.print(":  ");
                            filtro[i] = scanner.nextLine();
                            if (filtro[i] == "") {
                                throw new IllegalArgumentException("A raça deve ser preenchida obrigatoriamente!");
                            }
                            filtro[i] = filtro[i].trim().replaceAll(" +", " ");
                            for (int k = 0; k < filtro[i].length(); k++) {
                                if ((filtro[i].charAt(k) < 'a' || filtro[i].charAt(k) > 'z') && (filtro[i].charAt(k) < 'A'
                                        || filtro[i].charAt(k) > 'Z') && (filtro[i].charAt(k) != ' ')) {
                                    throw new IllegalArgumentException("Utilize apenas letras de A-Z e espaços!");
                                }
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return filtro;
    }
}
