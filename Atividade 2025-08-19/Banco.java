import java.util.Scanner;

class Conta{
    private int numero;
    private String nome;
    private double dinheiro;
    private double limite;

    public Conta(int numero, String nome, double dinheiro, double limite){
        this.numero = numero;
        this.nome = nome;
        this.dinheiro = dinheiro;
        this.limite = limite;
    }

    public void saque(double saida){
        if(saida > this.dinheiro){
            throw new IllegalArgumentException("Erro de saque: Saldo insuficiente");
        }
        else if(saida > this.limite){
            throw new IllegalArgumentException("Erro de saque: O saque excede o limite");
        }
        else{
            dinheiro = dinheiro - saida;
            System.out.println("Novo saldo: " + dinheiro);
        }
    }
}

public class Banco{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Digite os dados da conta");
            System.out.print("Número: ");
            int numero = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Titular: ");
            String nome = scanner.nextLine();

            System.out.print("Saldo: ");
            double dinheiro = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Limite: ");
            double limite = scanner.nextDouble();
            scanner.nextLine();

            Conta conta = new Conta(numero, nome, dinheiro, limite);

            System.out.print("\nDigite o quanto você deseja sacar:");
            double saida = scanner.nextDouble();
            scanner.nextLine();
            conta.saque(saida);

        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println("Erro Inesperado: " + e.getMessage());
        }

        scanner.close();
    }
}
