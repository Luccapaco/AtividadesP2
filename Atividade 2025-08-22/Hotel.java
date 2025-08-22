import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

class Reserva{
    private int numero;
    private LocalDate checkin;
    private LocalDate checkout;

    private static DateTimeFormatter estilo = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reserva(int numero, LocalDate checkin, LocalDate checkout){
        if(!checkout.isAfter(checkin)){
            throw new IllegalArgumentException("Erro: a data de check-out deve ser depois da data de check-in.");
        }
        this.numero = numero;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public long duracao(){
        return ChronoUnit.DAYS.between(checkin, checkout);
    }

    public void atualizar(LocalDate novocheckin, LocalDate novocheckout){
        if (novocheckin.isBefore(this.checkin) || novocheckout.isBefore(this.checkin)) {
            throw new IllegalArgumentException("Erro: As novas datas devem ser depois da reserva atual.");
        }

        if (!novocheckout.isAfter(novocheckin)) {
            throw new IllegalArgumentException("Erro: A data de check-out deve ser após a data de check-in.");
        }

        this.checkin = novocheckin;
        this.checkout = novocheckout;
    }

    public String toString(){
        return "Quarto " + numero +
        ", CheckIn: " + checkin.format(estilo) +
        ", CheckOut: " + checkout.format(estilo) +
        ", " + duracao() + " noites";
    }
}

public class Hotel{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter estilo = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
            System.out.println("Número do quarto: ");
            int numero = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Data de entrada (dd/MM/yyyy):");
            LocalDate checkin = LocalDate.parse(scanner.nextLine(), estilo);

            System.out.println("Data de saída (dd/MM/yyyy):");
            LocalDate checkout = LocalDate.parse(scanner.nextLine(), estilo);

            Reserva reserva = new Reserva(numero, checkin, checkout);
            System.out.println("Reserva: " + reserva);

            System.out.println("\nDigite os novos dados da reserva:\n");

            System.out.print("Nova data de entrada (dd/MM/yyyy): ");
            LocalDate novocheckin = LocalDate.parse(scanner.nextLine(), estilo);

            System.out.print("Nova data de saída (dd/MM/yyyy): ");
            LocalDate novocheckout = LocalDate.parse(scanner.nextLine(), estilo);

            reserva.atualizar(novocheckin, novocheckout);
            System.out.println("Reserva atualizada: " + reserva);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
        System.out.println("Erro inesperado: " + e.getMessage());
        }

        scanner.close();
    }
}
