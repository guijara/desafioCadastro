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

    public int retornaQuantidadeDeCriterios(){
        int i;
        while (true){
            try {
                i = Integer.parseInt(pedir("Você deve selecionar alguma(s) outras opções para filtrar seu Pet, deseja selecionar quantas opções?\n" + "1 - Uma opção de filtro\n" + "2 - Duas opções de filtro\n" + ":  "))
                if (i < 1 || i > 2) {
                    throw new IllegalArgumentException("Digite uma opção válida!");
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Erro encontrado: Digite apenas números!");
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        return i;
    }


    public CriteriosDeBusca retornaPrimeiroCriterioObrigatorio(CriteriosDeBusca criteriosDeBusca){
        while (true){
            try {
                int i = Integer.parseInt(pedir("Escolha um filtro primário para encontrar seu pet!\n" + "1 - Tipo do Pet (Cachorro ou Gato)\n" + "2 - Data de cadastro do Pet\n" + ":  "));

                if (i < 1 || i > 2) {
                    throw new IllegalArgumentException("Digite uma opção válida!");
                }

                if (i == 1) {
                    criteriosDeBusca.setTipo(Tipo.valueOf(petValidation.validaTipo("Digite o tipo:  ")));
                } else {
                    criteriosDeBusca.setData_de_cadastro(petValidation.validaDataDeCadastro("Digite o ano:  "));
                }

                break;
            }catch (NumberFormatException e){
                System.out.println("Erro encontrado: Digite apenas números!");
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        return criteriosDeBusca;
    }

    public void mostraPerguntasDeFiltro(){
        String[] perguntas = new String[6];
        perguntas[0] = "1 - Nome ou sobrenome";
        perguntas[1] = "2 - Sexo";
        perguntas[2] = "3 - Endereço";
        perguntas[3] = "4 - Idade";
        perguntas[4] = "5 - Peso";
        perguntas[5] = "6 - Raça";
    }

    public void compara perguntas(){
        //Um array que
    }


    public CriteriosDeBusca retornaCriterioSimples(CriteriosDeBusca criteriosDeBusca){
        while (true) {
            try {
                System.out.println("Escolha um filtro!");
                System.out.println("1 - Nome ou sobrenome\n" +
                        "2 - Sexo\n" +
                        "3 - Endereço\n" +
                        "4 - Idade\n" +
                        "5 - Peso\n" +
                        "6 - Raça\n");
                System.out.print(":  ");
                int i = scanner.nextInt();
                if (i < 1 || i > 6) {
                    throw new IllegalArgumentException("A opção deve ser uma das apresentadas!");
                }
                switch (i) {
                    case 1:
                        criteriosDeBusca.setNome(petValidation.validaNome("Digite o nome ou sobrenome:  "));
                        break;
                    case 2:
                        criteriosDeBusca.setSexo(Sexo.valueOf(petValidation.validaSexo("Digite o sexo:  ")));
                        break;
                    case 3:
                        criteriosDeBusca.setCidade(petValidation.validaCidade("Digite a cidade:  "));
                        criteriosDeBusca.setRua(petValidation.validaRua("Digite a rua:  "));
                        String opção = pedir("Deseja inserir o número da casa? (S ou N)");
                        if (opção.equalsIgnoreCase("s")) {
                            criteriosDeBusca.setNum_casa(Integer.parseInt(petValidation.validaNumeroDaCasa("Digite o número da casa:  ")));
                        } else if (!opção.equalsIgnoreCase("n")) {
                            throw new IllegalArgumentException("Digite S (sim) ou N (não) para a pergunta!");
                        }
                        break;
                    case 4:
                        criteriosDeBusca.setIdade(Double.parseDouble(petValidation.validaIdade("Digite a idade:  ")));
                        break;
                    case 5:
                        criteriosDeBusca.setPeso(Double.parseDouble(petValidation.validaPeso("Digite o peso:  ")));
                        break;
                    case 6:
                        criteriosDeBusca.setRace(petValidation.validaRaça("Digite a raça:  "));
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro encontrado: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Erro encontrado: Digite apenas números!");
            }
            break;
        }
        return criteriosDeBusca;
    }

    public CriteriosDeBusca retornaCriteriosAvançados(){
        int i = retornaQuantidadeDeCriterios();
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
