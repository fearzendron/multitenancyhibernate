package br.com.zendron.controller.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA. User: Zendron Date: 20/08/13 Time: 16:33
 * <p/>
 * <p/>
 * Este interceptor age em cada request, o objetivo é obter o tenant que é a primeira palavra da url Por exemplo:
 * <p/>
 * arizona.visto-web.com.br = Tenant é igual a "arizona"
 */
public class TenantHandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     * Log.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TenantHandlerInterceptor.class);

    private static final String URL_PATTERN =
            "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)" +
                    "*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";

    private static final String TENANT_IDENTIFICATION = "tenant";

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String host = null;

        try {

            host = new URL(request.getRequestURL().toString()).getHost();

            if (host.indexOf(".") == -1) {
                LOG.error("O Path passado não corresponde ao path padrão da Arizona, verifique o endereço!" +
                        "::TenantHandlerInterceptor.preHandle()");
            } else {
                host = host.substring(0, host.indexOf("."));
                Cookie c = new Cookie("tenantID", host);
                response.addCookie(c);
                LOG.info("**************************************************************************************");
                LOG.info("**************************************************************************************");
                LOG.info("---------------------------              TENANT              -------------------------");
                LOG.info("----------------              " + host + "              ----------------");
                LOG.info("**************************************************************************************");
                LOG.info("**************************************************************************************");

            }


        } catch (MalformedURLException ue) {
            LOG.error("O Path passado para o TenantHandlerInterceptor é inválido!" +
                    "::TenantHandlerInterceptor.preHandle()");
        }

        if (Pattern.matches(URL_PATTERN, host)) {
            if (!isNumeric(host)) {
            } else {
                LOG.error("O Host é um valor numerico na identificação do tenant, " +
                        "verifique a configuração DNS do servidor!" +
                        "::TenantHandlerInterceptor.preHandle()");
            }
        } else {
            LOG.error("O Host não é um endereço válido de URLt, " +
                    "verifique o endereço do servidor!" +
                    "::TenantHandlerInterceptor.preHandle()");
        }

        return true;
    }

    /**
     * Valida se uma string obtida a partir de um host é um valor numérico o que significa que foi pego um valor IP e o
     * DNS não esta funcionando!
     *
     * @param str Valor com a string a ser validada
     * @return Retorna true se é um valor numerico e false se não for
     */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
