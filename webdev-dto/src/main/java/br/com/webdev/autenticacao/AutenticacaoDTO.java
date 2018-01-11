
package br.com.webdev.autenticacao;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AutenticacaoDTO
{

   private String email;
   private String senha;

}
