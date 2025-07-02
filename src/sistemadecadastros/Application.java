package sistemadecadastros;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.service.AlteraçãoService;
import sistemadecadastros.service.CadastroService;
import sistemadecadastros.service.ConsultaService;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        CadastroService cadastro = new CadastroService();
        ConsoleUi consoleUi = new ConsoleUi();
        ConsultaService consultaService = new ConsultaService();
        AlteraçãoService alteraçãoService = new AlteraçãoService();


        while (true){
            switch (consoleUi.recebeOpçãoDoMenu()) {
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
