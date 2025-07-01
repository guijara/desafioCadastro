package sistemadecadastros.service;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.Validation.PetValidation;
import sistemadecadastros.model.CriteriosDeBusca;
import sistemadecadastros.model.Pet;
import sistemadecadastros.model.Sexo;
import sistemadecadastros.model.Tipo;
import sistemadecadastros.repository.PetRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsultaService {
    ConsoleUi consoleUi = new ConsoleUi();
    CriteriosDeBusca criteriosDeBusca = new CriteriosDeBusca();
    PetValidation petValidation = new PetValidation();
    Scanner scanner = new Scanner(System.in);
    PetRepository petRepository = new PetRepository();


    public void consultaGeral(){
        Pet[] pets = petRepository.retornaTodosOsPets();

        int i = 1;

        for (Pet pet:pets){
            consoleUi.formataListaDePets(pet,i);
            i++;
        }
    }


    public void consultaSimples(){
        CriteriosDeBusca criterio = new CriteriosDeBusca();

        int escolha = Integer.parseInt(verificaEscolhaDeNumeroSimples());
        criterio = retornaCriterioSimples(criterio,escolha);

        Pet[] pets = petRepository.buscaPets(criterio);
        int i = 1;
        for (Pet pet:pets){
            consoleUi.formataListaDePets(pet,i);
            i++;
        }
    }


    public void consultaAvançada() {
        CriteriosDeBusca criterios = new CriteriosDeBusca();
        String[] filtrosUtilizados = new String[2];
        criterios = retornaPrimeiroCriterioObrigatorio(criterios);
        for (int i = 0; i < retornaQuantidadeDeCriteriosOpcionais(); i++) {
                String escolha =  verificaEscolhaDeNumeroAvançada(filtrosUtilizados);
                criterios = retornaCriterioAvançado(criterios, retornaRelaçãoDeEscolha(escolha,filtrosUtilizados));
                filtrosUtilizados[i] = identificaCriterio(criterios);
        }
        Pet[] pets = petRepository.buscaPets(criterios);
        int i = 1;
        for (Pet pet:pets){
            consoleUi.formataListaDePets(pet,i);
            i++;
        }
    }


    private CriteriosDeBusca retornaPrimeiroCriterioObrigatorio(CriteriosDeBusca criteriosDeBusca){
        while (true){
            try {
                int i = Integer.parseInt(consoleUi.pedir("Escolha um filtro primário para encontrar seu pet!\n" + "1 - Tipo do Pet (Cachorro ou Gato)\n" + "2 - Data de cadastro do Pet\n" + ":  "));

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


    private int retornaQuantidadeDeCriteriosOpcionais(){
        int i;
        while (true){
            try {
                i = Integer.parseInt(consoleUi.pedir("Você deve selecionar alguma(s) outras opções para filtrar seu Pet, deseja selecionar quantas opções?\n" + "1 - Uma opção de filtro\n" + "2 - Duas opções de filtro\n" + ":  "));
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


    private String retornaPerguntasPossiveis(String[] filtros) { //Ele retorna todos os tipos que não forão usados ainda pelo usuario
        String[] perguntasValidas = {"Nome ou sobrenome\n", "Sexo\n", "Endereço\n", "Idade\n", "Peso\n", "Raça\n"};
        for (int i = 0; i < perguntasValidas.length; i++) {
            for (int j = 0; j < filtros.length; j++) {
                if (perguntasValidas[i].equals(filtros[j])) {
                    perguntasValidas[i] = "";
                    break;
                }
            }
        }
        String perguntasValidasAux = "";
        int i = 1;
        int j = 0;
        while (j < perguntasValidas.length){
            if (!perguntasValidas[j].isBlank()) {
                perguntasValidasAux += (i) + " - " + perguntasValidas[j];
                i++;
            }
            j++;
        }
        perguntasValidasAux = "Escolha uma opção abaixo:\n" + perguntasValidasAux;
        return perguntasValidasAux;
    }


    private CriteriosDeBusca retornaCriterioAvançado(CriteriosDeBusca criteriosDeBusca, String tipo){
        while (true) {
            try {
                switch (tipo) {
                    case "Nome ou sobrenome\n":
                        criteriosDeBusca.setNome(petValidation.validaNome("Digite o nome ou sobrenome:  "));
                        break;
                    case "Sexo\n":
                        criteriosDeBusca.setSexo(Sexo.valueOf(petValidation.validaSexo("Digite o sexo:  ")));
                        break;
                    case "Endereço\n":
                        criteriosDeBusca.setCidade(petValidation.validaCidade("Digite a cidade:  "));
                        criteriosDeBusca.setRua(petValidation.validaRua("Digite a rua:  "));
                        String opção = consoleUi.pedir("Deseja inserir o número da casa? (S ou N)");
                        if (opção.equalsIgnoreCase("s")) {
                            criteriosDeBusca.setNum_casa(Integer.parseInt(petValidation.validaNumeroDaCasa("Digite o número da casa:  ")));
                        } else if (!opção.equalsIgnoreCase("n")) {
                            throw new IllegalArgumentException("Digite S (sim) ou N (não) para a pergunta!");
                        }
                        break;
                    case "Idade\n":
                        criteriosDeBusca.setIdade(Double.parseDouble(petValidation.validaIdade("Digite a idade:  ")));
                        break;
                    case "Peso\n":
                        criteriosDeBusca.setPeso(Double.parseDouble(petValidation.validaPeso("Digite o peso:  ")));
                        break;
                    case "Raça\n":
                        criteriosDeBusca.setRace(petValidation.validaRaça("Digite a raça:  "));
                        break;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro encontrado: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Erro encontrado: Digite apenas números!");
            }
        }
        return criteriosDeBusca;
    }


    private CriteriosDeBusca retornaCriterioSimples(CriteriosDeBusca criteriosDeBusca, int i){
        while (true){
            switch (i){
                case 1:
                    criteriosDeBusca.setNome(petValidation.validaNome("Digite o nome ou sobrenome:  "));
                    break;
                case 2:
                    criteriosDeBusca.setTipo(Tipo.valueOf(petValidation.validaTipo("Digite o tipo do pet:  ")));
                    break;
                case 3:
                    criteriosDeBusca.setSexo(Sexo.valueOf(petValidation.validaSexo("Digite o sexo:  ")));
                    break;
                case 4:
                    criteriosDeBusca.setCidade(petValidation.validaCidade("Digite a cidade:  "));
                    criteriosDeBusca.setRua(petValidation.validaRua("Digite a rua:  "));
                    String opção = consoleUi.pedir("Deseja inserir o número da casa? (S ou N)");
                    if (opção.equalsIgnoreCase("s")) {
                        criteriosDeBusca.setNum_casa(Integer.parseInt(petValidation.validaNumeroDaCasa("Digite o número da casa:  ")));
                    } else if (!opção.equalsIgnoreCase("n")) {
                        throw new IllegalArgumentException("Digite S (sim) ou N (não) para a pergunta!");
                    }
                    break;
                case 5:
                    criteriosDeBusca.setIdade(Double.parseDouble(petValidation.validaIdade("Digite a idade:  ")));
                    break;
                case 6:
                    criteriosDeBusca.setPeso(Double.parseDouble(petValidation.validaPeso("Digite o peso:  ")));
                    break;
                case 7:
                    criteriosDeBusca.setRace(petValidation.validaRaça("Digite a raça:  "));
                    break;
                case 8:
                    criteriosDeBusca.setData_de_cadastro(petValidation.validaDataDeCadastro("Digite a data de cadastro do Pet"));
                    break;
            }
            break;
        }
        return criteriosDeBusca;
    }



    private String identificaCriterio(CriteriosDeBusca criteriosDeBusca) { //Recebe o objeto criterio com um atributo passado pelo usuário
        if (criteriosDeBusca.getNome() != null ){
            return "Nome ou sobrenome\n";
        }
        if (criteriosDeBusca.getSexo() != null){
            return "Sexo\n";
        }
        if (criteriosDeBusca.getRua() != null){
            return "Endereço\n";
        }else  if (criteriosDeBusca.getNum_casa() != null){
            return "Endereço\n";
        }else if (criteriosDeBusca.getCidade() != null){
            return "Endereço\n";
        }
        if (criteriosDeBusca.getIdade() != null){
            return "Idade\n";
        }
        if (criteriosDeBusca.getPeso() != null){
            return "Peso\n";
        }
        if (criteriosDeBusca.getRace() != null){
            return "Raça\n";
        }
        return null;
    }



    private String retornaRelaçãoDeEscolha(String escolha, String[] filtrosUtilizados){//Utiliza as perguntas possiveis do
        //menu dinamico e encontra a linha correspondente ao numero que o usuario escolheu
        //divide a linha e pega só o tipo e retorna
        //esse tipo é usado na retornaCriterioSimples para identificar o switch
        //talvez substitua o  retornaNumeroDaOpçaoDeCriterio()
        String filtro = "";
        String[] partes1 = retornaPerguntasPossiveis(filtrosUtilizados).split("\n");
        for (int i = 0;i < partes1.length;i++){
            if (partes1[i].charAt(0) == (escolha).charAt(0)){
                String filtroAux = partes1[i];
                filtro = filtroAux.split(" - ")[1];
            }
        }
        return filtro;
    }


    private String verificaEscolhaDeNumeroAvançada(String[] filtrosUtilizados){
        String escolha;
        while (true){
            try {
                String partes[] = retornaPerguntasPossiveis(filtrosUtilizados).split("\n");
                escolha = consoleUi.pedir(retornaPerguntasPossiveis(filtrosUtilizados));
                int testeAux = Integer.parseInt(escolha.trim());
                if (testeAux < 1 || testeAux > partes.length){
                    throw new IllegalArgumentException("Opção inválida!");
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Erro encontrado: Digite apenas números");
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return escolha.trim();
    }


    private String verificaEscolhaDeNumeroSimples(){
        String escolha;
        while (true){
            try {
                escolha = consoleUi.pedir("1 - Nome ou sobrenome\n"+"2 - Tipo\n"+"3 - Sexo\n"+"4 - Endereço\n"+"5 - Idade\n"+"6 - Peso\n"+"7 - Raça\n"+"8 - Data de cadastro\n"+":  ").trim();
                int testeAux = Integer.parseInt(escolha);
                if (testeAux < 1 || testeAux > 8){
                    throw new IllegalArgumentException("Escolha uma opção válida!");
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Erro encontrado: Digite um número válido!");
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return escolha;
    }









    // 1 - A apllication vai pedir esse metodo caso tenha q apresentar uma lista com base em 2 ou 3 critérios
        // 2 - Esse metodo vai criar um Objeto critério, que vai chamar a ConsoleUi para perguntar ao usuario qual criterio ele deseja escolher dentre os dois primerios obrigatorios
        // 3 - Após o usuario escolher, a ConsoleUi, em outro metodo, vai receber esse critério e perguntar se ele deseja mais 1 ou 2 critérios
        // 4 - Após isso, com o loop, vc recebe mais um criterio, e coloca esse critério em uma lista de criterios já utilizados, após isso, vai repetir o loop e
        // e perguntar ao usuario qual criterio ele deseja escolher, caso ele responda um critério que já está na lista dos utilizados, então mande ele escolher outro com a ConsoleUi
        // 4 - Agora a PetRepository vai pegar esses critérios e comparar com os registros da pasta, um metodo vai comparar,e  outro vai fazer com que a comparação passe por todos os registros
        // 5 - Depois de retornar uma lista dos Pets validos pelos critérios, o ConsoleUi vai formatar essa lista e apresenta-la ao usuario
        // 6 - E acabou, já que o critério simples deve apenas listar com base em algum critério

    // 1 - Application - Apenas comanda a execução de todas as classes, ela é responsavel por organizar, mostrar e executar o menu inicial
    // 2 - ConsoleUi - É a classe que conversa com o usuario, pergunta, armazena algo com base na pergunta e já passa a informação para outra classe,
    // qualquer coisa que o usuario tem que escolher funciona por aqui
    // 3 - Service - Contém as classes que organizam as maiores operações, que manipulam a execução das principais opções do menu principal
    // 3.1 - Alteração - É responsavel por coordenar a alteração dos cadastros armazenados
    // 3.2 - Cadastro - É responsavel por coordenar o cadastro dos pets, é a função primária do programa
    // 3.3 - Consulta - É responsavel por coordenar as pesquisas sobre quais Pets serão apresentados ao usuario dependendo dos critérios que ele escolher
    // 4 - Model - Contem a entidade, a abstração do real do motivo pelo qual o sistema foi feito, e contém um espelho operacional da entidade
    // 5 - Repository - É a classe responsável por interagir com os registros da pasta dos pets cadastrados, ela que analisa se precisar saber a quantidade de arquivos,
    // e se precisar passar por todos eles ou adicionar um novo arquivo
    // 6 - PetValidation - É responsavel por verificar as regras de negócio, as  condições de todos os tipos possiveis de dados da entidade,
    // um padrão sobre como utilizar e como eles devem ser recebidos e armazenados

}
