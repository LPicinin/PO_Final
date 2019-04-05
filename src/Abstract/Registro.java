package Abstract;

public abstract class Registro
{

    protected int compProg;

    protected int compEqua;

    protected int movProg;

    protected int movEqua;

    protected long tempo;

    public Registro(int compProg, int compEqua, int movProg, int movEqua, long tempo)
    {
        this.compProg = compProg;
        this.compEqua = compEqua;
        this.movProg = movProg;
        this.movEqua = movEqua;
        this.tempo = tempo;
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

    public void incCompProg()
    {
        this.compProg++;
    }

    public void incCompEqua()
    {
        this.compEqua++;
    }

    public void incMovProg()
    {
        this.movProg++;
    }

    public void incMovEqua()
    {
        this.movEqua++;
    }

    public void incTempo()
    {
        this.tempo++;
    }

    public void sumCompProg(int compProg)
    {
        this.compProg += compProg;
    }

    public void sumCompEqua(int compEqua)
    {
        this.compEqua += compEqua;
    }

    public void sumMovProg(int movProg)
    {
        this.movProg += movProg;
    }

    public void sumMovEqua(int movEqua)
    {
        this.movEqua += movEqua;
    }

    public void sumTempo(long tempo)
    {
        this.tempo += tempo;
    }
    public void iniciaValores()
    {
        compProg = compEqua = movProg = movProg = movEqua = 0;
        tempo = 0;
    }
    public void iniciaEqui(int comp, int mov)
    {
        compEqua = comp;
        movEqua = mov;
    }

}
