package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import backbone.Attribute;
import backbone.Unit;


/**
 * Just a judge. 
 * One is necessary for a proper pairing to occur
 * 
 * @author matt
 *
 */
public abstract class DefaultUnit implements Unit{

	private String _name;
	private ArrayList<Attribute> attributes;
	private HashMap<String, Attribute> titles;

	public DefaultUnit(String name) {
		this._name = name;
		attributes = new ArrayList<Attribute>();
		titles = new HashMap<String, Attribute>();
	}

	@Override
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	protected void addAttribute(Attribute attribute) {
		attributes.add(attribute);
		titles.put(attribute.getTitle(), attribute);
	}
	
	@Override
	public String getName(){
		return this._name;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		Attribute att = titles.get(attribute.getTitle());
		int index = attributes.indexOf(att);
		attributes.remove(index);
		attributes.add(index, attribute);
		titles.put(attribute.getTitle(), attribute);
	}

}
