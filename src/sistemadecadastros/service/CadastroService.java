package sistemadecadastros.service;

import sistemadecadastros.UI.ConsoleUi;
import sistemadecadastros.Validation.PetValidation;
import sistemadecadastros.cadastro.TratamentoEntrada;

import java.io.*;

public class CadastroService {
    ConsoleUi consoleUi = new ConsoleUi();
    PetValidation petValidation = new PetValidation();

    private File escreveArquivo() {
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

    String[] respostas = new String[9];



    public String[] printArquivoFormulario() {
        TratamentoEntrada tratamentoEntrada = new TratamentoEntrada();
        try (BufferedReader bf = new BufferedReader(new FileReader(tratamentoEntrada.retornaPastaDeRegistros()))) {
            String linha;
            int i = 0;
            while ((linha = bf.readLine()) != null && i < 9) {
                boolean verify = false;
                final String naoinformado = "NÃO INFORMADO";
                switch (i) {
                    case 0:
                        respostas[i] = petValidation.validaNome(linha);
                        i++;
                        break;
                    case 1:
                        respostas[i] = petValidation.validaTipo(linha);
                        i++;
                        break;
                    case 2:
                        respostas[i] = petValidation.validaSexo(linha);
                        i++;
                        break;
                    case 3:
                        respostas[i] = petValidation.validaCidade(linha);
                        i++;
                        break;
                    case 4:
                        respostas[i] = petValidation.validaRua(linha);
                        i++;
                        break;
                    case 5:
                        respostas[i] = petValidation.validaNumeroDaCasa(linha);
                        i++;
                        break;
                    case 6:
                        respostas[i] = petValidation.validaIdade(linha);
                        i++;
                        break;
                    case 7:
                        respostas[i] = petValidation.validaPeso(linha);
                        i++;
                        break;
                    case 8:
                        respostas[i] = petValidation.validaRaça(linha);
                        i++;
                        break;
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado ou não existe!");
        }catch (IOException e){
            System.out.println("Não foi possível ler o arquivo!");
        }
        return respostas;
    }
}
