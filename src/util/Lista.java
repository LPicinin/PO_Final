package util;

public class Lista
{

    private No inicio;

    private No fim;
    
    private int size;

    public Lista()
    {
    }

    public void inicializa()
    {
        inicio = fim = null;
        size = 0;
    }

    public void insereInicio(int inf)
    {
        No ncaixa = new No(inf);
        if (inicio == null)
        {
            inicio = fim = ncaixa;
        } else
        {
            inicio.setAnt(ncaixa);
            ncaixa.setProx(inicio);
            inicio = ncaixa;
        }
        size++;
    }

    public void insereFinal(int inf)
    {
        No ncaixa = new No(inf);
        if (inicio == null)
        {
            inicio = fim = ncaixa;
        } else
        {
            fim.setProx(ncaixa);
            ncaixa.setAnt(fim);
            fim = ncaixa;
        }
        size++;
    }

    public No get(int index)
    {
        No a = inicio;
        for (int i = 0; a != null && i < index; i++)
        {
            a = a.getProx();
        }
        return a;
    }

    public void set(int index, int info)
    {
        No a = get(index);
        if (a != null)
        {
            a.setInfo(info);
        } else
        {
            System.out.println("\nErro index errado para set na classe Lista!!!");
        }
    }

    public int size()
    {
        return size;
    }

    public boolean remove(int index)
    {
        No c = get(index);
        if (c != null)
        {
            if (inicio == fim)
            {
                inicializa();
            } else if (c == inicio)
            {
                inicio.getProx().setAnt(null);
                inicio = inicio.getProx();
            } else if (c == fim)
            {
                fim.getAnt().setProx(null);
                fim = fim.getAnt();
            } else
            {
                c.getProx().setAnt(c.getAnt());
                c.getAnt().setProx(c.getProx());
            }
            size--;
        }
        return c != null;
    }

    public boolean exclui(int inf)
    {
        No c = BuscaExaustiva(inf);
        if (c != null)
        {
            if (inicio == fim)
            {
                inicializa();
            } else if (c == inicio)
            {
                inicio.getProx().setAnt(null);
                inicio = inicio.getProx();
            } else if (c == fim)
            {
                fim.getAnt().setProx(null);
                fim = fim.getAnt();
            } else
            {
                c.getProx().setAnt(c.getAnt());
                c.getAnt().setProx(c.getProx());
            }
        }
        return c != null;
    }

    public No BuscaExaustiva(int inf)
    {
        No a = inicio;
        while (a != null && a.getInfo() != inf)
        {
            a = a.getProx();
        }
        return a;
    }

    public No BuscaSentinela(int inf)
    {
        No a = inicio;
        insereFinal(inf);
        while (a.getInfo() != inf)
        {
            a = a.getProx();
        }
        //retira o item inserido no final
        fim.getAnt().setProx(null);
        fim = fim.getAnt();

        return a;
    }

    public Integer max()
    {
        Integer maior = (size() > 0) ? -9999 : null;
        for (int i = 0; i < size(); i++)
        {
            if (maior < get(i).getInfo())
            {
                maior = get(i).getInfo();
            }
        }
        return maior;
    }

    public Integer min()
    {
        Integer menor = (size() > 0) ? 9999 : null;
        for (int i = 0; i < size(); i++)
        {
            if (menor > get(i).getInfo())
            {
                menor = get(i).getInfo();
            }
        }
        return menor;
    }

    public int[] toVector()
    {
        int s = size();
        int[] v = new int[s];
        No aux = inicio;
        for (int i = 0; aux != null; i++)
        {
            v[i] = aux.getInfo();
            aux = aux.getProx();
        }
        return v;
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        No aux = inicio;
        while (aux != null)
        {
            s.append(aux.getInfo()).append(", ");
            aux = aux.getProx();
        }
        return s.toString();
    }

    public No getInicio()
    {
        return inicio;
    }

    public No getFim()
    {
        return fim;
    }

    public void setInicio(No inicio)
    {
        this.inicio = inicio;
    }

    public void setFim(No fim)
    {
        this.fim = fim;
    }

    public No avanca(No p, int offset)//avança n elementos e retorna no vetor
    {
        No aux = p;
        for (int i = 0; aux != null && i < offset; i++)
        {
            aux = aux.getProx();
        }
        return aux;
    }
    public No recua(No p, int offset)//avança n elementos e retorna no vetor
    {
        No aux = p;
        for (int i = 0; aux != null && i < offset; i++)
        {
            aux = aux.getAnt();
        }
        return aux;
    }
    public Lista copyOf(int begin, int end)
    {
        Lista l = new Lista();
        No aux = get(begin);
        for (int i = begin; i < end && aux != null; i++)
        {
            l.insereFinal(aux.getInfo());
            aux = aux.getProx();
        }
            
        return l;
    }
    
}
