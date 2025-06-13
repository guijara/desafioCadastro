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
                    System.out.print(linha);
                    boolean verify = false;
                    switch (i){
                        case 0:
                            while (!verify){
                                respostas[i] = scanner.next();
                                respostas[i].trim().replaceAll(" +", " ");
                                String[] partes = respostas[i].split(" ");
                                if (partes.length < 2 || partes[0].isEmpty() || partes[1].isEmpty()){
                                    System.out.print(linha);
                                    respostas[i] = scanner.next();
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
