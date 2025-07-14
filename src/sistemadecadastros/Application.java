package sistemadecadastros;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.Validation.PetValidation;
import sistemadecadastros.repository.PetRepository;
import sistemadecadastros.service.AlteraçãoService;
import sistemadecadastros.service.CadastroService;
import sistemadecadastros.service.ConsultaService;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ConsoleUi consoleUi = new ConsoleUi();
        PetValidation petValidation = new PetValidation(consoleUi);
        PetRepository petRepository = new PetRepository();

        CadastroService cadastro = new CadastroService(consoleUi,petValidation,petRepository);
        ConsultaService consultaService = new ConsultaService(consoleUi,petValidation,petRepository,cadastro);
        AlteraçãoService alteraçãoService = new AlteraçãoService(consoleUi,petValidation,petRepository,consultaService,cadastro);



        while (true){
            int opcao = consoleUi.recebeOpçãoDoMenu();
            switch (opcao) {
                case 1:
                    cadastro.criaRegistroDoPet();
                    break;
                case 2:
                    alteraçãoService.alteraPet();
                    break;
                case 3:
                    alteraçãoService.removePet();
                    break;
                case 4:
                    consultaService.consultaGeral();
                    break;
                case 5:
                    consultaService.consultaSimples();
                    break;
                case 6:
                    consoleUi.printar("Finalizando programa...");
                    return;
            }
        }

    }
}
