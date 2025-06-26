package sistemadecadastros.service;

import java.io.*;
import java.util.Objects;

public class ConsultaService {

    public void listaTodosOsPets(){
        File file = new File("/desafioCadastro/petsCadastrados");

        if (!file.exists() || !file.isDirectory()){
            System.out.println("O Diretório de registros não foi encontrado!");
            return;
        }

        File[] arquivos = file.listFiles();

        if (file.listFiles() == null){
            System.out.println("Ocoreu um erro ao ler o Diretório");
            return;
        }

        int numLinha = 1;
        String resultadoPet;
        for (File files: arquivos){
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(files))){
                resultadoPet = String.valueOf(numLinha)+". ";
                String linha;
                StringBuilder stringBuilder = new StringBuilder();
                while ((linha = bufferedReader.readLine()) != null){
                    String[] tipo = linha.split(" ");
                    if (tipo.length > 4){
                        stringBuilder = stringBuilder.append(" - ").append(tipo[2]).append(" - ").append(tipo[3]).append(" - ").append(tipo[4]);
                    }else if (tipo.length > 3){
                        stringBuilder = stringBuilder.append(" - ").append(tipo[2]).append(" - ").append(tipo[3]);
                    }else {
                        stringBuilder = stringBuilder.append(" - ").append(tipo[2]);
                    }
                }
                resultadoPet += stringBuilder.substring(3,stringBuilder.length());
                numLinha++;
                System.out.println(resultadoPet);
            }catch (IOException e){
                System.out.println("Erro ao ler o arquivo!");
            }
        }
    }

    public void listaComCritério(){

    }

    public void listaComCritérios(){

    }
}
