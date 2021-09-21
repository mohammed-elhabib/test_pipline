package dz.itinfinity.orderMS.SSL_HttpToHttps;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TLSConfigue {

    @Bean
    public TomcatServletWebServerFactory servletContainer(){
        return new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {

                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection  collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
                super.postProcessContext(context);
            }
            {
                getAdditionalTomcatConnectors().add(0,redirectConnector());
            }
        };
    }

    private Connector redirectConnector(){
        return new Connector(Http11Nio2Protocol.class.getName()) {
            {
                setScheme("http");
                setPort(99);
                setSecure(true);
                setRedirectPort(1995);
            }
        };
    }
}