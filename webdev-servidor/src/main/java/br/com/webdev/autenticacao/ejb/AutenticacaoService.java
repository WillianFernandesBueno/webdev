
package br.com.webdev.autenticacao.ejb;

import javax.ejb.Stateless;
import br.com.webdev.autenticacao.IAutenticacao;

@Stateless
public class AutenticacaoService implements IAutenticacao
{

   @Override
   public void realizaLogin()
   {
      System.out.println("entrou");
   }

}
