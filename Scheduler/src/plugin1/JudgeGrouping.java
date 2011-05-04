package plugin1;

import java.util.List;

public class JudgeGrouping extends MyCategory<Judge> {

	public JudgeGrouping(String name) {
		super(name);
	}
	public JudgeGrouping(String name, List<Judge> judges){
		super(name, judges);
	}

	@Override
	public Judge getBlank() {
		return new Judge(null);
	}

}
