/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iatrabalho;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 *
 * @author Lara
 */
public class Estado {

    private int posicao; //a==1 e b==0
    private int ladoA; // 1 para sujo e 0 para limpo
    private int ladoB;
    private int custoHn;
    private int custoGn;
    private AspiradorDePo aspirador;
    private final static Random gerador = new Random();

    public Estado() {
        this.ladoA = gerador.nextInt(2);
        this.ladoB = gerador.nextInt(2);
        this.posicao = gerador.nextInt(2);
        this.setCustoHn();
        this.custoGn = 0;

    }

    // o custo de um estado é o numero de quadrados sujos
    public void setCustoHn() {
        if (this.ladoA == 1 && this.ladoB == 1) {
            this.custoHn = 2;
        } else if ((this.ladoA == 1 && this.ladoB == 0) || (this.ladoA == 0 && this.ladoB == 1)) {
            this.custoHn = 1;
        } else {
            this.custoHn = 0;
        }
    }
    
    
    public void setCustoGn(Estado estadoInicial){
    if(estadoInicial.getCustoHn() == 2 && this.getCustoHn() == 2)
        this.custoGn = 0;
    else if(estadoInicial.getCustoHn() == 2 && this.getCustoHn() == 1)
        this.custoGn = 1;
    else if(estadoInicial.getCustoHn() == 2 && this.getCustoHn() == 0)
        this.custoGn = 2;
    else if(estadoInicial.getCustoHn() == 1 && this.getCustoHn() == 1)
        this.custoGn = 0;
    else if(estadoInicial.getCustoHn() == 1 && this.getCustoHn() == 0)
        this.custoGn = 1;
    }
    
    public int getCustoHn(){
        return this.custoHn;
    }
    
    public int getCustoGn(){
        return this.custoGn;
    }
    
    
    

    // mostra estado e custo
    public void mostrarEstadoAEstrela() {

        if (this.posicao == 1) {
            if (this.ladoA == 0 && this.ladoB == 0) {
                System.out.println("|G   |    |  -  custo: " + this.getCusto());
            } else if (this.ladoA == 0 && this.ladoB == 1) {
                System.out.println("|G   | *  |  -  custo: " + this.getCusto());
            } else if (this.ladoA == 1 && this.ladoB == 0) {
                System.out.println("|G * |    |  -  custo: " + this.getCusto());
            } else {
                System.out.println("|G * |  * |  -  custo: " + this.getCusto());
            }
        } else if (this.ladoA == 0 && this.ladoB == 0) {
            System.out.println("|    |G   |  -  custo: "+ this.getCusto());
        } else if (this.ladoA == 0 && this.ladoB == 1) {
            System.out.println("|    |G * |  -  custo: " + this.getCusto());
        } else if (this.ladoA == 1 && this.ladoB == 0) {
            System.out.println("|  * |G   |  -  custo: " + this.getCusto());
        } else {
            System.out.println("|  * |G * |  -  custo: " + this.getCusto());
        }
    }
    
    // mostra estado, sem o custo
     public void mostrarEstado() {

        if (this.posicao == 1) {
            if (this.ladoA == 0 && this.ladoB == 0) {
                System.out.println("|G   |    |");
            } else if (this.ladoA == 0 && this.ladoB == 1) {
                System.out.println("|G   | *  |");
            } else if (this.ladoA == 1 && this.ladoB == 0) {
                System.out.println("|G * |    |");
            } else {
                System.out.println("|G * |  * |");
            }
        } else if (this.ladoA == 0 && this.ladoB == 0) {
            System.out.println("|    |G   |");
        } else if (this.ladoA == 0 && this.ladoB == 1) {
            System.out.println("|    |G * |");
        } else if (this.ladoA == 1 && this.ladoB == 0) {
            System.out.println("|  * |G   |");
        } else {
            System.out.println("|  * |G * |");
        }
    }

    public void aspirarLadoA() {
        this.setLadoA(0);
    }

    public void aspirarLadoB() {
        this.setLadoB(0);
    }

