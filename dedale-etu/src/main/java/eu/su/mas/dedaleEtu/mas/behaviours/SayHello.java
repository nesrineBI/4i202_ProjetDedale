package eu.su.mas.dedaleEtu.mas.behaviours;

import eu.su.mas.dedale.mas.AbstractDedaleAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * This example behaviour try to send a hello message (every 3s maximum) to agents Collect2 Collect1
 * @author hc
 *
 */
public class SayHello extends SimpleBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2058134622078521998L;
	private boolean finished =false;

	/**
	 * An agent tries to contact its friend and to give him its current position
	 * @param myagent the agent who posses the behaviour
	 *  
	 */
	public SayHello (final Agent myagent) {
		super(myagent);
		//super(myagent);
	}

	@Override
	public void action() {
		System.out.println("je dis coucou");
		String myPosition=((AbstractDedaleAgent)this.myAgent).getCurrentPosition();

		//A message is defined by : a performative, a sender, a set of receivers, (a protocol),(a content (and/or contentOBject))
		ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
		msg.setSender(this.myAgent.getAID());
		msg.setProtocol("PositionSending");

		if (myPosition!=""){
			//System.out.println("Agent "+this.myAgent.getLocalName()+ " is trying to reach its friends");
			msg.setContent(myPosition);
			
			//TODO get team list ! → constructor
			if(this.myAgent.getLocalName().equals("Explo1")) {
				msg.addReceiver(new AID("Explo2",AID.ISLOCALNAME));
			} else {
				msg.addReceiver(new AID("Explo1",AID.ISLOCALNAME));
			}
			
			

			//Mandatory to use this method (it takes into account the environment to decide if someone is reachable or not)
			((AbstractDedaleAgent)this.myAgent).sendMessage(msg);
			this.finished =true;
		}
	}



	@Override
	public boolean done() {
		// TODO Stub de la méthode généré automatiquement
		return false;
	}
	
}