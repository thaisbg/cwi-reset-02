package com.company;

import com.company.domain.Editora;
import com.company.domain.Filme;
import com.company.domain.Genero;
import com.company.heranca.Diretor;

public class AplicacaoTeste {

    public static void main(String[] args) {
        Editora editora1 = new Editora("Editora Exemplo", "Teste localização");
        System.out.println("Editora: " + editora1.getNome());
        //Editora editora2 = new Editora("DC Comics", "Teste localização");

        Filme filme1 = new Filme("Interestelar", "Ficção científica/drama", 120, 2012, 2, new Diretor("Diretor fulano", 59, 13, Genero.MASCULINO));
        System.out.println(filme1.getAvaliacao());
    }

}
