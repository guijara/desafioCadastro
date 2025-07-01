package sistemadecadastros;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.cadastro.TratamentoEntrada;
import sistemadecadastros.model.Pet;
import sistemadecadastros.service.CadastroService;
import sistemadecadastros.service.ConsultaService;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        CadastroService cadastro = new CadastroService();
        TratamentoEntrada tratamentoEntrada = new TratamentoEntrada();
        ConsoleUi consoleUi = new ConsoleUi();
        ConsultaService consultaService = new ConsultaService();

        switch (consoleUi.recebeOpçãoDoMenu()) {
            case 1:
                cadastro.criaRegistroDoPet();
            case 2:
            case 3:
            case 4:
                consultaService.consultaGeral();
            case 5:
                consultaService.consultaSimples();
        }

    }
}
