package sistemadecadastros.service;

import sistemadecadastros.model.CriteriosDeBusca;
import sistemadecadastros.model.Pet;

import java.io.*;
import java.util.Objects;

public class ConsultaService {
    Conso

    public Pet[] consultaSimples(){
        // 1 - A apllication vai pedir pedir esse metodo caso tenha q apresentar uma lista com base em um critério
        // 2 - Esse metodo vai criar um Objeto critério, que vai chamar a ConsoleUi para perguntar ao usuario qual criterio ele deseja escolher
        // 3 - Após o usuario escolher, a ConsoleUi, em outro metodo, vai receber esse critério
        // 4 - Agora a PetRepository vai pegar esse critério e comparar com os registros da pasta, um metodo vai comparar,e  outro vai fazer com que a comparação passe por todos os registros
        // 5 - Depois de retornar uma lista dos Pets validos pelos critérios, o ConsoleUi vai formatar essa lista e apresenta-la ao usuario
        // 6 - E acabou, já que o critério simples deve apenas listar com base em algum critério
    }

    public Pet[] consultaAvançada(){
        CriteriosDeBusca criterios = new CriteriosDeBusca();
        criterios = consoleUi.retornaPrimeiroCriterioObrigatorio(criterios);

        // 1 - A apllication vai pedir esse metodo caso tenha q apresentar uma lista com base em 2 ou 3 critérios
        // 2 - Esse metodo vai criar um Objeto critério, que vai chamar a ConsoleUi para perguntar ao usuario qual criterio ele deseja escolher dentre os dois primerios obrigatorios
        // 3 - Após o usuario escolher, a ConsoleUi, em outro metodo, vai receber esse critério e perguntar se ele deseja mais 1 ou 2 critérios
        // 4 - Após isso, com o loop, vc recebe mais um criterio, e coloca esse critério em uma lista de criterios já utilizados, após isso, vai repetir o loop e
        // e perguntar ao usuario qual criterio ele deseja escolher, caso ele responda um critério que já está na lista dos utilizados, então mande ele escolher outro com a ConsoleUi
        // 4 - Agora a PetRepository vai pegar esses critérios e comparar com os registros da pasta, um metodo vai comparar,e  outro vai fazer com que a comparação passe por todos os registros
        // 5 - Depois de retornar uma lista dos Pets validos pelos critérios, o ConsoleUi vai formatar essa lista e apresenta-la ao usuario
        // 6 - E acabou, já que o critério simples deve apenas listar com base em algum critério
    }

    // 1 - Application - Apenas comanda a execução de todas as classes, ela é responsavel por organizar, mostrar e executar o menu inicial
    // 2 - ConsoleUi - É a classe que conversa com o usuario, pergunta, armazena algo com base na pergunta e já passa a informação para outra classe,
    // qualquer coisa que o usuario tem que escolher funciona por aqui
    // 3 - Service - Contém as classes que organizam as maiores operações, que manipulam a execução das principais opções do menu principal
    // 3.1 - Alteração - É responsavel por coordenar a alteração dos cadastros armazenados
    // 3.2 - Cadastro - É responsavel por coordenar o cadastro dos pets, é a função primária do programa
    // 3.3 - Consulta - É responsavel por coordenar as pesquisas sobre quais Pets serão apresentados ao usuario dependendo dos critérios que ele escolher
    // 4 - Model - Contem a entidade, a abstração do real do motivo pelo qual o sistema foi feito, e contém um espelho operacional da entidade
    // 5 - Repository - É a classe responsável por interagir com os registros da pasta dos pets cadastrados, ela que analisa se precisar saber a quantidade de arquivos,
    // e se precisar passar por todos eles ou adicionar um novo arquivo
    // 6 - PetValidation - É responsavel por verificar as regras de negócio, as  condições de todos os tipos possiveis de dados da entidade,
    // um padrão sobre como utilizar e como eles devem ser recebidos e armazenados

}
