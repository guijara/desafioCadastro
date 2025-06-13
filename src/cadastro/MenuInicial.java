package cadastro;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInicial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArquivoFormulario arquivoFormulario = new ArquivoFormulario();
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
        switch (option){
            case 1:
                arquivoFormulario.printArquivoFormulario();
        }

    }
}
