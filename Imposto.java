import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Contribuinte{
    protected String nome;
    protected double renda;

    public Contribuinte(String nome, double renda){
        this.nome = nome;
        this.renda = renda;
    }

    public abstract double calcular();

    public String pegarnome(){
        return nome;
    }
}

class fisica extends Contribuinte{
    private double saude;

    public fisica(String nome, double renda, double saude){
        super(nome, renda);
        this.saude = saude;
    }

    @Override
    public double calcular(){
        double base = (renda < 20000.0) ? 0.15 : 0.25;
        double total = renda * base - saude * 0.5;
        return Math.max(total, 0);
    }
}

class juridica extends Contribuinte{
    private int funcionarios;

    public juridica(String nome, double renda, int funcionarios){
        super(nome, renda);
        this.funcionarios = funcionarios;
    }

    @Override
    public double calcular(){
        double base = (funcionarios > 10) ? 0.14 : 0.16;
        return renda * base;
    }
}

public class Imposto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Contribuinte> lista = new ArrayList<>();

        System.out.println("Quantos contribuintes serão cadastrados?");
        int n = scanner.nextInt();

        for(int i = 1; i <= n; i++){
            System.out.println("Contribuinte #" + i + " - pessoa física ou jurídica (f/j)?");
            char tipo = scanner.next().toLowerCase().charAt(0);

            System.out.print("Nome: ");
            scanner.nextLine();
            String nome = scanner.nextLine();

            System.out.print("Renda anual: ");
            double renda = scanner.nextDouble();

            if(tipo == 'f'){
                System.out.print("Gastos com saúde: ");
                double saude = scanner.nextDouble();
                lista.add(new fisica(nome, renda, saude));
            }
            else{
                System.out.print("Número de funcionários: ");
                int funcionarios = scanner.nextInt();
                lista.add(new juridica(nome, renda, funcionarios));
            }
        }

        System.out.println("\nVALOR EM IMPOSTO PAGO:");
        double totali = 0.0;

        for(Contribuinte c : lista){
            double imposto = c.calcular();
            System.out.printf("%s: R$ %.2f\n", c.pegarnome(), imposto);
            totali += imposto;
        }

        System.out.printf("\nTOTAL DE IMPOSTOS: R$ %.2f\n", totali);

        scanner.close();
    }
}