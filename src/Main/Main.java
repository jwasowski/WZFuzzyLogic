package Main;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Main {

	public static void main(String[] args) {
		// Load from 'FCL' file
		String fileName = "fcl/JWGZfcl.fcl";
		// Number of arguments checker
		if (args.length < 3 || args.length > 3) {
			System.err.println("Input must be of three arguments!");
			System.err.println("Syntax: java -jar <jar name> <Skill value> <Experience value> <Soft Skills value>");
			System.err.println("Example: java -jar testJar.jar 1.5 2.3 3.4");
			System.exit(0);
		}
		String[] params = args;
		FIS fis = FIS.load(fileName, true);

		// Error while loading?
		if (fis == null) {
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}
		// Get default function block
		FunctionBlock functionBlock = fis.getFunctionBlock("salaryRating");

		// Show
		JFuzzyChart.get().chart(functionBlock);

		// Skill rating based on job requirements
		fis.setVariable("skill", Float.parseFloat(params[0]));
		// Years of Experience
		fis.setVariable("experience", Float.parseFloat(params[1]));
		// How good communicator he is?
		fis.setVariable("softSkills", Float.parseFloat(params[2]));

		// Evaluate
		fis.evaluate();

		// Show output variable's chart
		// Salary value should be multiplied by 1k
		Variable salary = functionBlock.getVariable("salary");
		JFuzzyChart.get().chart(salary, salary.getDefuzzifier(), true);

		// Print ruleSet
		System.out.println(fis);
		System.out.println("Skill value: " + params[0]);
		System.out.println("Experience value: " + params[1]);
		System.out.println("Soft Skills value: " + params[2]);
		System.out.println("Estimated salary: " + Math.floor(salary.getValue() * 1000));

	}

}
