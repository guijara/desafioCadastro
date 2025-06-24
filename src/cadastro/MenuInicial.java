package cadastro;

import pet.Pet;

import java.io.*;
import java.nio.channels.IllegalChannelGroupException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInicial {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ArquivoFormulario arquivoFormulario = new ArquivoFormulario();
        TratamentoEntrada tratamentoEntrada = new TratamentoEntrada();
        int option = 0;
        System.out.println("1. Cadastrar um novo pet\n" +
                "2. Alterar os dados do pet cadastrado\n" +
                "3. Deletar um pet cadastrado\n" +
                "4. Listar todos os pets cadastrados\n" +
                "5. Listar pets por algum critério (idade, nome, raça)\n" +
                "6. Sair");
        try {
            int optionaux = scanner.nextInt();
            while (optionaux < 1 || optionaux > 6) {
                System.out.println("1. Cadastrar um novo pet\n" +
                        "2. Alterar os dados do pet cadastrado\n" +
                        "3. Deletar um pet cadastrado\n" +
                        "4. Listar todos os pets cadastrados\n" +
                        "5. Listar pets por algum critério (idade, nome, raça)\n" +
                        "6. Sair");
                optionaux = scanner.nextInt();
            }
            option = optionaux;
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        switch (option) {
            case 1:
                String[] ans = arquivoFormulario.printArquivoFormulario();
                Pet pet = new Pet(ans[0], ans[1], ans[2], ans[4], Integer.parseInt(ans[5]), ans[3], Double.parseDouble(ans[6]), Double.parseDouble(ans[7]), ans[8]);
                pet.criaArquivo();
            case 2:
                System.out.println("Escolha o numero do Pet que deseja alterar:");
                String[] listaDePets = tratamentoEntrada.listaPets();
                for (int i = 0;i < listaDePets.length;i++){
                    System.out.println(listaDePets[i]);
                }
                System.out.println(":  ");
                int opcao = 0;
                try {
                    int i = listaDePets.length;
                    opcao = scanner.nextInt();
                    while (opcao < 1 || opcao > i){
                        System.out.println("Escolha o numero do Pet que deseja alterar:");
                        for (int j = 0;j < listaDePets.length;j++){
                            System.out.println(listaDePets[j]);
                        }
                        System.out.println(":  ");
                    }
                }catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                }
                try (BufferedReader bf = new BufferedReader(new FileReader(listaDePets[opcao-1]))){
                    int contador = 0;
                    String linha;
                    System.out.println("Qual dado deseja alterar?");

                    while ((linha = bf.readLine()) != null){
                        if (contador == 1 || contador == 2){
                            continue;
                        }
                        System.out.println(linha);
                        contador++;
                    }

                    try {
                        opcao = scanner.nextInt();
                        while (opcao < 1 || opcao > 7){
                            for (int j = 0;j < listaDePets.length;j++){
                                System.out.println(listaDePets[j]);
                            }
                            System.out.println(":  ");
                        }
                    }catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                    }
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
        }

    }
}
