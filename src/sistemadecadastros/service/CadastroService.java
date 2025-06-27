package sistemadecadastros.service;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.Validation.PetValidation;
import sistemadecadastros.model.Pet;
import sistemadecadastros.repository.PetRepository;

import java.io.*;

public class CadastroService {
    ConsoleUi consoleUi = new ConsoleUi();
    PetValidation petValidation = new PetValidation();

    private File escrevaQuestionário() {
        File file = new File("formulario.txt");
        try {
            file.createNewFile();
            boolean exists = file.exists();
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("1 - Qual o nome e sobrenome do pet?:  " +
                        "2 - Qual o tipo do pet (Cachorro/Gato)?\n:  " +
                        "3 - Qual o sexo do animal (Macho/Femea)?\n:  " +
                        "4 - Qual endereço que ele foi encontrado - 4.1 - (Cidade)?\n:  " +
                        "4.2 - (Rua)\n:  " +
                        "4.3 - (Número da casa)\nn°:  " +
                        "5 - Qual a idade aproximada do pet (Em anos)?\n:  " +
                        "6 - Qual o peso aproximado do pet?\nKG:  " +
                        "7 - Qual a raça do pet?\n:  ");
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
                        respostas[i] = petValidation.validaNome(linha);
                        break;
                    case 1:
                        respostas[i] = petValidation.validaTipo(linha);
                        break;
                    case 2:
                        respostas[i] = petValidation.validaSexo(linha);
                        break;
                    case 3:
                        respostas[i] = petValidation.validaCidade(linha);
                        break;
                    case 4:
                        respostas[i] = petValidation.validaRua(linha);
                        break;
                    case 5:
                        respostas[i] = petValidation.validaNumeroDaCasa(linha);
                        break;
                    case 6:
                        respostas[i] = petValidation.validaIdade(linha);
                        break;
                    case 7:
                        respostas[i] = petValidation.validaPeso(linha);
                        break;
                    case 8:
                        respostas[i] = petValidation.validaRaça(linha);
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
        PetRepository petRepository = new PetRepository(pet);
        petRepository.criaArquivo();
        System.out.println("Cadastro realizado com sucesso!");
    }
}
