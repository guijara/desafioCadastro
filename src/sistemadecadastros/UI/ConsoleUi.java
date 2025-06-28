package sistemadecadastros.UI;

import sistemadecadastros.Validation.PetValidation;
import sistemadecadastros.model.CriteriosDeBusca;
import sistemadecadastros.model.Sexo;
import sistemadecadastros.model.Tipo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUi {
    Scanner scanner = new Scanner(System.in);
    PetValidation petValidation = new PetValidation();

    public String pedir(String pergunta){
        System.out.print(pergunta);
        String resposta = scanner.nextLine();
        return resposta;
    }

    public void printar(String mensagem){
        System.out.println(mensagem);
    }


    public CriteriosDeBusca retornaCriteriosAvançados(){
        int i = retornaQuantidadeDeCriteriosOpcionais();
        if (i == 1){
            criteriosDeBusca = retornaCriterioSimples(criteriosDeBusca);
        }else if (i == 2){
            criteriosDeBusca = retornaCriterioSimples(criteriosDeBusca);

        }
        CriteriosDeBusca criterios = new CriteriosDeBusca();
        criterios =
        for (int j = 0; j < i; j++){
            criterios = retornaCriterioSimples(criterios);
        }
        return criterios;
    }

    public void mostraResultadoDaBusca(){

    }

    public int recebeOpçãoDoMenu(){
        int opção;
        while (true){
            try {
                System.out.println("1. Cadastrar um novo pet\n" +
                        "2. Alterar os dados do pet cadastrado\n" +
                        "3. Deletar um pet cadastrado\n" +
                        "4. Listar todos os pets cadastrados\n" +
                        "5. Listar pets por algum critério (idade, nome, raça)\n" +
                        "6. Sair");
                System.out.print(":  ");
                String opçãoAux = scanner.nextLine();
                opção = Integer.parseInt(opçãoAux);
                if (opção < 1 || opção > 6) {
                    throw new IllegalArgumentException("Erro encontrado: Digite uma opção válida!");
                }
                break;
            }catch (NumberFormatException e) {
                System.out.println("Erro encontrado: Digite apenas números, sem espaços!");
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Erro encontrado: Digite apenas números!");
            }
        }
        return opção;
    }
}
