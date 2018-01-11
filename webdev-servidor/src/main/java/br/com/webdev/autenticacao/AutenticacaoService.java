
package br.com.webdev.autenticacao;

import javax.ejb.Stateless;

@Stateless
public class AutenticacaoService implements IAutenticacao
{

   @Override
   public void realizaLogin()
   {
      System.out.println("entrou");
   }

}
