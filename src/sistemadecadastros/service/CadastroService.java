package sistemadecadastros.service;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.Validation.PetValidation;
import sistemadecadastros.model.Pet;
import sistemadecadastros.model.Tipo;
import sistemadecadastros.repository.PetRepository;

import java.io.*;

public class CadastroService {

    private final ConsoleUi consoleUi;
    private final PetValidation petValidation;
    private final PetRepository petRepository;

    public CadastroService(ConsoleUi consoleUi,PetValidation petValidation,PetRepository petRepository){
        this.consoleUi = consoleUi;
        this.petValidation = petValidation;
        this.petRepository = petRepository;
    }

    private File escrevaQuestionário() {
        File file = new File("formulario.txt");
        try {
            file.createNewFile();
            boolean exists = file.exists();
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("1 - Qual o nome e sobrenome do pet?:  \n" +
                        "2 - Qual o tipo do pet (Cachorro/Gato)?:  \n" +
                        "3 - Qual o sexo do animal (Macho/Femea)?:  \n" +
                        "4 - Qual endereço que ele foi encontrado - 4.1 - (Cidade)?:  \n" +
                        "4.2 - (Rua):  \n" +
                        "4.3 - (Número da casa) n°:  \n" +
                        "5 - Qual a idade aproximada do pet (Em anos)?:  \n" +
                        "6 - Qual o peso aproximado do pet? KG:  \n" +
                        "7 - Qual a raça do pet?:  \n");
                fw.flush();
            } catch (IOException e) {
                System.out.println("Erro ao inserir texto no arquivo!");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo!");
        }
        return file;
    }


    public void criaRegistroDoPet() {
        System.out.println("Inicializando cadastro...");
        String[] respostas = new String[9];
        try (BufferedReader bf = new BufferedReader(new FileReader(escrevaQuestionário()))) {
            String linha;
            int i = 0;
            while ((linha = bf.readLine()) != null && i < 9) {
                switch (i) {
                    case 0:
                        respostas[i] = pedirEValidarNome(linha);
                        break;
                    case 1:
                        respostas[i] = pedirEValidarTipo(linha);
                        break;
                    case 2:
                        respostas[i] = pedirEValidarSexo(linha);
                        break;
                    case 3:
                        respostas[i] = pedirEValidarCidade(linha);
                        break;
                    case 4:
                        respostas[i] = pedirEValidarRua(linha);
                        break;
                    case 5:
                        respostas[i] = pedirEValidarNumCasa(linha);
                        break;
                    case 6:
                        respostas[i] = pedirEValidarIdade(linha);
                        break;
                    case 7:
                        respostas[i] = pedirEValidarPeso(linha);
                        break;
                    case 8:
                        respostas[i] = pedirEValidarRaça(linha);
                        break;
                }
                i++;
            }
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado ou não existe!");
        }catch (IOException e){
            System.out.println("Não foi possível ler o arquivo!");
        }
        Pet pet = new Pet(respostas[0], respostas[1], respostas[2], respostas[4], Integer.parseInt(respostas[5]), respostas[3], Double.parseDouble(respostas[6]), Double.parseDouble(respostas[7]), respostas[8]);
        petRepository.criaArquivo(pet);
        System.out.println("Cadastro realizado com sucesso!");
    }


    private String pedirEValidarNome(String linha){
        String resposta;
        while (true){
            try {
                resposta = consoleUi.pedir(linha);
                resposta = petValidation.validaNome(resposta);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return resposta;
    }


    private String pedirEValidarTipo(String linha){
        String resposta;
        while (true){
            try {
                resposta = consoleUi.pedir(linha);
                resposta = petValidation.validaTipo(resposta);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return resposta;
    }


    private String pedirEValidarSexo(String linha){
        String resposta;
        while (true){
            try {
                resposta = consoleUi.pedir(linha);
                resposta = petValidation.validaSexo(resposta);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return resposta;
    }


    private String pedirEValidarCidade(String linha){
        String resposta;
        while (true){
            try {
                resposta = consoleUi.pedir(linha);
                resposta = petValidation.validaCidade(resposta);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return resposta;
    }


    private String pedirEValidarRua(String linha){
        String resposta;
        while (true){
            try {
                resposta = consoleUi.pedir(linha);
                resposta = petValidation.validaRua(resposta);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return resposta;
    }


    private String pedirEValidarNumCasa(String linha){
        String resposta;
        while (true){
            try {
                resposta = consoleUi.pedir(linha);
                resposta = petValidation.validaNumeroDaCasa(resposta);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return resposta;
    }


    private String pedirEValidarIdade(String linha){
        String resposta;
        while (true){
            try {
                resposta = consoleUi.pedir(linha);
                resposta = petValidation.validaIdade(resposta);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }catch (NullPointerException e){
            System.out.println("Erro encontrado: Valor inválido!");
            }
        }
        return resposta;
    }


    private String pedirEValidarPeso(String linha){
        String resposta;
        while (true){
            try {
                resposta = consoleUi.pedir(linha);
                resposta = petValidation.validaPeso(resposta);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }catch (NullPointerException e){
                System.out.println("Erro encontrado: Valor inválido!");
            }
        }
        return resposta;
    }


    private String pedirEValidarRaça(String linha){
        String resposta;
        while (true){
            try {
                resposta = consoleUi.pedir(linha);
                resposta =  petValidation.validaRaça(resposta);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Erro encontrado: "+e.getMessage());
            }
        }
        return resposta;
    }
}
