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
public class RegistroArq
{

    public final int SIZEINT = Integer.BYTES;//4 bytes
    private int codigo; 

    public RegistroArq()
    {
    }

    public RegistroArq(int codigo)
    {
        this.codigo = codigo;
    }

    public void gravaNoArq(RandomAccessFile arquivo)
    {
        try
        {
            arquivo.writeInt(codigo);
        } catch (IOException e)
        {
        }
    }

    public void leDoArq(RandomAccessFile arquivo)
    {
        try
        {
            codigo = arquivo.readInt();
        } catch (IOException e)
        {
        }
    }

    public static int length()
    {
        return (Integer.BYTES);
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }
}
