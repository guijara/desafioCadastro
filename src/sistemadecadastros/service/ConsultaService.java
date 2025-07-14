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
    Scanner scanner = new Scanner(System.in);
    CriteriosDeBusca criteriosDeBusca = new CriteriosDeBusca();

    private final ConsoleUi consoleUi;
    private final PetValidation petValidation;
    private final PetRepository petRepository;
    private final CadastroService cadastroService;

    public ConsultaService(ConsoleUi consoleUi,PetValidation petValidation,PetRepository petRepository,CadastroService cadastroService){
        this.consoleUi = consoleUi;
        this.petValidation = petValidation;
        this.petRepository = petRepository;
        this.cadastroService = cadastroService;
    }


    public void consultaGeral(){
        consoleUi.printar("Inicializando consulta...");

        Pet[] pets = petRepository.retornaTodosOsPets();

        if (verificaConsulta(pets)){
            int i = 1;

            for (Pet pet:pets){
                consoleUi.formataListaDePets(pet,i);
                i++;
            }

        }
        consoleUi.printar("Consulta Finalizada!");
    }


    public void consultaSimples(){
        consoleUi.printar("Inicializando consula...");

        CriteriosDeBusca criterio = new CriteriosDeBusca();

        int escolha = Integer.parseInt(verificaEscolhaDeNumeroSimples());
        criterio = retornaCriterioSimples(criterio,escolha);

        Pet[] pets = petRepository.buscaPets(criterio);

        if (verificaConsulta(pets)){
            int i = 1;

            for (Pet pet:pets){
                consoleUi.formataListaDePets(pet,i);
                i++;
            }
        }

        consoleUi.printar("Consulta Finalizada!");
    }


    public Pet[] retornaPetsDaconsultaAvançada() {
        CriteriosDeBusca criterios = new CriteriosDeBusca();
        String[] filtrosUtilizados = new String[2];
        criterios = retornaPrimeiroCriterioObrigatorio(criterios);
        int quantidadeDeCriteriosAdicionais = retornaQuantidadeDeCriteriosOpcionais();
        for (int i = 0; i < quantidadeDeCriteriosAdicionais; i++) {
                String escolha =  verificaEscolhaDeNumeroAvançada(filtrosUtilizados); //2
                criterios = retornaCriterioAvançado(criterios, retornaRelaçãoDeEscolha(escolha,filtrosUtilizados));
                filtrosUtilizados[i] = identificaCriterio(criterios);
        }
        Pet[] pets = petRepository.buscaPets(criterios);
        return pets;
    }

