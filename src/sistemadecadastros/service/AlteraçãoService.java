package sistemadecadastros.service;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.Validation.PetValidation;
import sistemadecadastros.model.Pet;
import sistemadecadastros.repository.PetRepository;

public class AlteraçãoService {
    ConsultaService consultaService = new ConsultaService();
    ConsoleUi consoleUi = new ConsoleUi();
    PetValidation petValidation = new PetValidation();
    PetRepository petRepository = new PetRepository();


    public void alteraPet(){
        consoleUi.printar("Alteração iniciando...");
        Pet[] pets = consultaService.retornaPetsDaconsultaAvançada();
        int opcaoDePet = trataOpçaoDePet(pets);
        Pet petEscolhido = pets[opcaoDePet-1];
        Pet petAux = new Pet(petEscolhido);
        int opcaoDeTipo = verificaAlteracao(petEscolhido);
        petEscolhido = alteraTipo(opcaoDeTipo,petEscolhido);
        petRepository.atualizaPet(petEscolhido,petAux);
        consoleUi.printar("Alteração concluída com sucesso!");
    }

    public void removePet(){
        consoleUi.printar("Remoção iniciando...");

        Pet[] pets = consultaService.retornaPetsDaconsultaAvançada();
        int opcaoDePet = trataOpçaoDePet(pets);
        Pet petEscolhido = pets[opcaoDePet-1];

        String confirmacao = perguntaConfirmação();
        confirmaEscolha(confirmacao,petEscolhido);
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
                    pet.setNome(petValidation.validaNome("Digite o novo nome:  "));
                    break;
                case 2:
                    pet.setRua(petValidation.validaRua("Digite a nova rua:  "));
                    pet.setNum_casa(Integer.parseInt(petValidation.validaNumeroDaCasa("Digite o novo número da casa:  ")));
                    pet.setCidade(petValidation.validaCidade("Digite a nova cidade:  "));
                    break;
                case 3:
                    pet.setIdade(Double.parseDouble(petValidation.validaIdade("Digite a nova idade:  ")));
                    break;
                case 4:
                    pet.setPeso(Double.parseDouble(petValidation.validaPeso("Digite o novo peso:  ")));
                    break;
                case 5:
                    pet.setRace(petValidation.validaRaça("Digite a nova raça:  "));
                    break;
            }
        return pet;
    }
}
