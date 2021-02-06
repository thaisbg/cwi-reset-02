package OO;

public class Aplicacao {

    public static void main(String[] args) {

        Carro chevette = new Carro("Chevrolet", "Chevette", 1990, 4);
        chevette.estacionar();
        chevette.abastecer(40);

        System.out.println("O veículo está estacionado? " + chevette.isEstacionado());

        chevette.acelerar(30);

        System.out.println("O veículo está estacionado? " + chevette.isEstacionado());
        System.out.println("O veículo ficou com " + chevette.getQuantidadeCombustivel() + " litros de combustível");


    }
}
