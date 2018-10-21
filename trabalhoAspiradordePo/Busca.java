/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iatrabalho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Lara
 */
public class Busca {

    private AspiradorDePo aspirador;

    public Busca(AspiradorDePo aspirador) {
        this.aspirador = aspirador;
    }

    public void buscarLargura(Queue<Estado> borda) {
        final ArrayList<Estado> expandidos = new ArrayList<>();
         Estado inicial = aspirador.getEstado();
        Estado atual = new Estado();
        boolean testeObjetivo;
        int profundidade = 0;


        borda.clear();
        borda.add(aspirador.getEstado());
        testeObjetivo = aspirador.getEstado().testeObjetivo();

        do {
            atual = borda.remove(); // sempre remove o estado que está na cabeça da fila, ou seja, o estado mais 'raso'
            expandidos.add(atual);
             
            if (testeObjetivo == false) {
                profundidade++;
                for (Estado sucessor : atual.expandir(inicial)) {
                    if (expandidos.contains(sucessor) == false) { // o estado ainda não foi expandido, portanto adiciona à borda
                       testeObjetivo = sucessor.testeObjetivo();
                        if (testeObjetivo == true) { /// O TESTE OBJETIVO É FEITO EM CADA NÓ QDO ESTE É GERADO 
                            atual = sucessor;
                            break; // sai do for e do while pois já gerou estado objetivo
                        }
                        borda.add(sucessor); // se não foi expandido e não é estado objetivo, adiciona à borda
                    }
                }
            }
        } while (!testeObjetivo && !borda.isEmpty());

        System.out.println("\nEstado final:");
        atual.mostrarEstado();
        if (testeObjetivo) {
            System.out.println("\n> Achou solucao com profundidade igual a " + profundidade);
        } else {
            System.out.println("\n> Nao achou solucao");
        }

    }

    public void buscarProfundidade(Queue<Estado> borda) {
        final ArrayList<Estado> expandidos = new ArrayList<>();
        ArrayList<Estado> sucessores = new ArrayList<>();
        Estado inicial = aspirador.getEstado();
        Estado atual = new Estado();
        boolean testeObjetivo;
        int profundidade = 0;

        borda.clear();
        borda.add(aspirador.getEstado());
    
        testeObjetivo = aspirador.getEstado().testeObjetivo();

        do {
            atual = borda.remove(); // sempre remove otopo da pilha, ou seja, o estado mais 'profundo'
            expandidos.add(atual);

            if (testeObjetivo == false) {
                profundidade++;
                sucessores = atual.expandir(inicial);
                Collections.reverse(sucessores);
                for (Estado sucessor : sucessores) {
                    if (expandidos.contains(sucessor) == false) {
                        testeObjetivo = sucessor.testeObjetivo();
                        if (testeObjetivo == true) {
                            atual = sucessor;
                            break; /// O TESTE OBJETIVO É FEITO EM CADA NÓ QDO ELE É GERADO E NAO QDO ELE EH EXPANDIDO
                        }
                        borda.add(sucessor);
                    }
                }

            }
        } while (!testeObjetivo && !borda.isEmpty());

        System.out.println("\nEstado final:");
        atual.mostrarEstado();
        if (testeObjetivo) {
            System.out.println("\n> Achou solucao com profundidade igual a " + profundidade);
        } else {
            System.out.println("\n> Nao achou solucao");
        }

    }

    public void buscarAEstrela(PriorityQueue<Estado> borda) {

        final ArrayList<Estado> expandidos = new ArrayList<>();
        Estado atual = new Estado();
        boolean testeObjetivo;
        int profundidade = 0;
        Estado inicial = aspirador.getEstado();
        

        borda.clear();
        borda.add(aspirador.getEstado());
        testeObjetivo = aspirador.getEstado().testeObjetivo();

        do {

            atual = borda.poll(); // retorna e remove cabeca da fila, a qual tera o estado com menor custo
            expandidos.add(atual);
            testeObjetivo = atual.testeObjetivo(); // o teste obetivo é feito qdo o estado eh expandido

            if (testeObjetivo == false) {
                profundidade++;
                for (Estado sucessor : atual.expandirAEstrela(inicial)) {
                    if (expandidos.contains(sucessor) == false) {

                        borda.add(sucessor); /// a fila de prioridade insere o estado ordenando pelo custo
                    }
                }
            }
        } while (!testeObjetivo && !borda.isEmpty());

        System.out.println("\nEstado final:");
        atual.mostrarEstadoAEstrela();
        if (testeObjetivo) {
            System.out.println("\n> Achou solucao com profundidade igual a " + profundidade);
        } else {
            System.out.println("\n> Nao achou solucao");
        }
    }
    }

    
