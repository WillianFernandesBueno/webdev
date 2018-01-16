
package br.com.webdev.token.dto;

import java.util.Date;
import br.com.webdev.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Descrição do Fonte
 * 
 * @author <Mês por extenso>/2018: Douglas.Queiroz
 *         <DD>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO<T extends DTO> implements DTO
{

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   /**
    * Universal Unique Identifier do token
    */
   @NonNull
   private String uuid;

   /**
    * Identificador do issuer do token
    */
   private String issuer;

   /**
    * Data de expiração do token
    */
   private Date expiresAt;

   /**
    * DTO de acessos do token
    */
   private T acessos;

}
