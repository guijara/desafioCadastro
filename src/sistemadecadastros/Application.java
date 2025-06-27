package sistemadecadastros;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.cadastro.TratamentoEntrada;
import sistemadecadastros.model.Pet;
import sistemadecadastros.service.CadastroService;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        CadastroService cadastro = new CadastroService();
        TratamentoEntrada tratamentoEntrada = new TratamentoEntrada();
        ConsoleUi consoleUi = new ConsoleUi();

        switch (consoleUi.recebeOpçãoDoMenu()) {
            case 1:
                cadastro.criaRegistroDoPet();
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
