package rx.ee.v.jaxws;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.WebServiceRef;
import org.oorsprong.websamples.ArrayOftLanguage;
import org.oorsprong.websamples.ListOfLanguagesByCode;
import org.oorsprong.websamples.TLanguage;
import org.oorsprong.websamples_countryinfo.CountryInfoService;
import org.oorsprong.websamples_countryinfo.CountryInfoServiceSoapType;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns="/jax-ws")
public class SOAPWebServiceClientCallServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation="http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?wsdl")
    private CountryInfoService countryInfoService;
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response )
            throws IOException, ServletException {
        response.setContentType("text/html");
        try(PrintWriter out = response.getWriter()) {
            CountryInfoServiceSoapType port = countryInfoService.getCountryInfoServiceSoap();
            ArrayOftLanguage arrayOfLanguage = port.listOfLanguagesByCode();
            for (TLanguage language : arrayOfLanguage.getTLanguage()) {
                out.println(language.getSName() + " with code " + language.getSISOCode());
                out.println("<br />");
            }
        }
    }
}
