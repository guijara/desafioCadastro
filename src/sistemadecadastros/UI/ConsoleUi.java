package sistemadecadastros.UI;

import java.util.Scanner;

public class ConsoleUi {
    Scanner scanner = new Scanner(System.in);

    public String pedir(String pergunta){
        System.out.println(pergunta);
        String resposta = scanner.nextLine();
        return resposta;
    }

    public void printar(String mensagem){
        System.out.println(mensagem);
    }
}
