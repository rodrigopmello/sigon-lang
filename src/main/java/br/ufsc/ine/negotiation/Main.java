package br.ufsc.ine.negotiation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import agent.AgentLexer;
import agent.AgentParser;
import br.ufsc.ine.agent.Agent;
import br.ufsc.ine.agent.context.ContextService;
import br.ufsc.ine.agent.context.beliefs.BeliefsContextService;
import br.ufsc.ine.agent.context.communication.CommunicationContextService;
import br.ufsc.ine.agent.context.custom.CustomContext;
import br.ufsc.ine.agent.context.desires.DesiresContextService;
import br.ufsc.ine.agent.context.intentions.IntentionsContextService;
import br.ufsc.ine.agent.context.plans.PlansContextService;
import br.ufsc.ine.parser.AgentWalker;
import br.ufsc.ine.parser.VerboseListener;



public class Main{
	
	public static void main(String[] args) {
		startAgent();
		percept();


		


		
	}
	
	public static void sendPropose(List<String> t) {
		
		for (String string : t) {
			System.out.println(string + " propose");
		}
	}
	
	private static void startAgent(){
	    try {

	        File agentFile = new File("/home/rodrigor/repositorios/mcs/sigon-lang/src/main/java/br/ufsc/ine/negotiation/negotiation_sample.on");
	        CharStream stream = CharStreams.fromFileName(agentFile.getAbsolutePath());
	        AgentLexer lexer = new AgentLexer(stream);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);

	        AgentParser parser = new AgentParser(tokens);
	        parser.removeErrorListeners();
	        parser.addErrorListener(new VerboseListener());

	        ParseTree tree = parser.agent();
	        ParseTreeWalker walker = new ParseTreeWalker();
	        System.out.println(tree.toStringTree(parser));
	        

	        AgentWalker agentWalker = new AgentWalker();
	        walker.walk(agentWalker, tree);
	        
	        NegotiationContextService nc =  NegotiationContextService.getInstance();
	        
	        
	        ContextService[] cc = new ContextService[] {nc};
	        //CustomContext[] cc = new CustomContext[] {nc.};	        
	        Agent agent = new Agent();	    
	        agent.run(agentWalker, cc);
	        //agent.run(agentWalker, new String[] {"_negotiation"});
	        
	        System.out.println("NC 1 "+ NegotiationContextService.getInstance().getTheory().toString());
	        
	        
	        
	        
	        
	        




	    } catch (IOException e) {
	        System.out.println("I/O exception.");
	    }
	}
	
	
	 private static void percept(){
	        System.out.println("Percept");

	        //ReadMessage.publish.onNext("readPropose(teste).");
	        ReadMessage.msg.onNext("teste1.");	        
	        
	        
	        
	        System.out.println("CC "+CommunicationContextService.getInstance().getTheory());
	        System.out.println("BC "+BeliefsContextService.getInstance().getTheory().toString());	
			System.out.println("NC "+NegotiationContextService.getInstance().getTheory());

	        System.out.println("DC " +DesiresContextService.getInstance().getTheory());
	        System.out.println("PC " +PlansContextService.getInstance().getTheory());
	        System.out.println("IC "+IntentionsContextService.getInstance().getTheory());
	        System.out.println("CC " +CommunicationContextService.getInstance().getTheory());
	        
	        
	        

	    }

	


	
}




