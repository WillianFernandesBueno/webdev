
package br.com.webdev.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Descrição do Fonte
 * 
 * @author <Mês por extenso>/2018: Douglas.Queiroz
 *         <DD>
 */
@Getter
@RequiredArgsConstructor
public class RequisicaoException extends RuntimeException
{

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private final int status;

   public RequisicaoException(int status, String msgErro)
   {
      super(msgErro);
      this.status = status;
   }

   public RequisicaoException(int status, String msgErro, Exception cause)
   {
      super(msgErro, cause);
      this.status = status;
   }

}
