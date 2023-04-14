package application;

import Services.ContractServices;
import Services.PaypalService;
import entities.Contract;
import entities.Installment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[]args){

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre os dados do contrato: ");
        System.out.println("Número: ");
        int number = sc.nextInt();
        System.out.println("Data (dd/MM /yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), fmt);
        System.out.println("Valor do contrato: ");
        double totalValue = sc.nextDouble();

        Contract obj = new Contract(number, date, totalValue);


        System.out.println("Digite o número de parcelas desejadas: ");
        int n = sc.nextInt();

        ContractServices contractServices = new ContractServices(new PaypalService());

        contractServices.processContract(obj, n);

        System.out.println("Parcelas: ");
        for(Installment installment : obj.getInstallment()){
            System.out.println(installment);
        }

        sc.close();

    }
}
