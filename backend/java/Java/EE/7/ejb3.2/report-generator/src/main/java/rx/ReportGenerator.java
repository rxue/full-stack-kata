package rx;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig =
{
@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
@ActivationConfigProperty(propertyName="destinationLookup", propertyValue="jms/PublicQueue")
})
public class ReportGenerator implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("Message driven bean invoked!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

}
