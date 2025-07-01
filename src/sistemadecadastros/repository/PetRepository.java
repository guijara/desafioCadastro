package sistemadecadastros.repository;

import sistemadecadastros.model.CriteriosDeBusca;
import sistemadecadastros.model.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetRepository {
    private Pet pet;

//    public PetRepository(Pet pet){
//        this.pet = pet;
//    }

    public void criaArquivo(){
        LocalDateTime now = LocalDateTime.now().withNano(0);
        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        String nomeFormatado = pet.getNome().toUpperCase().replaceAll(" ","");
        pet.setData_de_cadastro(date);
        File file = new File("petsCadastrados/"+date+"-"+nomeFormatado+".txt");
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

    public Pet criaPet(File file){
        String[] petAux = new String[7];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            int i = 0;
            String linha;
            while ((linha = bufferedReader.readLine()) != null){
                String[] partes = linha.split(" - ",2);
                petAux[i] = partes[1];
                i++;
            }
        }catch (IOException e) {
            System.out.println("Erro encontrado: Não foi possível abrir o arquivo!");
        }
        try {
            String[] endereço = petAux[3].split(",");
            return new Pet(petAux[0],petAux[1],petAux[2],endereço[0],Integer.parseInt(endereço[1]),endereço[2],Double.parseDouble(petAux[4]),Double.parseDouble(petAux[5]),petAux[6],file.getName().substring(0,6));
        }catch (Exception e){
            System.out.println("Erro encontrado!");
            return null;
        }
    }

    public Pet[] retornaTodosOsPets(){
        File file = new File("petsCadastrados");

        if (!file.exists() || !file.isDirectory()){
            System.out.println("O Diretório de registros não foi encontrado!");
            return new Pet[0];
        }

        File[] arquivos = file.listFiles();

        if (arquivos == null){
            System.out.println("Ocoreu um erro ao ler o Diretório");
            return new Pet[0];
        }

        Pet[] pets = new Pet[arquivos.length];

        int i = 0;
        for (File files: arquivos){
            Pet pet = criaPet(files);
            pets[i] = pet;
            i++;
        }
        return pets;
    }


    public Pet[] buscaPets(CriteriosDeBusca criteriosDeBusca){
        File file = new File("petsCadastrados");

        if (!file.exists() || !file.isDirectory()){
            System.out.println("O Diretório de registros não foi encontrado!");
            return new Pet[0];
        }

        File[] arquivos = file.listFiles();

        if (arquivos == null){
            System.out.println("Ocoreu um erro ao ler o Diretório");
            return new Pet[0];
        }

        Pet[] petsAux = new Pet[arquivos.length];
        int i = 0;

        for (File files:arquivos){
            Pet pet = criaPet(files);
            if (verificaPet(pet,criteriosDeBusca)){
                petsAux[i] = pet;
                i++;
            }
        }
        Pet[] pets = new Pet[i];
        System.arraycopy(petsAux,0,pets,0,i);
        return pets;
    }


    private boolean verificaPet(Pet pet,CriteriosDeBusca criterio){

        if (criterio.getNome() != null && !pet.getNome().equals(criterio.getNome())){
            return false;
        }
        if (criterio.getTipo() != null && pet.getTipo() != criterio.getTipo()){
            return false;
        }
        if (criterio.getSexo() != null && pet.getSexo() != criterio.getSexo()){
            return false;
        }
        if (criterio.getRua() != null && !pet.getRua().equals(criterio.getRua())){
            return false;
        }
        if (criterio.getNum_casa() != null && pet.getNum_casa() != criterio.getNum_casa()){
            return false;
        }
        if (criterio.getCidade() != null  && !pet.getCidade().equals(criterio.getCidade())){
            return false;
        }
        if (criterio.getIdade() != null && criterio.getIdade() != pet.getIdade()){
            return false;
        }
        if (criterio.getPeso() != null && criterio.getPeso() != pet.getPeso()){
            return false;
        }
        if (criterio.getRace() != null && !criterio.getRace().equals(pet.getRace())){
            return false;
        }
        if (criterio.getData_de_cadastro() != null && !criterio.getData_de_cadastro().equals(pet.getData_de_cadastro())){
            return false;
        }

        return true;
    }
}

