package Algoritmos;

import Abstract.Inserção;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;

public class InserçãoBinaria extends Inserção
{

    public InserçãoBinaria()
    {
    }

    public InserçãoBinaria(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public InserçãoBinaria(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        
    }

    @Override
    protected void sortedVetor()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void sortedArquivo()
    {
        int tl = arquivo.size();
        RegistroArq aux = new RegistroArq();
        RegistroArq vet = new RegistroArq();
        int pos;
        for (int i = 1; i < tl; i++)
        {
            arquivo.seekArq(i);
            registro.incMovProg();
            aux.leDoArq(arquivo.getFile());
            pos = busca_binariaArquivo(aux, i);
            for (int j = i; j > pos; j--)
            {
                arquivo.seekArq(j - 1);
                vet.leDoArq(arquivo.getFile());
                arquivo.seekArq(j);
                vet.gravaNoArq(arquivo.getFile());
                registro.sumMovProg(2);//2 seeks
            }
            arquivo.seekArq(pos);
            aux.gravaNoArq(arquivo.getFile());
            registro.incMovProg();
        }
    }
    
    private int busca_binariaArquivo(RegistroArq elem, int tl)
    {
        int ini = 0, fim = tl - 1, meio = tl/2;
        RegistroArq vet = new RegistroArq();
        arquivo.seekArq(meio);
        registro.incMovProg();
        vet.leDoArq(arquivo.getFile());
        
        while(ini < fim && elem.getCodigo() != vet.getCodigo())
        {
            registro.incCompProg();
            if(elem.getCodigo() < vet.getCodigo())
                fim = meio - 1;
            else
                ini = meio + 1;
            
            meio = (ini + fim)/2;
        
            arquivo.seekArq(meio);
            registro.incMovProg();
            vet.leDoArq(arquivo.getFile());
        }
        registro.incCompProg();
        if(elem.getCodigo() < vet.getCodigo())
            return meio;
        return meio + 1;
    }

}
