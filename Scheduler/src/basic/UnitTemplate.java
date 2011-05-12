package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import backbone.Attribute;
import backbone.Unit;


/**
 * 
 */
public abstract class UnitTemplate implements Unit{

	private String _name;
	private ArrayList<Attribute> _attributes;
	private HashMap<String, Attribute> _titles;

	public UnitTemplate(String name) {
		_name = name;
		_attributes = new ArrayList<Attribute>();
		_titles = new HashMap<String, Attribute>();
	}

	@Override
	public List<Attribute> getAttributes() {
		return _attributes;
	}
	
	protected void addAttribute(Attribute attribute) {
		_attributes.add(attribute);
		_titles.put(attribute.getTitle(), attribute);
	}
	
	@Override
	public String getName(){
		return _name;
	}
	
	@Override
	public void setName(String name){
		_name = name;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		Attribute att = _titles.get(attribute.getTitle());
		int index = _attributes.indexOf(att);
		_attributes.remove(index);
		_attributes.add(index, attribute);
		_titles.put(attribute.getTitle(), attribute);
	}

}
