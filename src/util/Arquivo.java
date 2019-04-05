/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author luis
 */
public class Arquivo
{

    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public Arquivo(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        {
        }
    }

    public void copiaArquivo(RandomAccessFile arquivoOrigem)
    {
    }

    public RandomAccessFile getFile()
    {
        return arquivo;
    }

    public void truncate(long pos)
    {
        try
        {
            arquivo.setLength(pos * RegistroArq.length());
        } catch (IOException exc)
        {
        }
    }

    public boolean eof()
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() >= arquivo.length())
            {
                retorno = true;
            }
        } catch (IOException e)
        {
        }
        return (retorno);
    }

    public void seekArq(int pos)
    {
        try
        {
            if(pos < size() && pos >= 0)
                arquivo.seek(pos * RegistroArq.length());
            /*else
                System.out.println("nao trocou: "+Integer.toString(pos));*/
        } catch (IOException ex)
        {
            System.out.println("Exception em seekArq");
        }
    }

    //insere um Registro no final do arquivo, passado por parâmetro
    public void inserirRegNoFinal(RegistroArq reg)
    {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public int filesize()
    {
        try
        {
            return (int) arquivo.length();
        } catch (IOException e)
        {
            return -1;
        }
    }

    public int size()
    {
        try
        {
            return (int) (arquivo.length() / RegistroArq.length());
        } catch (IOException e)
        {
            return 0;
        }
    }

    public void initComp()
    {
        comp = 0;
    }

    public void initMov()
    {
        mov = 0;
    }

    public int getComp()
    {
        return comp;
    }

    public int getMov()
    {
        return mov;
    }

    public String getNomearquivo()
    {
        return nomearquivo;
    }

    public void setNomearquivo(String nomearquivo)
    {
        this.nomearquivo = nomearquivo;
    }

    public void setArquivo(RandomAccessFile arquivo)
    {
        this.arquivo = arquivo;
    }

    public int max()
    {
        int M = Integer.MIN_VALUE;
        RegistroArq r = new RegistroArq();
        seekArq(0);
        while (!eof())
        {
            r.leDoArq(arquivo);
            if (r.getCodigo() > M)
            {
                M = r.getCodigo();
            }
        }
        return M;
    }

    public int min()
    {
        int m = Integer.MAX_VALUE;
        RegistroArq r = new RegistroArq();
        seekArq(0);
        while (!eof())
        {
            r.leDoArq(arquivo);
            if (m > r.getCodigo())
            {
                m = r.getCodigo();
            }
        }
        return m;
    }

    @Override
    public String toString()
    {
        RegistroArq r = new RegistroArq();
        seekArq(0);
        StringBuilder sb = new StringBuilder();
        while (!eof())
        {
            r.leDoArq(arquivo);
            sb.append(r.getCodigo()).append("\n");
        }
        return sb.toString();
    }

    public boolean posicoesExistes(Integer... indexs)
    {
        boolean f = true;
        int s = size();
        for (int i = 0; i < indexs.length; i++)
        {
            f = f && (indexs[i] < s) && (indexs[i] >= 0);
        }
        return f;
    }
}
