/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iatrabalho;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author Lara
 */
public class Main {

    public static void main(String[] args) {

        // uso de pilha para busca em profundidade
        Queue<Estado> bordaPilha = Collections.asLifoQueue(new ArrayDeque<>());
        // uso de fila de prioridade ordenada pelo custo do estado para busca A*
        Comparator<Estado> comparator = new EstadoComparator();
        PriorityQueue<Estado> bordaPrioridade = new PriorityQueue<Estado>(10, comparator);
        // uso de fila fifo para busca em largura
        Queue<Estado> bordaFila = new ArrayDeque<>();

        AspiradorDePo aspirador = new AspiradorDePo();
        System.out.println("\nEstado Inicial: ");
        aspirador.getEstado().mostrarEstado();
        Busca busca = new Busca(aspirador);
        System.out.println("    \nBUSCA EM LARGURA\n");
        busca.buscarLargura(bordaFila);
        //System.out.println("    \nBUSCA EM PROFUNDIDADE\n");
        //busca.buscarProfundidade(bordaPilha);
        //System.out.println("    \nBUSCA A *\n");
       //busca.buscarAEstrela(bordaPrioridade);

    }
}

// esse metodo eh para implementar a fila de prioridade
class EstadoComparator implements Comparator<Estado> {

    @Override
    public int compare(Estado x, Estado y) {
       
        if (x.getCusto() < y.getCusto()) {
            return -1;
        }
        if (x.getCusto() > y.getCusto()) {
            return 1;
        }
        return 0;
    }
}
