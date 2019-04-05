/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Abstract.Registro;

/**
 *
 * @author Luis
 */
public class Saida
{
    private String nome;

    protected int compProg;

    protected int compEqua;

    protected int movProg;

    protected int movEqua;

    protected long tempo;

    public Saida(String nome, Registro registro)
    {
        this.nome = nome;
        this.compProg = registro.getCompProg();
        this.compEqua = registro.getCompEqua();
        this.movProg = registro.getMovProg();
        this.movEqua = registro.getMovEqua();
        this.tempo = registro.getTempo();
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public int getCompProg()
    {
        return compProg;
    }

    public void setCompProg(int compProg)
    {
        this.compProg = compProg;
    }

    public int getCompEqua()
    {
        return compEqua;
    }

    public void setCompEqua(int compEqua)
    {
        this.compEqua = compEqua;
    }

    public int getMovProg()
    {
        return movProg;
    }

    public void setMovProg(int movProg)
    {
        this.movProg = movProg;
    }

    public int getMovEqua()
    {
        return movEqua;
    }

    public void setMovEqua(int movEqua)
    {
        this.movEqua = movEqua;
    }

    public long getTempo()
    {
        return tempo;
    }

    public void setTempo(long tempo)
    {
        this.tempo = tempo;
    }

    

}
