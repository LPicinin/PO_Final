package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;

/**
 * Uma melhoria p/ este método(buble) seria alterar os sucessivos passos de
 * ordenação. O algoritmo resultante sedda prática se chama ShakeSort (ordenação
 * por agitação)
 *
 * @author Aluno
 */
public class Shake extends Algoritmo
{

    public Shake()
    {
    }

    public Shake(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Shake(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        int aux;
        for (No inicio = lista.getInicio(), fim = lista.getFim(); inicio != fim.getAnt(); inicio = inicio.getProx())
        {
            for (No i = inicio; i != fim; i = i.getProx())
            {
                if (i.getInfo() > i.getProx().getInfo())
                {
                    aux = i.getInfo();
                    i.setInfo(i.getProx().getInfo());
                    i.getProx().setInfo(aux);
                }
            }
            fim = fim.getAnt();
            for (No j = fim; j != inicio; j = j.getAnt())
            {
                if (j.getInfo() < j.getAnt().getInfo())
                {
                    aux = j.getInfo();
                    j.setInfo(j.getAnt().getInfo());
                    j.getAnt().setInfo(aux);
                }
            }
        }
    }
    @Override
    protected void sortedVetor()
    {
        int aux;
        int inicio = 0, fim = TL - 1;
        while (inicio < fim)
        {
            for (int j = inicio; j < fim; j++)
            {
                if (vet[j] > vet[j + 1])
                {
                    aux = vet[j];
                    vet[j] = vet[j + 1];
                    vet[j + 1] = aux;
                }
            }
            fim--;
            for (int j = fim; j > inicio; j--)
            {
                if (vet[j] < vet[j - 1])
                {
                    aux = vet[j];
                    vet[j] = vet[j - 1];
                    vet[j - 1] = aux;
                }
            }
            inicio++;
        }
    }

    @Override
    protected void sortedArquivo()
    {
        int inicio = 0, fim = arquivo.size() - 1;
        RegistroArq regi = new RegistroArq();
        RegistroArq regii = new RegistroArq();
        while (inicio < fim)
        {
            for (int i = inicio; i < fim; i++)
            {
                registro.sumMovProg(2);
                arquivo.seekArq(i);
                regi.leDoArq(arquivo.getFile());
                regii.leDoArq(arquivo.getFile());
                
                registro.incCompProg();
                if (regi.getCodigo() > regii.getCodigo())
                {
                    registro.sumMovProg(2);
                    arquivo.seekArq(i);
                    regii.gravaNoArq(arquivo.getFile());
                    regi.gravaNoArq(arquivo.getFile());
                }
            }
            fim--;
            for (int i = fim; i > inicio; i--)
            {
                registro.incMovProg();
                arquivo.seekArq(i);
                regi.leDoArq(arquivo.getFile());
                
                registro.incMovProg();
                arquivo.seekArq(i - 1);
                regii.leDoArq(arquivo.getFile());
                
                registro.incCompProg();
                if (regi.getCodigo() < regii.getCodigo())
                {
                    registro.incMovProg();
                    arquivo.seekArq(i);
                    regii.gravaNoArq(arquivo.getFile());
                    
                    registro.incMovProg();
                    arquivo.seekArq(i - 1);
                    regi.gravaNoArq(arquivo.getFile());
                }
            }
            inicio++;
        }
    }

}
