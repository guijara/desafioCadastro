package cadastro;

import java.io.*;
import java.util.Scanner;

public class ArquivoFormulario {

    public void printArquivoFormulario() {
        Scanner scanner = new Scanner(System.in);
        File file = new File("formulario.txt");
        try {
            file.createNewFile();
            boolean exists = file.exists();
            try (FileWriter fw = new FileWriter(file)){
                fw.write("1 - Qual o nome e sobrenome do pet?\n" +
                        "2 - Qual o tipo do pet (Cachorro/Gato)?\n" +
                        "3 - Qual o sexo do animal (Macho/Femea)?\n" +
                        "4 - Qual endereço e bairro que ele foi encontrado (Número da casa)?\n" +
                        "(Cidade)\n" +
                        "(Rua)\n" +
                        "5 - Qual a idade aproximada do pet?\n" +
                        "6 - Qual o peso aproximado do pet?\n" +
                        "7 - Qual a raça do pet?");
                fw.flush();
            }catch (IOException e){
                e.printStackTrace();
            }

            try (BufferedReader bf = new BufferedReader(new FileReader(file))){
                String[] respostas = new String[9];
                String linha;
                int i = 0;
                while ((linha = bf.readLine()) != null && i < 9){
                    boolean verify = false;
                    final String naoinformado = "NÃO INFORMADO";
                    switch (i){
                        case 0:
                            while (!verify){
                                verify = true;
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
                                        verify = false;
                                    }
                                }
                                String[] partes = respostas[i].toLowerCase().split(" ");
                                if (partes.length < 2 || partes[0].isEmpty() || partes[1].isEmpty()){
                                    verify = false;
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
    }
}
