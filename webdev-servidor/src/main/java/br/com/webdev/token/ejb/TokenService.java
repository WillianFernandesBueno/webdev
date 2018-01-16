
package br.com.webdev.token.ejb;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.webdev.dto.DTO;
import br.com.webdev.exception.RequisicaoException;
import br.com.webdev.token.dto.TokenDTO;

/**
 * Descrição do Fonte
 * 
 * @author <Mês por extenso>/2018: Douglas.Queiroz
 *         <DD>
 */
@Startup
@Singleton
public class TokenService
{

   private static final String ACESSOS = "ace";
   private static final String UUID = "uid";

   private Algorithm algorithm;
   private ObjectMapper objectMapper = new ObjectMapper();

   /**
    * Inicializa a Service de Tokens - Instancia o algorítimo utilizado para assinatura do token (HMAC256)
    */
   @PostConstruct
   public void inicializa()
   {
      this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

      try
      {
         // #TODO armazenar uma senha valida no banco de dados
         this.algorithm = Algorithm.HMAC256("secret");
      }
      catch (IllegalArgumentException | UnsupportedEncodingException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Cria um token com as informações definidas.
    *
    * @param token DTO do token a ser gerado
    * @return token JWT
    */
   public String cria()
   {
      JWTCreator.Builder builder = JWT.create()
               .withIssuedAt(new Date())
               .withClaim(UUID, "");

      return builder.sign(this.algorithm);
   }

   /**
    * Verifica e retorna a representação em DTO do token informado
    *
    * @param token
    * @return Representação em DTO do token informado
    */
   public <T extends DTO> TokenDTO<T> carrega(Class<T> type, String token)
   {
      try
      {
         DecodedJWT decoded = JWT.require(algorithm).build().verify(token);

         TokenDTO.TokenDTOBuilder<DTO> builder = TokenDTO.builder();

         Claim acesso = decoded.getClaim(ACESSOS);
         if (!acesso.isNull())
         {
            builder.acessos(
               objectMapper.readValue(Base64.getDecoder().decode(acesso.asString()), type));
         }

         return (TokenDTO<T>) builder
                  .uuid(decoded.getClaim(UUID).asString())
                  .issuer(decoded.getIssuer()).expiresAt(decoded.getExpiresAt()).build();
      }
      catch (Exception e)
      {
         throw new RequisicaoException(HttpServletResponse.SC_FORBIDDEN, "Token inválido ou expirado.", e);
      }
   }

}
