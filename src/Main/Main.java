package Main;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Main {

	public static void main(String[] args) {
		// Load from 'FCL' file
        String fileName = "fcl/JWGZfcl.fcl";
        FIS fis = FIS.load(fileName,true);

        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }
        // Get default function block
     	FunctionBlock functionBlock = fis.getFunctionBlock(null);
        
        // Show 
        JFuzzyChart.get().chart(functionBlock);
        
        
        // Set inputs
        fis.setVariable("skill", 2);
        fis.setVariable("expirience", 7);
        fis.setVariable("softSkills", 7);

        // Evaluate
        fis.evaluate();

        // Show output variable's chart
        Variable salary = functionBlock.getVariable("salary");
        JFuzzyChart.get().chart(salary, salary.getDefuzzifier(), true);

        // Print ruleSet
        System.out.println(fis);

	}

}
