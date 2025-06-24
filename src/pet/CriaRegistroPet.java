package pet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CriaRegistroPet {
    private Pet pet;

    public CriaRegistroPet(Pet pet){
        this.pet = pet;
    }

    public void criaArquivo(){
        LocalDateTime now = LocalDateTime.now().withNano(0);
        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        String nomeFormatado = pet.getNome().toUpperCase().replaceAll(" ","");
        File file = new File("src/petsCadastrados/"+date+"-"+nomeFormatado+".txt");
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
}
