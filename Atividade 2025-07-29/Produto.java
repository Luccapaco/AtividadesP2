import java.util.Scanner;

public class Produto{
    String nome;
    double preco;
    int quantidade;

    double valortotal(){
        return preco * quantidade;
    }
    void mostrarproduto(){
        System.out.println("Nome: "+nome);
        System.out.println("Preço: R$ "+preco);
        System.out.println("Quantidade: "+quantidade);
        System.out.println("Valor total: R$ "+valortotal());
    }
    void addestoque(int qtd){
        quantidade = quantidade + qtd;
    }
    void removerestoque(int qtd){
        quantidade = quantidade - qtd;
    }

    public static void main(String[] args){
        Scanner scanner = new  Scanner(System.in);
        Produto produto = new Produto();

        System.out.println("Digite o nome do produto: ");
        produto.nome = scanner.nextLine();

        System.out.println("Digite o valor do produto: ");
        produto.preco = scanner.nextDouble();

        System.out.println("Digite a quantidade do produto: ");
        produto.quantidade = scanner.nextInt();

        System.out.println("\nDados do produto\n");
        produto.mostrarproduto();

        System.out.println("\nDigite a quantidade de produtos a ser adicionada no estoque: ");
        int entrar = scanner.nextInt();
        produto.addestoque(entrar);

        System.out.println("\nDados atualizados do produto\n");
        produto.mostrarproduto();

        System.out.println("\nDigite a quantidade de produtos a ser removida do estoque: ");
        int sair = scanner.nextInt();
        produto.removerestoque(sair);

        System.out.println("\nDados atualizados do produto\n");
        produto.mostrarproduto();

        scanner.close();
    }
}