    public void vaiPraDireita() {
        this.setPosicao(0);
    }

    public void vaiPraEsquerda() {
        this.setPosicao(1);
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public void setLadoA(int ladoA) {
        this.ladoA = ladoA;
    }

    public void setLadoB(int ladoB) {
        this.ladoB = ladoB;
    }

    public int getPosicao() {
        return this.posicao;
    }

    public int getLadoA() {
        return this.ladoA;
    }

    public int getCusto() {
        return this.getCustoGn() + this.getCustoHn();
    }
   

    public int getLadoB() {
        return this.ladoB;
    }

    public boolean testeObjetivo() {
        if (this.ladoA == 0 && this.ladoB == 0) {
            return true;
        } else {
            return false;
        }
    }

    // verifica se a e b estao limpos
    public boolean estaLimpo() {
        if ((this.ladoA == 1 && this.posicao == 1) || (this.ladoB == 1 && this.posicao == 0)) {
            return false;
        } else {
            return true;
        }

    }

    private Estado(Estado e) {
        this.posicao = e.getPosicao();
        this.ladoA = e.getLadoA();
        this.ladoB = e.getLadoB();
        this.custoGn = e.getCustoGn();
        this.custoHn = e.getCustoHn();
    }
    
    
    // mostra estados sem o custo
    public ArrayList<Estado> expandir(Estado inicial) {
        ArrayList<Estado> sucessores = new ArrayList<>();
        Estado aux;
        System.out.println("\nEstado retirado da borda para expansao:");
        this.mostrarEstado();

        System.out.println("Estados gerados apos a expansao: ");

        if (this.estaLimpo() == false) {

            aux = new Estado(this);

            if (this.getPosicao() == 1) {
                aux.aspirarLadoA();// aspira lado A
            } else {
                aux.aspirarLadoB(); // aspira lado B
            }
            aux.setCustoHn();
              aux.setCustoGn(inicial);
            aux.mostrarEstado();

            sucessores.add(aux);
        }

        if (this.getPosicao() == 1) { // esta em A
            aux = new Estado(this);

            aux.vaiPraDireita(); // vai pra direita
            aux.setCustoHn();
            aux.setCustoGn(inicial);
            aux.mostrarEstado();

            sucessores.add(aux);
        }

        if (this.getPosicao() == 0) { // esta em B

            aux = new Estado(this);

            aux.vaiPraEsquerda(); // vai pra esquerda
            aux.setCustoHn();
              aux.setCustoGn(inicial);
            aux.mostrarEstado();

            sucessores.add(aux);
        }

    
        return sucessores;
    }

    // mostra estados com o custo
      public ArrayList<Estado> expandirAEstrela(Estado inicial) {
        ArrayList<Estado> sucessores = new ArrayList<>();
        Estado aux;
        System.out.println("\nEstado retirado da borda para expansao:");
        this.mostrarEstadoAEstrela();

        System.out.println("Estados gerados apos a expansao: ");

        if (this.estaLimpo() == false) {

            aux = new Estado(this);

            if (this.getPosicao() == 1) {
                aux.aspirarLadoA();// aspira lado A
            } else {
                aux.aspirarLadoB(); // aspira lado B
            }
            aux.setCustoHn();
              aux.setCustoGn(inicial);
            aux.mostrarEstadoAEstrela();

            sucessores.add(aux);
        }

        if (this.getPosicao() == 1) { // esta em A
            aux = new Estado(this);

            aux.vaiPraDireita(); // vai pra direita
            aux.setCustoHn();
              aux.setCustoGn(inicial);
            aux.mostrarEstadoAEstrela();

            sucessores.add(aux);
        }

        if (this.getPosicao() == 0) { // esta em B

            aux = new Estado(this);

            aux.vaiPraEsquerda(); // vai pra esquerda
            aux.setCustoHn();
  aux.setCustoGn(inicial);
  aux.mostrarEstadoAEstrela();

            sucessores.add(aux);
        }

    
        return sucessores;
    }

}
