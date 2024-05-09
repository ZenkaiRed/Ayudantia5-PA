package util;

import sistema.ISistema;
import sistema.SistemaArcadia;

public class Instalador {

    private ISistema sistemaArcadia;

    public Instalador(){ this.sistemaArcadia = new SistemaArcadia(); }

    public ISistema inyectarSistema(){ return this.sistemaArcadia; }

}