//    public Pet[] retornaPetsDaconsultaAvançada() {
//        CriteriosDeBusca criterios = new CriteriosDeBusca();
//        String[] filtrosUtilizados = new String[3];
//        int filtrosUsadosCount = 0;
//
//        // 1. Pede o critério primário e anota. Esta parte já estava certa.
//        criterios = retornaPrimeiroCriterioObrigatorio(criterios);
//        filtrosUtilizados[filtrosUsadosCount++] = identificaCriterio(criterios);
//
//        // 2. CORREÇÃO Nº 1: Pergunte a quantidade ANTES do loop.
//        int quantidadeAdicional = retornaQuantidadeDeCriteriosOpcionais();
//
//        // 3. Use a variável no loop.
//        for (int i = 0; i < quantidadeAdicional; i++) {
//
//            // 4. CORREÇÃO Nº 2: A sequência de chamadas explícita.
//
//            // a. Peça a ESCOLHA NUMÉRICA ao usuário.
//            String escolhaNumerica = verificaEscolhaDeNumeroAvançada(filtrosUtilizados);
//
//            // b. "Traduza" o número para o NOME do filtro.
//            String nomeDoFiltro = retornaRelaçãoDeEscolha(escolhaNumerica, filtrosUtilizados);
//
//            // c. Agora que sabe o nome, peça o VALOR para aquele filtro.
//            criterios = retornaCriterioAvançado(criterios, nomeDoFiltro);
//
//            // d. Atualize o controle de filtros usados.
//            filtrosUtilizados[filtrosUsadosCount++] = nomeDoFiltro;
//        }
//
//        Pet[] pets = petRepository.buscaPets(criterios);
//        return pets;
//    }

    public void mostraConsultaAvançada(Pet[] pets){
        int i = 1;
        consoleUi.printar("Qual Pet deseja alterar?");
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
                    String tipo = cadastroService.pedirEValidarTipo("Digite o tipo:  ");
                    criteriosDeBusca.setTipo(Tipo.valueOf(tipo));
                } else {
                    String data = cadastroService.pedirEValidarDataDeCadastro();
                    criteriosDeBusca.setData_de_cadastro(data);
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
                    case "Nome ou sobrenome":
                        String nome = cadastroService.pedirEValidarNome("Digite o nome ou sobrenome:  ");
                        criteriosDeBusca.setNome(nome);
                        break;
                    case "Sexo":
                        String sexo = cadastroService.pedirEValidarSexo("Digite o sexo:  ");
                        criteriosDeBusca.setSexo(Sexo.valueOf(sexo));
                        break;
                    case "Endereço":
                        String cidade = cadastroService.pedirEValidarCidade("Digite a cidade:  ");
                        criteriosDeBusca.setCidade(cidade);
                        cidade = cadastroService.pedirEValidarRua("Digite a rua:  ");
                        criteriosDeBusca.setRua(cidade);
                        String opção = consoleUi.pedir("Deseja inserir o número da casa? (S ou N)");
                        if (opção.equalsIgnoreCase("s")) {
                            cidade = cadastroService.pedirEValidarNumCasa("Digite o número da casa:  ");
                            criteriosDeBusca.setNum_casa(Integer.parseInt(cidade));
                        } else if (!opção.equalsIgnoreCase("n")) {
                            throw new IllegalArgumentException("Digite S (sim) ou N (não) para a pergunta!");
                        }
                        break;
                    case "Idade":
                        String idade = cadastroService.pedirEValidarIdade("Digite a idade:  ");
                        criteriosDeBusca.setIdade(Double.parseDouble(idade));
                        break;
                    case "Peso":
                        String peso = cadastroService.pedirEValidarPeso("Digite o peso:  ");
                        criteriosDeBusca.setPeso(Double.parseDouble(peso));
                        break;
                    case "Raça":
                        String raça = cadastroService.pedirEValidarRaça("Digite a raça:  ");
                        criteriosDeBusca.setRace(raça);
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
                    String nome = cadastroService.pedirEValidarNome("Digite o nome ou sobrenome:  ");
                    criteriosDeBusca.setNome(nome);
                    break;
                case 2:
                    String tipo = cadastroService.pedirEValidarTipo("Digite o tipo do pet:  ");
                    criteriosDeBusca.setTipo(Tipo.valueOf(tipo));
                    break;
                case 3:
                    String sexo = cadastroService.pedirEValidarSexo("Digite o sexo:  ");
                    criteriosDeBusca.setSexo(Sexo.valueOf(sexo));
                    break;
                case 4:
                    String cidade = cadastroService.pedirEValidarCidade("Digite a cidade:  ");
                    criteriosDeBusca.setCidade(cidade);
                    cidade = cadastroService.pedirEValidarRua("Digite a rua:  ");
                    criteriosDeBusca.setRua(cidade);
                    String opção = consoleUi.pedir("Deseja inserir o número da casa? (S ou N)");
                    if (opção.equalsIgnoreCase("s")) {
                        cidade = cadastroService.pedirEValidarNumCasa("Digite o número da casa:  ");
                        criteriosDeBusca.setNum_casa(Integer.parseInt(cidade));
                    } else if (!opção.equalsIgnoreCase("n")) {
                        throw new IllegalArgumentException("Digite S (sim) ou N (não) para a pergunta!");
                    }
                    break;
                case 5:
                    String idade = cadastroService.pedirEValidarIdade("Digite a idade:  ");
                    criteriosDeBusca.setIdade(Double.parseDouble(idade));
                    break;
                case 6:
                    String peso = cadastroService.pedirEValidarPeso("Digite o peso:  ");
                    criteriosDeBusca.setPeso(Double.parseDouble(peso));
                    break;
                case 7:
                    String raça = cadastroService.pedirEValidarRaça("Digite a raça:  ");
                    criteriosDeBusca.setRace(raça);
                    break;
                case 8:
                    String dataDeCadastro = cadastroService.pedirEValidarDataDeCadastro();
                    criteriosDeBusca.setData_de_cadastro(dataDeCadastro);
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
                String perguntas = retornaPerguntasPossiveis(filtrosUtilizados);
                escolha = consoleUi.pedir(perguntas);
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


    public boolean verificaConsulta(Pet[] pets){
        if (pets.length == 0){
            consoleUi.printar("Não existem registros de Pets com esses filtros!");
            return false;
        }
        return true;
    }

//    private void pedirEAdicionarUmFiltro(CriteriosDeBusca criterios, String[] filtrosUsados, int totalUsados) {
//        // Array mestre com os nomes exatos dos filtros
//        final String[] OPCOES_TOTAIS = {"Nome", "Sexo", "Idade", "Peso", "Raça", "Endereço"};
//
//        while (true) { // Loop para garantir que o usuário escolha uma opção nova e válida
//
//            // --- Etapa 1: Gerar as opções disponíveis para ESTA rodada ---
//            // (Lógica do antigo 'retornaPerguntasPossiveis')
//            String[] opcoesDisponiveis = gerarOpcoesDisponiveis(OPCOES_TOTAIS, filtrosUsados, totalUsados);
//            if (opcoesDisponiveis.length == 0) {
//                consoleUi.printar("Não há mais filtros disponíveis.");
//                return; // Sai do método se não houver mais o que escolher
//            }
//
//            // --- Etapa 2: Formatar e exibir o menu ---
//            String menuTexto = (opcoesDisponiveis);
//            int escolhaNum = consoleUi.pedirNumero(menuTexto, opcoesDisponiveis.length);
//
//            // --- Etapa 3: A "Tradução" direta e segura ---
//            String filtroEscolhido = opcoesDisponiveis[escolhaNum - 1];
//
//            // --- Etapa 4: Pedir o valor para o filtro traduzido ---
//            // (Lógica do antigo 'retornaCriterioAvançado')
//            try {
//                switch (filtroEscolhido) {
//                    case "Nome":
//                        criterios.setNome(petValidation.validaNome(consoleUi.pedir("Digite o nome: ")));
//                        break;
//                    case "Sexo":
//                        criterios.setSexo(Sexo.valueOf(petValidation.validaSexo(consoleUi.pedir("Digite o sexo (Macho ou Femea): "))));
//                        break;
//                    case "Idade":
//                        criterios.setIdade(Double.parseDouble(petValidation.validaIdade(consoleUi.pedir("Digite a idade: "))));
//                        break;
//                    case "Peso":
//                        criterios.setPeso(Double.parseDouble(petValidation.validaPeso(consoleUi.pedir("Digite o peso (KG): "))));
//                        break;
//                    case "Raça":
//                        criterios.setRace(petValidation.validaRaça(consoleUi.pedir("Digite a raça: ")));
//                        break;
//                    case "Endereço":
//                        String cidade = petValidation.validaCidade(consoleUi.pedir("Digite a cidade: "));
//                        String rua = petValidation.validaRua(consoleUi.pedir("Digite a rua: "));
//                        criterios.setCidade(cidade);
//                        criterios.setRua(rua);
//                        // Lógica para número opcional
//                        break;
//                }
//
//                // Se o switch terminou sem erro, a escolha foi um sucesso.
//                // Adiciona ao controle e sai do loop de tentativa.
//                filtrosUsados[totalUsados] = filtroEscolhido;
//                break;
//
//            } catch (IllegalArgumentException e) {
//                consoleUi.printar("Erro de validação: " + e.getMessage());
//                // O loop while(true) vai recomeçar, pedindo uma nova escolha de filtro.
//            }
//        }
//    }

}
