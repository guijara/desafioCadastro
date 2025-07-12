package sistemadecadastros.service;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.Validation.PetValidation;
import sistemadecadastros.model.Pet;
import sistemadecadastros.repository.PetRepository;

public class AlteraçãoService {

    private final ConsultaService consultaService;
    private final ConsoleUi consoleUi;
    private final PetValidation petValidation;
    private final PetRepository petRepository;
    private final CadastroService cadastroService;

    public AlteraçãoService(ConsoleUi consoleUi,PetValidation petValidation,PetRepository petRepository,ConsultaService consultaService,CadastroService cadastroService){
        this.consoleUi = consoleUi;
        this.petValidation = petValidation;
        this.petRepository = petRepository;
        this.consultaService = consultaService;
        this.cadastroService = cadastroService;
    }


    public void alteraPet(){
        consoleUi.printar("Alteração iniciando...");
        Pet[] pets = consultaService.retornaPetsDaconsultaAvançada();
        if (consultaService.verificaConsulta(pets)){
            int opcaoDePet = trataOpçaoDePet(pets);
            Pet petEscolhido = pets[opcaoDePet-1];
            Pet petAux = new Pet(petEscolhido);
            int opcaoDeTipo = verificaAlteracao(petEscolhido);
            petEscolhido = alteraTipo(opcaoDeTipo,petEscolhido);
            petRepository.atualizaPet(petEscolhido,petAux);
            petRepository.removeRegistroDePet(petAux);
            consoleUi.printar("Alteração concluída com sucesso!");
        }else {
            consoleUi.printar("Processo de alteração finalizado!");
        }
    }

    public void removePet(){
        consoleUi.printar("Remoção iniciando...");

        Pet[] pets = consultaService.retornaPetsDaconsultaAvançada();
        if (consultaService.verificaConsulta(pets)){
            int opcaoDePet = trataOpçaoDePet(pets);
            Pet petEscolhido = pets[opcaoDePet-1];

            String confirmacao = perguntaConfirmação();
            confirmaEscolha(confirmacao,petEscolhido);

            consoleUi.printar("Remoção concluída!");
        }else {
            consoleUi.printar("Remoção finalizada!");
        }
    }


    private void confirmaEscolha(String confirmacao, Pet pet){
        if (confirmacao.equalsIgnoreCase("s")){
            petRepository.removeRegistroDePet(pet);
            consoleUi.printar("Pet removido com sucesso!");
        }else if (confirmacao.equalsIgnoreCase("n")){
            consoleUi.printar("Remoção cancelada!");
        }
    }


    private String perguntaConfirmação(){
        String confirmacao;
        while (true){
            try {
                confirmacao = consoleUi.pedir("Tem certeza que deseja remover esse Pet dos registros? (S ou N)");
                if (!confirmacao.equalsIgnoreCase("s") && !confirmacao.equalsIgnoreCase("n")){
                    throw new IllegalArgumentException("Digite S ou N!");
                }
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return confirmacao;
    }


    private int trataOpçaoDePet(Pet[] pets){//Recebe a lista dos pets filtrados e mostra a lista até obter a opção correta do usuário
        int opcao;
        while (true){
            try {
                consultaService.mostraConsultaAvançada(pets);
                String opcaoAux = consoleUi.pedir(":  ");
                opcao = Integer.parseInt(opcaoAux.trim());
                if (opcao < 1 || opcao > pets.length){
                    throw new IllegalArgumentException("Digite uma opção válida!");
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Erro encontrado: Digite apenas números!");
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return opcao;
    }


    private int verificaAlteracao(Pet pet){
        int opcao;
        while (true){
            try {
                consoleUi.mostraTiposDoPet(pet);
                String opcaoAux = consoleUi.pedir(":  ");
                opcao = Integer.parseInt(opcaoAux);
                if (opcao < 1 || opcao > 5){
                    throw new IllegalArgumentException("Digite uma opção válida!");
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Erro encontrado: Digite apenas números!");
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return opcao;
    }


    private Pet alteraTipo(int opcaoDeTipo, Pet pet){
            switch (opcaoDeTipo){
                case 1:
                    String nome = cadastroService.pedirEValidarNome("Digite o novo nome:  ");
                    pet.setNome(nome);
                    break;
                case 2:
                    String rua = cadastroService.pedirEValidarRua("Digite a nova rua:  ");
                    pet.setRua(petValidation.validaRua(rua));
                    String numCasa = cadastroService.pedirEValidarNumCasa("Digite o novo número da casa:  ");
                    pet.setNum_casa(Integer.parseInt(numCasa));
                    String cidade = cadastroService.pedirEValidarCidade("Digite a nova cidade:  ");
                    pet.setCidade(cidade);
                    break;
                case 3:
                    String idade = cadastroService.pedirEValidarIdade("Digite a nova idade:  ");
                    pet.setIdade(Double.parseDouble(idade));
                    break;
                case 4:
                    String peso = cadastroService.pedirEValidarPeso("Digite o novo peso:  ");
                    pet.setPeso(Double.parseDouble(peso));
                    break;
                case 5:
                    String race = cadastroService.pedirEValidarRaça("Digite a nova raça:  ");
                    pet.setRace(race);
                    break;
            }
        return pet;
    }
}
