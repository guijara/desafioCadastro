package sistemadecadastros.repository;

import sistemadecadastros.model.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetRepository {
    private Pet pet;

    public PetRepository(Pet pet){
        this.pet = pet;
    }

    public void criaArquivo(){
        LocalDateTime now = LocalDateTime.now().withNano(0);
        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        String nomeFormatado = pet.getNome().toUpperCase().replaceAll(" ","");
        File file = new File("petsCadastrados"+date+"-"+nomeFormatado+".txt");
        try {
            file.createNewFile();
            try (FileWriter fw = new FileWriter(file)){
                fw.write("1 - "+pet.getNome()+"\n"+
                        "2 - "+pet.getTipo()+"\n"+
                        "3 - "+pet.getSexo()+"\n"+
                        "4 - "+pet.getRua()+", "+ pet.getNum_casa()+", "+pet.getCidade()+"\n"+
                        "5 - "+pet.getIdade()+"\n"+
                        "6 - "+pet.getPeso()+"\n"+
                        "7 - "+pet.getRace()+"\n");
                fw.flush();
            }
        }catch (IOException e){
            System.out.println("Houve um erro ao tentar criar o Arquivo!");
        }
    }

    public Pet[] retornaTodosOsPets(){ //função que retorna todos os Pets cadastrados em forma de Objetos
        File file = new File("petsCadastrados");

        if (!file.exists() || !file.isDirectory()){
            System.out.println("O Diretório de registros não foi encontrado!");
            return new Pet[0]; //aqui retornamos um array vazio ao invés de null para dizer que tentamos
                              // procurar os arquivos e não tinham arquivos, e não que houve algum erro na procura
        }

        File[] arquivos = file.listFiles();

        if (arquivos == null){
            System.out.println("Ocoreu um erro ao ler o Diretório");
            return new Pet[0];
        }

        Pet[] pets = new Pet[arquivos.length];

        int i = 0;
        for (File files: arquivos){
            String[] novoPet = new String[9];
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(files))){
                String linha;
                int j = 1;
                while ((linha = bufferedReader.readLine()) != null){
                    String[] partes = linha.split(" ");
                    if (j == 4){
                            novoPet[j-1] = partes[3];
                            novoPet[j] = partes[4];
                            novoPet[j+1] = partes[5];
                        j += 3;
                    }else {
                        novoPet[j-1] = partes[3];
                    }
                    j++;
                }
            }catch (IOException e){
                System.out.println("Erro encontrado: Não foi possível ler o arquivo!");
            }
            pets[i] = new Pet(novoPet[0],novoPet[1],novoPet[2],novoPet[3],Integer.parseInt(novoPet[4]),novoPet[5],Double.parseDouble(novoPet[6]),Double.parseDouble(novoPet[7]),novoPet[8]);
            i++;
        }
        return pets;
    }

    public Pet[] retornaComUmCriterio(String criterio){
        File file = new File("petsCadastrados");

        if (!file.exists() || !file.isDirectory()){
            System.out.println("O Diretório de registros não foi encontrado!");
            return new Pet[0]; //aqui retornamos um array vazio ao invés de null para dizer que tentamos
            // procurar os arquivos e não tinham arquivos, e não que houve algum erro na procura
        }

        File[] arquivos = file.listFiles();

        if (arquivos == null){
            System.out.println("Ocoreu um erro ao ler o Diretório");
            return new Pet[0];
        }

        Pet[] pets = new Pet[arquivos.length];

        int i = 0;
        for (File files: arquivos){
            String[] novoPet = new String[9];
            boolean verificador = false;
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(files))){
                String linha;
                int j = 1;
                while ((linha = bufferedReader.readLine()) != null){
                    String[] partes = linha.split(" ");
                    if (j == 4){
                        novoPet[j-1] = partes[3];
                        novoPet[j] = partes[4];
                        novoPet[j+1] = partes[5];
                        if (partes[3].equals(criterio)){
                            verificador = true;
                        }
                        if (partes[4].equals(criterio)){
                            verificador = true;
                        }
                        if (partes[5].equals(criterio)){
                            verificador = true;
                        }
                        j += 3;
                    }else {
                        novoPet[j-1] = partes[3];
                        if (partes[3].equals(criterio)){
                            verificador = true;
                        }
                    }
                    j++;
                }
            }catch (IOException e){
                System.out.println("Erro encontrado: Não foi possível ler o arquivo!");
            }
            if (verificador){
                pets[i] = new Pet(novoPet[0],novoPet[1],novoPet[2],novoPet[3],Integer.parseInt(novoPet[4]),novoPet[5],Double.parseDouble(novoPet[6]),Double.parseDouble(novoPet[7]),novoPet[8]);
                i++;
            }
        }
        return pets;
    }
}
