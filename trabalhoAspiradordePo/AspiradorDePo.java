/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iatrabalho;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Lara
 */
public class AspiradorDePo {

    public Estado estado;

    public AspiradorDePo() {
        this.estado = new Estado();
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    // verifica se a posicao em que o aspirador esta esta limpa
    public boolean posicaoEhLimpa() {
        if ((this.estado.getPosicao() == 1 && this.estado.getLadoA() == 1) || (this.estado.getPosicao() == 0 && this.estado.getLadoB() == 0)) {
            return false;
        } else {
            return true;
        }

    }

}
