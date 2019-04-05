package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;

/**
 * Parecido com o Buble mais quando dois elementos são permutados ele volta uma
 * posição para trás(exceto em alguns casos - exemplo primeiro indice([0]))
 *
 * @author Luish
 */
public class Gnome extends Algoritmo
{

    public Gnome(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Gnome(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    public Gnome()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void sortedLista()
    {
        int size = lista.size();
        int temp;
        No a;
        No aa;
        int index = 0;

        while (index < size)
        {
            if (index == 0)
            {
                index++;
            }
            a = lista.get(index);
            aa = lista.get(index - 1);
            if (a.getInfo() >= aa.getInfo())
            {
                index++;
            } else
            {
                temp = a.getInfo();
                a.setInfo(aa.getInfo());
                aa.setInfo(temp);
                index--;
            }
        }
    }

    @Override
    protected void sortedVetor()
    {
        int index = 0;
        int temp;
        while (index < TL)
        {
            if (index == 0)
            {
                index++;
            }
            if (vet[index] >= vet[index - 1])
            {
                index++;
            } else
            {
                temp = vet[index];
                vet[index] = vet[index - 1];
                vet[index - 1] = temp;
                index--;
            }
        }
    }

    @Override
    protected void sortedArquivo()
    {
        int i = 0;
        int s = arquivo.size();
        RegistroArq r = new RegistroArq();
        RegistroArq r2 = new RegistroArq();
        while (i < s)
        {
            if (i == 0)
            {
                i++;
            }
            registro.incMovProg();
            arquivo.seekArq(i);
            r.leDoArq(arquivo.getFile());
            
            registro.incMovProg();
            arquivo.seekArq(i - 1);
            r2.leDoArq(arquivo.getFile());
            
            registro.incCompProg();
            if (r.getCodigo() >= r2.getCodigo())
            {
                i++;
            } else
            {
                registro.incMovProg();
                arquivo.seekArq(i-1);
                r.gravaNoArq(arquivo.getFile());
                
                registro.incMovProg();
                arquivo.seekArq(i);
                r2.gravaNoArq(arquivo.getFile());
                i--;
            }
        }
    }

}
