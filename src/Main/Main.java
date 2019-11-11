package Main;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Main {

	public static void main(String[] args) {
		// Load from 'FCL' file
		String fileName = "fcl/JWGZfcl.fcl";
		FIS fis = FIS.load(fileName, true);

		// Error while loading?
		if (fis == null) {
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}
		// Get default function block
		FunctionBlock functionBlock = fis.getFunctionBlock(null);

		// Show
		JFuzzyChart.get().chart(functionBlock);

		// Skill rating based on job requirements
		fis.setVariable("skill", 1.9f);
		// Years of Experience
		fis.setVariable("experience", 1.1f);
		// How good communicator he is?
		fis.setVariable("softSkills", 2.1f);

		// Evaluate
		fis.evaluate();

		// Show output variable's chart
		// Salary value should be multiplied by 1k 
		Variable salary = functionBlock.getVariable("salary");
		JFuzzyChart.get().chart(salary, salary.getDefuzzifier(), true);

		// Print ruleSet
		System.out.println(fis);

	}

}
